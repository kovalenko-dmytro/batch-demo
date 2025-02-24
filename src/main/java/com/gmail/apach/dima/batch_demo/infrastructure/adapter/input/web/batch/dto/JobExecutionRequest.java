package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.batch.dto;

import jakarta.validation.constraints.NotBlank;

public record JobExecutionRequest(
    @NotBlank
    String jobName,
    String fileStorageResource
) {
}
