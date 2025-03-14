package com.gmail.apach.dima.batch_demo.common.validator.policy.definition;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import org.springframework.lang.NonNull;

@SuppressWarnings("unused")
public interface ValidationPolicy<I> {

    boolean satisfy(@NonNull I input);

    Error errorCode();

    Object[] errorParams();

    ValidationPolicy<I> then(@NonNull ValidationPolicy<I> other);
}
