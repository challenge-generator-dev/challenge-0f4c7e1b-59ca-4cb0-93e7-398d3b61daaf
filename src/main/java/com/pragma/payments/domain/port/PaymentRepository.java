package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface PaymentRepository {
    Mono<Payment> save(Payment payment);
    Mono<Payment> findById(String id);
    Mono<Payment> findByOperationNumberAndChannel(String operationNumber, String channel);
    Mono<Payment> findByIdempotencyKey(String idempotencyKey);
    Mono<Void> deleteById(String id);
}