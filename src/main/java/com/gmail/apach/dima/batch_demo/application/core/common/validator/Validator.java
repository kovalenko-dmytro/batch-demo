package com.gmail.apach.dima.batch_demo.application.core.common.validator;

public interface Validator<I> {

    void validate(I input) throws Exception;
}
