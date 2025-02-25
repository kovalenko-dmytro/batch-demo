package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.common.dto;

public record ExecuteJobShellArgsWrapper(
    String jobName,
    String fileStorageResource
) {
}
