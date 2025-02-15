package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.oss.exception;

public class ObjectStorageException extends RuntimeException {
    public ObjectStorageException(String msg) {
        super(msg);
    }
}
