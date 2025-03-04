package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CsvLineModel(

    @NotBlank
    @Size(max = 70)
    String fullName,

    @Max(150)
    Integer age,

    Boolean enabled
) {
}
