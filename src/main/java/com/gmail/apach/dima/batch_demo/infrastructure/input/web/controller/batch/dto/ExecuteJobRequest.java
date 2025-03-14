package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto;

import jakarta.validation.constraints.NotBlank;

public record ExecuteJobRequest(
    @NotBlank
    String jobName,
    String fileStorageResource
) {
}
