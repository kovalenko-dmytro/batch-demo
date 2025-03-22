package com.gmail.apach.dima.batch_demo.application.core.job.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum ExitCode {

    UNKNOWN("UNKNOWN"),
    EXECUTING("EXECUTING"),
    COMPLETED("COMPLETED"),
    NOOP("NOOP"),
    FAILED("FAILED"),
    STOPPED("STOPPED");

    private static final Map<String, ExitCode> CACHE = new HashMap<>();
    private final String code;

    static {
        for (var exitCode : ExitCode.values()) {
            CACHE.put(exitCode.code, exitCode);
        }
    }

    public static ExitCode from(String value) {
        return Optional
            .ofNullable(CACHE.get(value))
            .orElseThrow(() ->
                new IllegalArgumentException("Exit code <%s> hasn't supported yet".formatted(value)));
    }
}
