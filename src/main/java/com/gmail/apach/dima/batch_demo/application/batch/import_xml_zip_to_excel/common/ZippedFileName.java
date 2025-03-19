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

    private final String name;

    public static final Set<String> zippedFileNames;

    static {
        zippedFileNames = Arrays
            .stream(ZippedFileName.values())
            .map(ZippedFileName::getName)
            .collect(Collectors.toSet());
    }
}
