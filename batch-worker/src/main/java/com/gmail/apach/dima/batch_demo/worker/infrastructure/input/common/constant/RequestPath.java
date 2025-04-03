package com.gmail.apach.dima.batch_demo.worker.infrastructure.input.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestPath {

    public static final class BatchExecutionApi {

        public static final String ROOT_PATH = "/api/v1/batches";
    }
}
