package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MasterModel(
    @NotBlank
    @Size(max = 20)
    String column1,

    @NotNull
    Integer column2,

    @NotNull
    LocalDateTime column3,

    Boolean column4
) {
}
