package com.gmail.apach.dima.batch_demo.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record BatchWorkerJobExecutionRequest(
    @NotBlank
    String jobName,
    @NotBlank
    String jobExecutionMarker,
    String fileStorageResource
) {
}
