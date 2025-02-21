package com.gmail.apach.dima.batch_demo.core.base.model.job;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.Optional;

public record RequestParameters(
    @NonNull
    Map<RequestParameter, String> parameters
) {

    public String get(RequestParameter requestParameter) {
        return Optional
            .ofNullable(parameters.get(requestParameter))
            .orElse(StringUtils.EMPTY);
    }

    public JobParameters toJobParameters() {
        final var builder = new JobParametersBuilder();
        parameters.forEach((name, value) -> builder.addString(RequestParameter.from(name), value));
        return builder.toJobParameters();
    }
}
