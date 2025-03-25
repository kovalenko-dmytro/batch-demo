package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto;

import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

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
