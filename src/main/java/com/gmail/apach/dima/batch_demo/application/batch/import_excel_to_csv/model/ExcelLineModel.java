package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ExcelLineModel(

    @NotBlank
    @Size(max = 20)
    String firstName,

    @NotBlank
    @Size(max = 50)
    String lastName,
    @Max(150)
    Integer age,

    Boolean active
) {
}
