package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImportCsvToDbTable {

    WORK_TABLE("work_table"),
    MASTER_TABLE("work_table");

    private final String name;
}
