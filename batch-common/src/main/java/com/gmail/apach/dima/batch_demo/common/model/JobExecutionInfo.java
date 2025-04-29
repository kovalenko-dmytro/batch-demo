package com.gmail.apach.dima.batch_demo.common.model;

import lombok.Builder;

import java.util.List;

@Builder
public record JobExecutionInfo(
    String jobName,
    String jobExecutionMarker,
    String batchStatus,
    List<String> failures
) {
}
