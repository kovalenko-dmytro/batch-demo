package com.gmail.apach.dima.batch_demo.core.validator.structure.policy;

import com.gmail.apach.dima.batch_demo.core.config.message.constant.Error;
import org.springframework.lang.NonNull;

public interface ValidationPolicy<I> {

    boolean satisfy(@NonNull I input);


    Error errorCode();

    Object[] errorParams();

    ValidationPolicy<I> then(@NonNull ValidationPolicy<I> other);
}
