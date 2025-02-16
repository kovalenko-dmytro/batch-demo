package com.gmail.apach.dima.batch_demo.core.job.import_example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record WorkLine(

    @NotBlank
    @Size(max = 20)
    String fieldParam1,
    Integer fieldParam2,
    LocalDateTime fieldParam3,
    Boolean fieldParam4,
    String fieldParam5,
    String fieldParam6,
    Integer fieldParam7,
    String fieldParam8,
    Integer fieldParam9
) {
}
