package com.pragma.payments.infrastructure.exception;

public class PaymentProcessingException extends RuntimeException {
    private final String paymentId;
    private final String operation;

    public PaymentProcessingException(String message) {
        super(message);
        this.paymentId = null;
        this.operation = null;
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.operation = null;
    }

    public PaymentProcessingException(String message, String paymentId, String operation) {
        super(message);
        this.paymentId = paymentId;
        this.operation = operation;
    }

    public PaymentProcessingException(String message, String paymentId, String operation, Throwable cause) {
        super(message, cause);
        this.paymentId = paymentId;
        this.operation = operation;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOperation() {
        return operation;
    }
}