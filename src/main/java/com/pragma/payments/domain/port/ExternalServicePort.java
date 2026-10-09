package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface ExternalServicePort {
    Mono<String> validateAntifraud(Payment payment);
    Mono<String> evaluateRisk(Payment payment);
    Mono<String> processBankCore(Payment payment);
    Mono<String> executePaymentGateway(Payment payment);
}