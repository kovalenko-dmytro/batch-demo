package com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record RestApiErrorResponse(
     HttpStatus status,
     String message,
     List<String> errors,
     String timestamp
) {}
