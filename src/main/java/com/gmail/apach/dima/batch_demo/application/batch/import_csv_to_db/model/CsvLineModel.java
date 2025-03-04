package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CsvLineModel(
    @NotBlank
    @Size(max = 20)
    String fieldParam1,
    @Max(100)
    Integer fieldParam2,
    @Past
    LocalDateTime fieldParam3,
    Boolean fieldParam4
) {
}
