package com.gmail.apach.dima.batch_demo.core.config.message.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {

    BATCH_NAME_NOT_DEFINED("error.batch.name.not.defined"),
    FILE_STORAGE_RESOURCE_MISSING("error.file.storage.resource.parameter.missing"),
    FILE_UNABLE_UPLOAD("error.file.storage.unable.upload"),
    FILE_UNABLE_READ("error.file.storage.unable.read"),
    JOB_FAILED("error.job.failed");

    private final String key;
}
