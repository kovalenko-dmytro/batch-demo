package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record JobExecutionErrors(
    HttpStatus status,
    String message,
    List<String> errors,
    String timestamp
) {
}
