package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.file.dto;

import lombok.Builder;

@Builder
public record FileResponse(

    String storageKey,
    String fileName
) {
}
