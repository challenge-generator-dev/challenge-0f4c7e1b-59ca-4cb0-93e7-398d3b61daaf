package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.port.ExternalServicePort;
import com.pragma.payments.infrastructure.exception.ExternalServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Component
public class ExternalServiceAdapter implements ExternalServicePort {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceAdapter.class);

    private final WebClient webClient;

    @Value("${external.services.antifraud.url:http://localhost:8081/api/antifraud}")
    private String antifraudUrl;

    @Value("${external.services.risk.url:http://localhost:8082/api/risk}")
    private String riskUrl;

    @Value("${external.services.bankcore.url:http://localhost:8083/api/bankcore}")
    private String bankCoreUrl;

    @Value("${external.services.gateway.url:http://localhost:8084/api/gateway}")
    private String gatewayUrl;

    @Value("${external.services.timeout:5000}")
    private int serviceTimeout;

    public ExternalServiceAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @CircuitBreaker(name = "antifraudService", fallbackMethod = "antifraudFallback")
    @Retry(name = "antifraudRetry")
    public Mono<String> validateAntifraud(Payment payment) {
        log.info("Llamando servicio de antifraude para el pago: {}", payment.operationNumber());

        return webClient.post()
                .uri(antifraudUrl + "/validate")
                .bodyValue(buildAntifraudRequest(payment))
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofMillis(serviceTimeout))
                .map(response -> {
                    String status = (String) response.getOrDefault("status", "UNKNOWN");
                    log.info("Respuesta de antifraude: {}", status);
                    return status;
                })
                .doOnError(e -> log.error("Error en servicio de antifraude: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(
                        new ExternalServiceException("Error en servicio de antifraude: " + e.getMessage(), e)));
    }

    @Override
    @CircuitBreaker(name = "riskEvaluationService", fallbackMethod = "riskEvaluationFallback")
    @Retry(name = "riskEvaluationRetry")
    public Mono<String> evaluateRisk(Payment payment) {
        log.info("Llamando servicio de evaluación de riesgo para el pago: {}", payment.operationNumber());

        return webClient.post()
                .uri(riskUrl + "/evaluate")
                .bodyValue(buildRiskRequest(payment))
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofMillis(serviceTimeout))
                .map(response -> {
                    String level = (String) response.getOrDefault("riskLevel", "MEDIUM");
                    log.info("Nivel de riesgo evaluado: {}", level);
                    return level;
                })
                .doOnError(e -> log.error("Error en servicio de evaluación de riesgo: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(
                        new ExternalServiceException("Error en servicio de evaluación de riesgo: " + e.getMessage(), e)));
    }

    @Override
    @CircuitBreaker(name = "bankCoreService", fallbackMethod = "bankCoreFallback")
    @Retry(name = "bankCoreRetry")
    public Mono<String> processBankCore(Payment payment) {
        log.info("Llamando servicio de core bancario para el pago: {}", payment.operationNumber());

        return webClient.post()
                .uri(bankCoreUrl + "/process")
                .bodyValue(buildBankCoreRequest(payment))
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofMillis(serviceTimeout))
                .map(response -> {
                    String transactionId = (String) response.getOrDefault("transactionId", "N/A");
                    log.info("ID de transacción bancaria: {}", transactionId);
                    return transactionId;
                })
                .doOnError(e -> log.error("Error en servicio de core bancario: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(
                        new ExternalServiceException("Error en servicio de core bancario: " + e.getMessage(), e)));
    }

    @Override
    @CircuitBreaker(name = "paymentGatewayService", fallbackMethod = "paymentGatewayFallback")
    @Retry(name = "paymentGatewayRetry")
    public Mono<String> executePaymentGateway(Payment payment) {
        log.info("Llamando servicio de gateway de pago para el pago: {}", payment.operationNumber());

        return webClient.post()
                .uri(gatewayUrl + "/execute")
                .bodyValue(buildGatewayRequest(payment))
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofMillis(serviceTimeout))
                .map(response -> {
                    String authorizationCode = (String) response.getOrDefault("authorizationCode", "N/A");
                    log.info("Código de autorización: {}", authorizationCode);
                    return authorizationCode;
                })
                .doOnError(e -> log.error("Error en servicio de gateway de pago: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(
                        new ExternalServiceException("Error en servicio de gateway de pago: " + e.getMessage(), e)));
    }

    private Map<String, Object> buildAntifraudRequest(Payment payment) {
        return Map.of(
                "operationNumber", payment.operationNumber(),
                "amount", payment.amount().toString(),
                "currency", payment.currency(),
                "channel", payment.channel(),
                "customerId", payment.customerId());
    }

    private Map<String, Object> buildRiskRequest(Payment payment) {
        return Map.of(
                "operationNumber", payment.operationNumber(),
                "amount", payment.amount().toString(),
                "customerId", payment.customerId(),
                "channel", payment.channel());
    }

    private Map<String, Object> buildBankCoreRequest(Payment payment) {
        return Map.of(
                "operationNumber", payment.operationNumber(),
                "amount", payment.amount().toString(),
                "currency", payment.currency(),
                "customerId", payment.customerId(),
                "accountNumber", payment.accountNumber() != null ? payment.accountNumber() : "");
    }

    private Map<String, Object> buildGatewayRequest(Payment payment) {
        return Map.of(
                "operationNumber", payment.operationNumber(),
                "amount", payment.amount().toString(),
                "currency", payment.currency(),
                "customerId", payment.customerId(),
                "paymentMethod", payment.paymentMethod() != null ? payment.paymentMethod() : "CARD");
    }

    private Mono<String> antifraudFallback(Payment payment, Throwable t) {
        log.warn("Fallback de antifraude activado para pago: {}. Razón: {}", payment.operationNumber(), t.getMessage());
        return Mono.just("FALLBACK_APPROVED");
    }

    private Mono<String> riskEvaluationFallback(Payment payment, Throwable t) {
        log.warn("Fallback de evaluación de riesgo activado para pago: {}. Razón: {}", payment.operationNumber(), t.getMessage());
        return Mono.just("FALLBACK_MEDIUM_RISK");
    }

    private Mono<String> bankCoreFallback(Payment payment, Throwable t) {
        log.warn("Fallback de core bancario activado para pago: {}. Razón: {}", payment.operationNumber(), t.getMessage());
        return Mono.just("FALLBACK_TXN_" + System.currentTimeMillis());
    }

    private Mono<String> paymentGatewayFallback(Payment payment, Throwable t) {
        log.warn("Fallback de gateway de pago activado para pago: {}. Razón: {}", payment.operationNumber(), t.getMessage());
        return Mono.just("FALLBACK_AUTH_" + System.currentTimeMillis());
    }

    public void updateServiceUrls(String antifraud, String risk, String bankCore, String gateway) {
        this.antifraudUrl = antifraud;
        this.riskUrl = risk;
        this.bankCoreUrl = bankCore;
        this.gatewayUrl = gateway;
    }

    public void updateTimeout(int timeout) {
        this.serviceTimeout = timeout;
    }
}