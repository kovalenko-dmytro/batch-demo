package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.common.swagger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OpenApi {

    OPEN_API_INFO_TITLE("Batch manager application REST API");

    private final String value;
}
