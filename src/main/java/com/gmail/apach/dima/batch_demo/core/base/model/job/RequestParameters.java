package com.gmail.apach.dima.batch_demo.core.base.model.job;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.Optional;

public record RequestParameters(
    @NonNull
    Map<Parameter, String> parameters
) {

    public String get(Parameter parameter) {
        return Optional
            .ofNullable(parameters.get(parameter))
            .orElse(StringUtils.EMPTY);
    }

    public JobParametersBuilder fromProperties() {
        final var builder = new JobParametersBuilder();
        parameters.forEach((name, value) -> builder.addString(Parameter.from(name), value));
        return builder;
    }
}
