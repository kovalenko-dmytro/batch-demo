package com.gmail.apach.dima.batch_demo.common.model;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record RestClientErrors(
    HttpStatus status,
    String message,
    List<String> errors,
    String timestamp
) {
}
