package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto;

import java.util.List;

public record GetRegisteredJobsResponse(
    List<String> jobNames
) {
}
