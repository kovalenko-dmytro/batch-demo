package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum CsvFileHeaders {

    FULL_NAME("full_name"),
    AGE("age"),
    ENABLED("enabled");

    private final String name;

    public static final String[] headers =
        Arrays.stream(CsvFileHeaders.values()).map(CsvFileHeaders::getName).toArray(String[]::new);
}
