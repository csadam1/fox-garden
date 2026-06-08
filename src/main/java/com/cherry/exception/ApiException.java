package com.cherry.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final int status;

    public ApiException(final String message, final int status) {
        super(message);
        this.status = status;
    }
}
