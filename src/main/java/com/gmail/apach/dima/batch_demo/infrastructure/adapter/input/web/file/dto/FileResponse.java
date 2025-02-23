package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.file.dto;

import lombok.Builder;

@Builder
public record FileResponse(
    String storageKey,
    String fileName
) {
}
