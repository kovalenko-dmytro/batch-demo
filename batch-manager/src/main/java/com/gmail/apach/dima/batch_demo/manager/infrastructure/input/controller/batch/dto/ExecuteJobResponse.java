package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gmail.apach.dima.batch_demo.common.dto.RestApiErrorResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ExecuteJobResponse(
    String jobName,
    String jobExecutionMarker,
    String batchStatus,
    List<String> failures,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    RestApiErrorResponse restClientErrors
) {
}
