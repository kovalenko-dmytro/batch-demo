package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SettingExcelLine(

    @NotNull
    Boolean activeFlag,

    @NotNull
    Boolean approveFlag
) {
}
