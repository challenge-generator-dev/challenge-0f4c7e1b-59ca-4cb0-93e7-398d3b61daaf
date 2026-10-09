package com.pragma.payments.application.usecase;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.ExternalServicePort;
import com.pragma.payments.domain.port.PaymentRepository;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Service
public class PaymentUseCase {

    private static final Logger log = LoggerFactory.getLogger(PaymentUseCase.class);
    private static final int MAX_RETRY_COUNT = 3;
    private static final Duration RETRY_WAIT_DURATION = Duration.ofMillis(500);

    private final PaymentRepository paymentRepository;
    private final ExternalServicePort externalServicePort;

    public PaymentUseCase(PaymentRepository paymentRepository, ExternalServicePort externalServicePort) {
        this.paymentRepository = paymentRepository;
        this.externalServicePort = externalServicePort;
    }

    @Retry(name = "paymentProcessing")
    public Mono<Payment> processPayment(Payment payment) {
        log.info("Iniciando procesamiento de pago con número de operación: {}", payment.operationNumber());

        return paymentRepository.findByIdempotencyKey(payment.idempotencyKey())
                .flatMap(existingPayment -> {
                    log.info("Pago idempotente encontrado, retornando resultado existente: {}", existingPayment.id());
                    return Mono.just(existingPayment);
                })
                .switchIfEmpty(Mono.defer(() -> executePaymentFlow(payment)))
                .doOnSuccess(result -> log.info("Pago procesado exitosamente: {}", result.id()))
                .doOnError(error -> log.error("Error en procesamiento de pago: {}", error.getMessage()));
    }

    private Mono<Payment> executePaymentFlow(Payment payment) {
        Payment pendingPayment = payment.withStatus(PaymentStatus.PENDING)
                .withIdempotencyKey(payment.idempotencyKey() != null ? payment.idempotencyKey() : UUID.randomUUID().toString());

        return paymentRepository.save(pendingPayment)
                .flatMap(savedPayment -> validateAntifraud(savedPayment)
                        .flatMap(antifraudResponse -> processRiskEvaluation(savedPayment, antifraudResponse))
                        .flatMap(riskResponse -> processBankCore(riskResponse.getKey(), riskResponse.getValue()))
                        .flatMap(bankCoreResponse -> executePaymentGateway(bankCoreResponse.getKey(), bankCoreResponse.getValue()))
                        .flatMap(gatewayResponse -> updatePaymentWithGatewayResponse(gatewayResponse.getKey(), gatewayResponse.getValue()))
                );
    }

    @CircuitBreaker(name = "antifraudCircuitBreaker", fallbackMethod = "antifraudFallback")
    private Mono<Payment> validateAntifraud(Payment payment) {
        log.info("Validando antifraude para el pago: {}", payment.id());
        return externalServicePort.validateAntifraud(payment)
                .flatMap(response -> {
                    log.info("Respuesta de antifraude recibida: {}", response);
                    Payment updatedPayment = payment.withAntifraudResponse(response)
                            .withStatus(PaymentStatus.ANTIFRAUD_VALIDATED);
                    return paymentRepository.save(updatedPayment)
                            .thenReturn(updatedPayment);
                });
    }

    @CircuitBreaker(name = "riskEvaluationCircuitBreaker", fallbackMethod = "riskEvaluationFallback")
    private Mono<java.util.Map.Entry<Payment, String>> processRiskEvaluation(Payment payment, String antifraudResponse) {
        log.info("Evaluando riesgo para el pago: {}", payment.id());
        return externalServicePort.evaluateRisk(payment)
                .flatMap(response -> {
                    log.info("Respuesta de evaluación de riesgo recibida: {}", response);
                    Payment updatedPayment = payment.withRiskResponse(response)
                            .withStatus(PaymentStatus.RISK_EVALUATED);
                    return paymentRepository.save(updatedPayment)
                            .map(savedPayment -> java.util.Map.entry(savedPayment, response));
                });
    }

    @CircuitBreaker(name = "bankCoreCircuitBreaker", fallbackMethod = "bankCoreFallback")
    private Mono<java.util.Map.Entry<Payment, String>> processBankCore(Payment payment, String riskResponse) {
        log.info("Procesando en core bancario para el pago: {}", payment.id());
        return externalServicePort.processBankCore(payment)
                .flatMap(response -> {
                    log.info("Respuesta del core bancario recibida: {}", response);
                    Payment updatedPayment = payment.withBankCoreResponse(response)
                            .withStatus(PaymentStatus.BANK_CORE_PROCESSED);
                    return paymentRepository.save(updatedPayment)
                            .map(savedPayment -> java.util.Map.entry(savedPayment, response));
                });
    }

    @CircuitBreaker(name = "paymentGatewayCircuitBreaker", fallbackMethod = "paymentGatewayFallback")
    private Mono<java.util.Map.Entry<Payment, String>> executePaymentGateway(Payment payment, String bankCoreResponse) {
        log.info("Ejecutando gateway de pago para el pago: {}", payment.id());
        return externalServicePort.executePaymentGateway(payment)
                .flatMap(response -> {
                    log.info("Respuesta del gateway de pago recibida: {}", response);
                    Payment updatedPayment = payment.withPaymentGatewayResponse(response)
                            .withStatus(PaymentStatus.COMPLETED);
                    return paymentRepository.save(updatedPayment)
                            .map(savedPayment -> java.util.Map.entry(savedPayment, response));
                });
    }

    private Mono<Payment> updatePaymentWithGatewayResponse(Payment payment, String gatewayResponse) {
        log.info("Actualizando estado final del pago: {}", payment.id());
        return paymentRepository.save(payment.withStatus(PaymentStatus.COMPLETED));
    }

    private Mono<Payment> antifraudFallback(Payment payment, Throwable t) {
        log.error("Fallback de antifraude activado para pago: {}. Error: {}", payment.id(), t.getMessage());
        Payment failedPayment = payment.withStatus(PaymentStatus.FAILED)
                .withAntifraudResponse("FALLBACK: " + t.getMessage());
        return paymentRepository.save(failedPayment)
                .thenReturn(failedPayment);
    }

    private Mono<java.util.Map.Entry<Payment, String>> riskEvaluationFallback(Payment payment, Throwable t) {
        log.error("Fallback de evaluación de riesgo activado para pago: {}. Error: {}", payment.id(), t.getMessage());
        Payment failedPayment = payment.withStatus(PaymentStatus.FAILED)
                .withRiskResponse("FALLBACK: " + t.getMessage());
        return paymentRepository.save(failedPayment)
                .map(savedPayment -> java.util.Map.entry(savedPayment, "FALLBACK: " + t.getMessage()));
    }

    private Mono<java.util.Map.Entry<Payment, String>> bankCoreFallback(Payment payment, Throwable t) {
        log.error("Fallback de core bancario activado para pago: {}. Error: {}", payment.id(), t.getMessage());
        Payment failedPayment = payment.withStatus(PaymentStatus.FAILED)
                .withBankCoreResponse("FALLBACK: " + t.getMessage());
        return paymentRepository.save(failedPayment)
                .map(savedPayment -> java.util.Map.entry(savedPayment, "FALLBACK: " + t.getMessage()));
    }

    private Mono<java.util.Map.Entry<Payment, String>> paymentGatewayFallback(Payment payment, Throwable t) {
        log.error("Fallback de gateway de pago activado para pago: {}. Error: {}", payment.id(), t.getMessage());
        Payment failedPayment = payment.withStatus(PaymentStatus.FAILED)
                .withPaymentGatewayResponse("FALLBACK: " + t.getMessage());
        return paymentRepository.save(failedPayment)
                .map(savedPayment -> java.util.Map.entry(savedPayment, "FALLBACK: " + t.getMessage()));
    }

    public Mono<Payment> getPaymentById(String id) {
        log.info("Consultando pago por ID: {}", id);
        return paymentRepository.findById(id)
                .switchIfEmpty(Mono.error(new PaymentProcessingException("Pago no encontrado con ID: " + id)));
    }

    public Mono<Payment> getPaymentByOperationNumber(String operationNumber, String channel) {
        log.info("Consultando pago por número de operación: {} y canal: {}", operationNumber, channel);
        return paymentRepository.findByOperationNumberAndChannel(operationNumber, channel)
                .switchIfEmpty(Mono.error(new PaymentProcessingException(
                        "Pago no encontrado con número de operación: " + operationNumber + " y canal: " + channel)));
    }
}