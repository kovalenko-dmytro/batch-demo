package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ImportCsvToDbFileHeaders {

    FIELD_PARAM_1("field_param_1"),
    FIELD_PARAM_2("field_param_2"),
    FIELD_PARAM_3("field_param_3"),
    FIELD_PARAM_4("field_param_4");

    private final String name;

    public static final String[] headers =
        Arrays.stream(ImportCsvToDbFileHeaders.values()).map(ImportCsvToDbFileHeaders::getName).toArray(String[]::new);
}
