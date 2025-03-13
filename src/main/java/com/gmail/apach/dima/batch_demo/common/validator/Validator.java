package com.gmail.apach.dima.batch_demo.common.validator;

public interface Validator<I> {

    void validate(I input) throws Exception;
}
