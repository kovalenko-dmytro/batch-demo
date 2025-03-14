package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.file.dto;

import lombok.Builder;

@Builder
public record FileResponse(
    String storageKey,
    String fileName
) {
}
