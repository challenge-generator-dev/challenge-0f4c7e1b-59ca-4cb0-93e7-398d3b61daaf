package com.pragma.payments.infrastructure.exception;

import com.pragma.payments.domain.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IdempotencyException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleIdempotencyException(IdempotencyException ex) {
        log.warn("Violación de idempotencia: {} - Key: {}", ex.getMessage(), ex.getIdempotencyKey());
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", ex.getMessage());
        body.put("idempotencyKey", ex.getIdempotencyKey());
        
        if (ex.getOperationNumber() != null) {
            body.put("operationNumber", ex.getOperationNumber());
        }
        
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(body));
    }

    @ExceptionHandler(PaymentProcessingException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handlePaymentProcessingException(PaymentProcessingException ex) {
        log.error("Error procesamiento pago: {} - Payment ID: {}", ex.getMessage(), ex.getPaymentId(), ex);
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        body.put("error", "Payment Processing Failed");
        body.put("message", ex.getMessage());
        body.put("paymentId", ex.getPaymentId());
        
        if (ex.getCause() != null) {
            body.put("cause", ex.getCause().getMessage());
        }
        
        HttpStatus status = ex.isRetryable() ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.UNPROCESSABLE_ENTITY;
        return Mono.just(ResponseEntity.status(status).body(body));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleExternalServiceException(ExternalServiceException ex) {
        log.error("Error servicio externo: {} - Servicio: {}", ex.getMessage(), ex.getServiceName(), ex);
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.BAD_GATEWAY.value());
        body.put("error", "External Service Error");
        body.put("message", ex.getMessage());
        body.put("serviceName", ex.getServiceName());
        body.put("retryable", ex.isRetryable());
        
        HttpStatus status = ex.isRetryable() ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.BAD_GATEWAY;
        return Mono.just(ResponseEntity.status(status).body(body));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Argumento inválido: {}", ex.getMessage());
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGenericException(Exception ex) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Error interno del servidor");
        
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body));
    }
}