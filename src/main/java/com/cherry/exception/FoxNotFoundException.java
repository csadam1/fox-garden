package com.cherry.exception;

public class FoxNotFoundException extends ApiException {

    public FoxNotFoundException(long id) {
        super(String.format("Fox not found with id %s", id), 404);
    }
}
