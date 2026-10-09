package com.pragma.payments.infrastructure.adapter;


import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.application.usecase.PaymentUseCase;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.infrastructure.exception.IdempotencyException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private static final String IDEMPOTENCY_HEADER = "X-Idempotency-Key";
    
    private final PaymentUseCase paymentUseCase;

    public PaymentController(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> processPayment(
            @RequestHeader(value = IDEMPOTENCY_HEADER, required = false) String idempotencyKey,
            @RequestBody PaymentRequest request) {
        
        log.info("Recibida solicitud de pago - OperationNumber: {}, Amount: {}, Channel: {}",
                request.operationNumber(), request.amount(), request.channel());
        
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            log.debug("Key de idempotencia proporcionada: {}", idempotencyKey);
        }
        
        if (request.operationNumber() == null || request.operationNumber().isBlank()) {
            return Mono.error(new IllegalArgumentException("El número de operación es requerido"));
        }
        
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new IllegalArgumentException("El monto debe ser mayor a cero"));
        }
        
        if (request.channel() == null || request.channel().isBlank()) {
            return Mono.error(new IllegalArgumentException("El canal de pago es requerido"));
        }
        
        Payment payment = new Payment(
            request.operationNumber(),
            request.amount(),
            request.channel(),
            Payment.PaymentStatus.PENDING,
            request.currency() != null ? request.currency() : "USD",
            request.customerId()
        );
        
        return paymentUseCase.processPayment(payment, idempotencyKey)
            .map(this::buildSuccessResponse)
            .onErrorResume(IdempotencyException.class, ex -> 
                Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(buildErrorResponse("CONFLICT", ex.getMessage(), idempotencyKey))))
            .onErrorResume(PaymentProcessingException.class, ex -> 
                Mono.just(ResponseEntity.status(ex.isRetryable() ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(buildErrorResponse("PAYMENT_FAILED", ex.getMessage(), null))))
            .onErrorResume(IllegalArgumentException.class, ex -> 
                Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(buildErrorResponse("BAD_REQUEST", ex.getMessage(), null))));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Payment>> getPayment(@PathVariable String id) {
        log.info("Consultando pago con ID: {}", id);
        
        return paymentUseCase.getPaymentById(id)
            .map(payment -> ResponseEntity.ok(payment))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Pago no encontrado")));
    }

    @GetMapping("/by-operation")
    public Mono<ResponseEntity<Payment>> getPaymentByOperation(
            @RequestParam String operationNumber,
            @RequestParam String channel) {
        log.info("Consultando pago por operación: {} y canal: {}", operationNumber, channel);
        
        return paymentUseCase.getPaymentByOperationNumberAndChannel(operationNumber, channel)
            .map(payment -> ResponseEntity.ok(payment))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Pago no encontrado para la operación y canal especificados")));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> cancelPayment(@PathVariable String id) {
        log.info("Cancelando pago con ID: {}", id);
        
        return paymentUseCase.cancelPayment(id)
            .then(Mono.just(ResponseEntity.noContent().build()))
            .onErrorResume(ex -> Mono.just(ResponseEntity.notFound().build()));
    }

    private ResponseEntity<Map<String, Object>> buildSuccessResponse(Payment payment) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", payment.id());
        response.put("operationNumber", payment.operationNumber());
        response.put("amount", payment.amount());
        response.put("currency", payment.currency());
        response.put("channel", payment.channel());
        response.put("status", payment.status().name());
        response.put("createdAt", payment.createdAt() != null ? payment.createdAt().toString() : Instant.now().toString());
        
        if (payment.antifraudResponse() != null) {
            response.put("antifraudResponse", payment.antifraudResponse());
        }
        if (payment.riskResponse() != null) {
            response.put("riskResponse", payment.riskResponse());
        }
        if (payment.bankCoreResponse() != null) {
            response.put("bankCoreResponse", payment.bankCoreResponse());
        }
        if (payment.paymentGatewayResponse() != null) {
            response.put("paymentGatewayResponse", payment.paymentGatewayResponse());
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Map<String, Object> buildErrorResponse(String error, String message, String idempotencyKey) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", error);
        response.put("message", message);
        response.put("timestamp", Instant.now().toString());
        
        if (idempotencyKey != null) {
            response.put("idempotencyKey", idempotencyKey);
        }
        
        return response;
    }

    public record PaymentRequest(
        String operationNumber,
        BigDecimal amount,
        String channel,
        String currency,
        String customerId
    ) {}
}