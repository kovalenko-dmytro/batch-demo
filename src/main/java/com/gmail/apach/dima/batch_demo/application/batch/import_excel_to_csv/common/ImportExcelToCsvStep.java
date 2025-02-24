package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImportExcelToCsvStep {

    IMPORT_EXCEL_STEP("import-excel-step"),
    EXCEL_TO_CSV_STEP("excel-to-csv-step"),
    EXPORT_CSV_STEP("export-csv-step");

    private final String name;
}
