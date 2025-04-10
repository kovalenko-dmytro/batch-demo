package com.gmail.apach.dima.batch_demo.common.dto;

import lombok.Builder;

@Builder
public record WorkerJobExecutionResponse(
    String jobName,
    String jobExecutionMarker,
    String batchStatus
) {
}
