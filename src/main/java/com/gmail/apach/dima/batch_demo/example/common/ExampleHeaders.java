package com.gmail.apach.dima.batch_demo.example.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ExampleHeaders {

    HEADER_STRING("header_string"),
    HEADER_INTEGER("header_integer"),
    HEADER_DATE_TIME("header_datetime"),
    HEADER_BOOLEAN("header_boolean");

    private final String value;

    public static final String[] headers =
        Arrays.stream(ExampleHeaders.values()).map(ExampleHeaders::getValue).toArray(String[]::new);
}
