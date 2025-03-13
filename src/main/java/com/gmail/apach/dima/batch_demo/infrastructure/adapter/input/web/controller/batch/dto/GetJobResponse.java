package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.batch.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetJobResponse(
    String jobExecutionMarker,
    String jobName,
    Integer jobInstanceId,
    Integer jobExecutionId,
    Integer version,
    List<JobStepResponse> steps,
    LocalDateTime createTime,
    LocalDateTime startTime,
    LocalDateTime endTime,
    String status,
    String exitCode,
    String exitMessage,
    LocalDateTime lastUpdated
) {
}
