package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum ConfigSheetHeader {

    LOCALE("Locale"),
    VERSION("Version");

    public static final Set<String> headers =
        Arrays.stream(ConfigSheetHeader.values()).map(ConfigSheetHeader::getName).collect(Collectors.toSet());

    private final String name;
}
