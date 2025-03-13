package com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestPath {

    public static final String BATCH_API_ROOT_PATH = "/api/v1/batches";
    public static final String GET_JOB_PATH = "/api/v1/batches/{jobExecutionMarker}";
    public static final String FILE_API_ROOT_PATH = "/api/v1/files";
    public static final String FILE_API_BY_STORAGE_KEY_PATH = "/api/v1/files/{fileStorageKey}";
}
