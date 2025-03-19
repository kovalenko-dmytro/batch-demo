package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum TemplateSheetHeader {

    NAME("Name"),
    DESCRIPTION("Description"),
    CODE("Code"),
    ENABLED("Enabled");

    private final String name;

    public static final List<String> headers;

    static {
        headers = Arrays
            .stream(TemplateSheetHeader.values())
            .map(TemplateSheetHeader::getName)
            .toList();
    }
}
