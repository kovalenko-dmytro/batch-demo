package com.gmail.apach.dima.batch_demo.core.job.import_example.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum FileHeaders {

    FIELD_PARAM_1("field_param_1"),
    FIELD_PARAM_2("field_param_2"),
    FIELD_PARAM_3("field_param_3"),
    FIELD_PARAM_4("field_param_4"),
    FIELD_PARAM_5("field_param_5"),
    FIELD_PARAM_6("field_param_6"),
    FIELD_PARAM_7("field_param_7"),
    FIELD_PARAM_8("field_param_8"),
    FIELD_PARAM_9("field_param_9");

    private final String name;

    public static final String[] headers =
        Arrays.stream(FileHeaders.values()).map(FileHeaders::getName).toArray(String[]::new);
}
