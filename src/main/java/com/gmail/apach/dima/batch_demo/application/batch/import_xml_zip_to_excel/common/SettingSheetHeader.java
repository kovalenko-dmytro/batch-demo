package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum SettingSheetHeader {

    ACTIVE_FLAG("Active Flag"),
    APPROVE_FLAG("Approve Flag");

    public static final List<String> headers = List.of(
        SettingSheetHeader.ACTIVE_FLAG.getName(),
        SettingSheetHeader.APPROVE_FLAG.getName()
    );

    private final String name;
}
