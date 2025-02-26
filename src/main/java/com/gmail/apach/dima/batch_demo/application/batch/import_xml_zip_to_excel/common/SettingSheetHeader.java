package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum SettingSheetHeader {

    ACTIVE_FLAG("Active Flag"),
    APPROVE_FLAG("Approve Flag");

    public static final Set<String> headers =
        Arrays.stream(SettingSheetHeader.values()).map(SettingSheetHeader::getName).collect(Collectors.toSet());

    private final String name;
}
