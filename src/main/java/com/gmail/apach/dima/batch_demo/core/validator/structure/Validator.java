package com.gmail.apach.dima.batch_demo.core.validator.structure;

import com.gmail.apach.dima.batch_demo.core.exception.ValidationException;

public interface Validator <I> {

    void validate(I input) throws ValidationException;
}
