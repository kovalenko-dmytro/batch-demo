package com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.swagger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OpenApi {

    OPEN_API_INFO_TITLE("Batch application REST API documentation");

    private final String value;
}
