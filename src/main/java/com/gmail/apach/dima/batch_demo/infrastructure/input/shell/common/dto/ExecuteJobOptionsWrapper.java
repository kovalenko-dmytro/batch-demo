package com.gmail.apach.dima.batch_demo.infrastructure.input.shell.common.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public record ExecuteJobOptionsWrapper(
    String jobName,
    String jobExecutionMarker,
    String fileStorageResource
) {

    public String defineJobExecutionMarker() {
        return StringUtils.isNoneBlank(jobExecutionMarker) ? jobExecutionMarker : UUID.randomUUID().toString();
    }
}
