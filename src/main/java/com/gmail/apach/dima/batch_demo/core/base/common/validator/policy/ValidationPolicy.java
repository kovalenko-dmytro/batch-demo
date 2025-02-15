package com.gmail.apach.dima.batch_demo.core.base.common.validator.policy;

import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import org.springframework.lang.NonNull;

@SuppressWarnings("unused")
public interface ValidationPolicy<I> {

    boolean satisfy(@NonNull I input);


    Error errorCode();

    Object[] errorParams();

    ValidationPolicy<I> then(@NonNull ValidationPolicy<I> other);
}
