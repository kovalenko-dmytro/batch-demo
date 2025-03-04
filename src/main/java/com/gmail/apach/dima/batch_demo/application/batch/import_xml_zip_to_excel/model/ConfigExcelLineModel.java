package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ConfigExcelLineModel(

    @NotBlank
    String locale,

    @NotBlank
    String version
) {
}
