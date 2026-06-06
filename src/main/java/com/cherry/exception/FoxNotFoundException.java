package com.cherry.exception;

public class FoxNotFoundException extends ApiException {
    private final long id;

    public FoxNotFoundException(long id) {
        super(404);
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Fox not found with id %s", id);
    }
}
