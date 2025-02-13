package com.gmail.apach.dima.batch_demo.example.model;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExampleLine(
    @NotBlank
    @Size(min = 10, max = 10)
    String stringColumn,

    @NotNull
    @Min(value = 10)
    Integer integerColumn,

    @NotNull
    @Past
    LocalDateTime dateTimeColumn,

    Boolean booleanColumn
) {
}
