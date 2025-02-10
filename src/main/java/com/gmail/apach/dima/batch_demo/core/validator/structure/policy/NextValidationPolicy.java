package com.gmail.apach.dima.batch_demo.core.validator.structure.policy;

import com.gmail.apach.dima.batch_demo.core.config.message.constant.Error;
import org.springframework.lang.NonNull;

public class NextValidationPolicy<I> extends AbstractValidationPolicy<I> {

    private final ValidationPolicy<I> next;

    public NextValidationPolicy(ValidationPolicy<I> next) {
        this.next = next;
    }

    @Override
    public boolean satisfy(@NonNull I input) {
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
