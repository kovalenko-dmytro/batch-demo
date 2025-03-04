package com.gmail.apach.dima.batch_demo.infrastructure.common.message.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {

    BATCH_NAME_NOT_DEFINED("error.batch.name.not.defined"),
    FILE_STORAGE_RESOURCE_MISSING("error.file.storage.resource.parameter.missing"),
    FILE_UNABLE_UPLOAD("error.file.storage.unable.upload"),
    FILE_ZIP_CONTENT_MISSING("error.file.zip.content.missing"),
    JOB_INTERRUPTED("error.job.interrupted"),
    JOB_FAILED("error.job.failed"),

    VALIDATION_REQUEST("error.validation.request"),
    MISSING_REQUEST_PARAMETER("error.missing.request.parameter"),
    NO_HANDLER_FOUND("error.no.handler.found"),
    HTTP_METHOD_NOT_ALLOWED("error.http.method.not.allowed"),
    MEDIA_TYPE_NOT_SUPPORTED("error.media.type.not.supported"),
    INTERNAL_SERVER_ERROR_OCCURRED("error.internal.server.error.occurred");

    private final String key;
}
