package com.gmail.apach.dima.batch_demo.application.core.job.model;

import com.gmail.apach.dima.batch_demo.common.constant.Delimiter;
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
            .orElse(Delimiter.EMPTY);
    }

    public JobParametersBuilder toJobParameters() {
        final var builder = new JobParametersBuilder();
        parameters.forEach((name, value) -> builder.addString(RequestParameter.from(name), value));
        return builder;
    }
}
