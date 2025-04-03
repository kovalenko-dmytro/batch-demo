package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Builder
public record ExecuteJobRequest(
    @NotBlank
    String jobName,
    String jobExecutionMarker,
    String fileStorageResource
) {

    public String defineJobExecutionMarker() {
        return StringUtils.isNoneBlank(jobExecutionMarker) ? jobExecutionMarker : UUID.randomUUID().toString();
    }
}
