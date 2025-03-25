package com.gmail.apach.dima.batch_demo.application.core.job.model;

import com.gmail.apach.dima.batch_demo.common.constant.Delimiter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.lang.NonNull;

import java.util.Map;

public record RequestParameters(
    @NonNull
    Map<RequestParameter, String> parameters
) {

    public String get(@NonNull RequestParameter requestParameter) {
        return this.parameters.getOrDefault(requestParameter, Delimiter.EMPTY);
    }

    public JobParameters toJobParameters() {
        final var builder = new JobParametersBuilder();
        parameters.forEach((parameter, value) -> builder.addString(parameter.getName(), value));
        return builder.toJobParameters();
    }
}
