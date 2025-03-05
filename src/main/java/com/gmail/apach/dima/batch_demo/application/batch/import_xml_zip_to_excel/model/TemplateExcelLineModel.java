package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record TemplateExcelLineModel(
    @NotBlank
    @Size(max = 100)
    String name,
    @NotNull
    @Size(max = 500)
    String description,
    @NotNull
    Integer code,
    Boolean enabled
) {
}
