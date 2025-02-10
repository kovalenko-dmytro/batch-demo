package com.gmail.apach.dima.batch_demo.core.exception;

import org.springframework.batch.core.JobExecutionException;

public class ObjectStorageException extends JobExecutionException {
    public ObjectStorageException(String msg) {
        super(msg);
    }
}
