package com.pragma.payments.infrastructure.exception;

public class ExternalServiceException extends RuntimeException {
    private final String serviceName;
    private final int httpStatusCode;
    private final boolean retryable;

    public ExternalServiceException(String message, String serviceName) {
        super(message);
        this.serviceName = serviceName;
        this.httpStatusCode = 0;
        this.retryable = true;
    }

    public ExternalServiceException(String message, String serviceName, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceName;
        this.httpStatusCode = 0;
        this.retryable = true;
    }

    public ExternalServiceException(String message, String serviceName, int httpStatusCode, boolean retryable) {
        super(message);
        this.serviceName = serviceName;
        this.httpStatusCode = httpStatusCode;
        this.retryable = retryable;
    }

    public ExternalServiceException(String message, String serviceName, int httpStatusCode, boolean retryable, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceName;
        this.httpStatusCode = httpStatusCode;
        this.retryable = retryable;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public boolean isRetryable() {
        return retryable;
    }
}