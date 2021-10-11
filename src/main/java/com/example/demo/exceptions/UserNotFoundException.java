package com.example.demo.exceptions;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Long id;

    public UserNotFoundException(Long id) {
        super("User with ID[ " + id + "] not found");
        this.id = id;
    }

    public Long getCustomerId() {
        return id;
    }
}
