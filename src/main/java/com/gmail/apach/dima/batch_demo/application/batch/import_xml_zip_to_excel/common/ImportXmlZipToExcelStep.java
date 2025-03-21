package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImportXmlZipToExcelStep {

    IMPORT_ZIP_STEP("import-zip-step"),
    UNPACK_ZIP_STEP("unpack-zip-step"),
    CREATE_EXCEL("create-excel-step"),
    PROCESS_TEMPLATE("process-template-step"),
    PROCESS_SETTING("process-setting-step"),
    PROCESS_CONFIG("process-config-step"),
    EXPORT_EXCEL("export-excel-step");

    private final String name;
}
