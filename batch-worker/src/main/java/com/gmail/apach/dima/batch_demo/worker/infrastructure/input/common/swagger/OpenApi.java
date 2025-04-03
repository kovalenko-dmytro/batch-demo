package com.gmail.apach.dima.batch_demo.worker.infrastructure.input.common.swagger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OpenApi {

    OPEN_API_INFO_TITLE("Batch worker application REST API");

    private final String value;
}
