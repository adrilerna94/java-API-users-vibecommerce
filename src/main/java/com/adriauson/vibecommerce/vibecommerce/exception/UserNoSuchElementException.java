package com.adriauson.vibecommerce.vibecommerce.exception;

public class UserNoSuchElementException extends RuntimeException {
    private final Long id;

    public UserNoSuchElementException(String message, Long id) {
        super(message);
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
