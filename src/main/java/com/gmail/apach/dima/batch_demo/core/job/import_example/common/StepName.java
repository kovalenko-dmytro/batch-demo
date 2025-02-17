package com.gmail.apach.dima.batch_demo.core.job.import_example.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StepName {

    UPLOAD_FILE_STEP("upload-file-step"),
    TRUNCATE_WORK_STEP("truncate-work-step"),
    FILE_TO_WORK_STEP("file-to-work-step"),
    WORK_TO_MASTER_STEP("work-to-master-step");

    private final String name;
}
