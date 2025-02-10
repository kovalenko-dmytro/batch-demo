package com.gmail.apach.dima.batch_demo.core.model.batch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum Parameter {

    BATCH_NAME("batch-name"),
    FILE_STORAGE_RESOURCE("file-storage-resource");

    private final String arg;

    private static final Map<String, Parameter> CACHE = new HashMap<>();

    static {
        for (var parameter : Parameter.values()) {
            CACHE.put(parameter.getArg(), parameter);
        }
    }

    public static Parameter from(String value) {
        return Optional
            .ofNullable(CACHE.get(value))
            .orElseThrow(IllegalArgumentException::new);
    }

    public static String from(Parameter parameter) {
        return parameter.arg;
    }
}
