package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum ZippedFileName {

    TEMPLATE("template.xml"),
    SETTING("setting.xml"),
    CONFIG("config.xml");

    public static final Set<String> zippedFileNames =
        Arrays.stream(ZippedFileName.values()).map(ZippedFileName::getName).collect(Collectors.toSet());

    private final String name;
}
