package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common;

import com.gmail.apach.dima.batch_demo.common.constant.Delimiter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum CsvFileHeaders {

    FULL_NAME("Full name"),
    AGE("Age"),
    ENABLED("Enabled");

    private final String name;

    public static final String headersRow;

    static {
        headersRow = Arrays
            .stream(CsvFileHeaders.values())
            .map(CsvFileHeaders::getName)
            .collect(Collectors.joining(Delimiter.COMMA));
    }
}
