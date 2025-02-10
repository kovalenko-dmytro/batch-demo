package com.gmail.apach.dima.batch_demo.core.exception;

import org.springframework.batch.core.JobExecutionException;

public class ValidationException extends JobExecutionException {
    public ValidationException(String msg) {
        super(msg);
    }
}
