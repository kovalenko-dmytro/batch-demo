package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum ConfigSheetHeader {

    LOCALE("Locale"),
    VERSION("Version");

    public static final List<String> headers = List.of(
        ConfigSheetHeader.LOCALE.getName(),
        ConfigSheetHeader.VERSION.getName()
    );

    private final String name;
}
