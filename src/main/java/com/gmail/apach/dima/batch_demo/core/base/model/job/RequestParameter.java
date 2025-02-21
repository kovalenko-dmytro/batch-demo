package com.gmail.apach.dima.batch_demo.core.base.model.job;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum RequestParameter {

    RUN_MODE("run-mode"),
    JOB_NAME("job-name"),
    JOB_EXEC_MARK("job-exec-mark"),
    FILE_STORAGE_RESOURCE("file-storage-resource");

    private final String arg;

    private static final Map<String, RequestParameter> CACHE = new HashMap<>();

    static {
        for (var parameter : RequestParameter.values()) {
            CACHE.put(parameter.getArg(), parameter);
        }
    }

    public static RequestParameter from(String value) {
        return Optional
            .ofNullable(CACHE.get(value))
            .orElseThrow(() ->
                new IllegalArgumentException("There id no request parameters with value: " + value));
    }

    public static String from(RequestParameter requestParameter) {
        return requestParameter.arg;
    }
}
