package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum ExportFile {

    FILE_NAME("export_file"),
    TEMPLATE_SHEET("Template"),
    SETTING_SHEET("Setting"),
    CONFIG_SHEET("Config");

    private final String value;

    public static Set<String> sheetNames() {
        return Set.of(TEMPLATE_SHEET.value, SETTING_SHEET.value, CONFIG_SHEET.value);
    }
}
