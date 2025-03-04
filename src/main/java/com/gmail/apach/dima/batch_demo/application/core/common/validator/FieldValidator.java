package com.gmail.apach.dima.batch_demo.application.core.common.validator;

import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class FieldValidator<T> {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private final MessageUtil messageUtil;

    public void validate(T object) throws JobInterruptedException {
        final var violations = validator.validate(object);
        if (CollectionUtils.isNotEmpty(violations)) {
            provideError(object, violations);
        }
    }

    private void provideError(T object, Set<ConstraintViolation<T>> violations) throws JobInterruptedException {
        final var errors = collectErrors(violations);
        final var target = object.getClass().getSimpleName();
        final var message = messageUtil.getMessage(Error.VALIDATION_REQUEST, target, errors);
        throw new JobInterruptedException(message, BatchStatus.FAILED);
    }

    private List<String> collectErrors(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
            .map(violation ->
                String.join(
                    Delimiter.COLON.concat(Delimiter.SPACE),
                    violation.getPropertyPath().toString(),
                    violation.getMessage()))
            .toList();
    }
}
