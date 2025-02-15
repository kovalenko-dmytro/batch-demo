package com.gmail.apach.dima.batch_demo.core.base.common.validator.policy;

import org.springframework.lang.NonNull;

public abstract class AbstractValidationPolicy<I> implements ValidationPolicy<I> {

    @Override
    public ValidationPolicy<I> then(@NonNull ValidationPolicy<I> other) {
        return new NextValidationPolicy<>(other);
    }
}
