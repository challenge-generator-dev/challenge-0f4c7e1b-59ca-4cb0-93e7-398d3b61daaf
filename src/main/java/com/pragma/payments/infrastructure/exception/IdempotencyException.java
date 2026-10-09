package com.pragma.payments.infrastructure.exception;

public class IdempotencyException extends RuntimeException {

    private final String idempotencyKey;
    private final String operationNumber;

    public IdempotencyException(String message) {
        super(message);
        this.idempotencyKey = null;
        this.operationNumber = null;
    }

    public IdempotencyException(String message, String idempotencyKey) {
        super(message);
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = null;
    }

    public IdempotencyException(String message, String idempotencyKey, String operationNumber) {
        super(message);
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = operationNumber;
    }

    public IdempotencyException(String message, Throwable cause) {
        super(message, cause);
        this.idempotencyKey = null;
        this.operationNumber = null;
    }

    public IdempotencyException(String message, String idempotencyKey, Throwable cause) {
        super(message, cause);
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = null;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getOperationNumber() {
        return operationNumber;
    }
}