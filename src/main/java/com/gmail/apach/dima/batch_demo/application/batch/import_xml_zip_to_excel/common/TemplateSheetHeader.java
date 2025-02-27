package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum TemplateSheetHeader {

    NAME("Name"),
    DESCRIPTION("Description"),
    CODE("Code"),
    ENABLED("Enabled");

    public static final List<String> headers = List.of(
        TemplateSheetHeader.NAME.getName(),
        TemplateSheetHeader.DESCRIPTION.getName(),
        TemplateSheetHeader.CODE.getName(),
        TemplateSheetHeader.ENABLED.getName()
    );

    private final String name;
}
