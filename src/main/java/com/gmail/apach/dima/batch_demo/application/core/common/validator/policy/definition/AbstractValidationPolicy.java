package com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.definition;

import org.springframework.lang.NonNull;

public abstract class AbstractValidationPolicy<I> implements ValidationPolicy<I> {

    @Override
    public ValidationPolicy<I> then(@NonNull ValidationPolicy<I> other) {
        return new NextValidationPolicy<>(other);
    }
}
