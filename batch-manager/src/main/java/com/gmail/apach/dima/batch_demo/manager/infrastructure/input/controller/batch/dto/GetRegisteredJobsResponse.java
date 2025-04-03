package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto;

import java.util.List;

public record GetRegisteredJobsResponse(

    List<String> jobNames
) {
}
