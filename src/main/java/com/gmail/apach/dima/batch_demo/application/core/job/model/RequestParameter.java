package com.gmail.apach.dima.batch_demo.application.core.job.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum RequestParameter {

    JOB_NAME("job-name"),
    FILE_STORAGE_RESOURCE("file-storage-resource");

    private final String name;

    private static final Map<String, RequestParameter> CACHE = new HashMap<>();

    static {
        for (var parameter : RequestParameter.values()) {
            CACHE.put(parameter.getName(), parameter);
        }
    }

    public static RequestParameter from(String name) {
        return Optional
            .ofNullable(CACHE.get(name))
            .orElseThrow(() -> new IllegalArgumentException("There is no request parameter: " + name));
    }
}
