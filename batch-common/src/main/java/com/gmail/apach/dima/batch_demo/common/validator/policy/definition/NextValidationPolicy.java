package com.gmail.apach.dima.batch_demo.common.validator.policy.definition;

import com.gmail.apach.dima.batch_demo.common.constant.Error;

public class NextValidationPolicy<I> extends AbstractValidationPolicy<I> {

    private final ValidationPolicy<I> next;

    public NextValidationPolicy(ValidationPolicy<I> next) {
        this.next = next;
    }

    @Override
    public boolean satisfy(I input) {
        return next.satisfy(input);
    }

    @Override
    public Error errorCode() {
        return next.errorCode();
    }

    @Override
    public Object[] errorParams() {
        return next.errorParams();
    }
}
