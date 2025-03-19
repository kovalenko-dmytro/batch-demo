package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum CsvFileFieldNames {

    FULL_NAME("fullName"),
    AGE("age"),
    ENABLED("enabled");

    private final String name;

    public static final String[] names;

    static {
        names = Arrays
            .stream(CsvFileFieldNames.values())
            .map(CsvFileFieldNames::getName)
            .toArray(String[]::new);
    }
}
