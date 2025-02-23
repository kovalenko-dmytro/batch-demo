package com.gmail.apach.dima.batch_demo.infrastructure.common.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
