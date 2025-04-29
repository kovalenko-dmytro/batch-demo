package com.gmail.apach.dima.batch_demo.common.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record WorkerJobExecutionResponse(
    String jobName,
    String jobExecutionMarker,
    String batchStatus,
    List<String> failures
) {
}
