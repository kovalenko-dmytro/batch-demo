package com.gmail.apach.dima.batch_demo.common.model;

import lombok.Builder;

@Builder
public record JobExecutionResult(
    JobExecutionInfo info,
    RestClientErrors errors
) {
}
