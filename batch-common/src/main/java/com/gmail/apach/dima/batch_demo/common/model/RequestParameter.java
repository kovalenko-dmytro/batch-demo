package com.gmail.apach.dima.batch_demo.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RequestParameter {

    JOB_NAME("job-name"),
    JOB_EXECUTION_MARKER("job-execution-marker"),
    FILE_STORAGE_RESOURCE("file-storage-resource");

    private final String name;
}
