package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto;

import lombok.Builder;

@Builder
public record ExecuteJobResponse(
    String jobName,
    String jobExecutionMarker,
    String batchStatus,
    JobExecutionErrors errors
) {
}
