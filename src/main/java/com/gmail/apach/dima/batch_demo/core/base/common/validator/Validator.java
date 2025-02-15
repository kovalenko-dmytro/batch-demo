package com.gmail.apach.dima.batch_demo.core.base.common.validator;

import org.springframework.batch.core.JobParametersInvalidException;

public interface Validator <I> {

    void validate(I input) throws JobParametersInvalidException;
}
