package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum TemplateSheetHeader {

    NAME("Name"),
    DESCRIPTION("Description"),
    CODE("Code"),
    ENABLED("Enabled");

    public static final Set<String> headers =
        Arrays.stream(TemplateSheetHeader.values()).map(TemplateSheetHeader::getName).collect(Collectors.toSet());

    private final String name;
}
