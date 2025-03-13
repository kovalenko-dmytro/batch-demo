package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.batch.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record JobStepResponse(
    Integer jobExecutionId,
    Integer version,
    String stepName,
    LocalDateTime createTime,
    LocalDateTime startTime,
    LocalDateTime endTime,
    String status,
    String exitCode,
    String exitMessage,
    LocalDateTime lastUpdated
) {
}
