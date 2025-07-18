package com.adriauson.vibecommerce.exception;

public class UserNotFoundException extends RuntimeException {
    private final Long id;

    public UserNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
