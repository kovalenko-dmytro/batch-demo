package com.gmail.apach.dima.batch_demo.common.validator;

import com.gmail.apach.dima.batch_demo.common.exception.ValidationException;

public interface Validator<T> {

    void validate(T target) throws ValidationException;
}
