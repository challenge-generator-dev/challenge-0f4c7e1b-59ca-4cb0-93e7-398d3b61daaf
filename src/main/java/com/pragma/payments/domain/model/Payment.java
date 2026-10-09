package com.pragma.payments.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Payment(
    String id,
    String operationNumber,
    String channel,
    BigDecimal amount,
    String currency,
    String accountOrigin,
    String accountDestination,
    PaymentStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String idempotencyKey,
    int retryCount,
    String antifraudResponse,
    String riskResponse,
    String bankCoreResponse,
    String paymentGatewayResponse) {

    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        REJECTED
    }

    public Payment {
        Objects.requireNonNull(operationNumber, "Operation number cannot be null");
        Objects.requireNonNull(channel, "Channel cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(accountOrigin, "Account origin cannot be null");
        Objects.requireNonNull(accountDestination, "Account destination cannot be null");
        Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (retryCount < 0) {
            throw new IllegalArgumentException("Retry count cannot be negative");
        }
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            newStatus,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            this.antifraudResponse,
            this.riskResponse,
            this.bankCoreResponse,
            this.paymentGatewayResponse
        );
    }

    public Payment incrementRetryCount() {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            this.updatedAt,
            this.idempotencyKey,
            this.retryCount + 1,
            this.antifraudResponse,
            this.riskResponse,
            this.bankCoreResponse,
            this.paymentGatewayResponse
        );
    }

    public Payment withAntifraudResponse(String response) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            response,
            this.riskResponse,
            this.bankCoreResponse,
            this.paymentGatewayResponse
        );
    }

    public Payment withRiskResponse(String response) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            this.antifraudResponse,
            response,
            this.bankCoreResponse,
            this.paymentGatewayResponse
        );
    }

    public Payment withBankCoreResponse(String response) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            this.antifraudResponse,
            this.riskResponse,
            response,
            this.paymentGatewayResponse
        );
    }

    public Payment withPaymentGatewayResponse(String response) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            this.antifraudResponse,
            this.riskResponse,
            this.bankCoreResponse,
            response
        );
    }
}