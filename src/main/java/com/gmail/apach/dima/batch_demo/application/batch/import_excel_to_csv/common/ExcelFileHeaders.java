package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExcelFileHeaders {

    FIRST_NAME("First Name"),
    LAST_NAME("Last Name"),
    AGE("Age"),
    ACTIVE("Active");

    private final String name;
}
