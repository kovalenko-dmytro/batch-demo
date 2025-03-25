package com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestPath {

    public static final class BatchExecutionApi {
        public static final String ROOT_PATH = "/api/v1/batches";
        public static final String GET_BY_JOB_EXECUTION_MARKER_PATH = ROOT_PATH + "/{job-execution-marker}";
    }

    public static final class BatchRegistryApi {
        public static final String ROOT_PATH = "/api/v1/registered-batches";
    }

    public static final class FileApi {
        public static final String ROOT_PATH = "/api/v1/files";
        public static final String DOWNLOAD_PATH = ROOT_PATH + "/{file-storage-key}";
    }
}
