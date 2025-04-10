package com.gmail.apach.dima.batch_demo.common.model;

import lombok.Builder;

@Builder
public record JobExecutionInfo(
    String jobName,
    String jobExecutionMarker,
    String batchStatus
) {
}
