package com.gmail.apach.dima.batch_demo.worker.infrastructure.input.controller;

import com.gmail.apach.dima.batch_demo.common.dto.WorkerJobExecutionRequest;
import com.gmail.apach.dima.batch_demo.worker.AbstractRestApiIntegrationTest;
import com.gmail.apach.dima.batch_demo.worker.application.core.constant.JobName;
import com.gmail.apach.dima.batch_demo.worker.infrastructure.input.common.constant.RequestPath;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class ExecuteJobRestApiTest extends AbstractRestApiIntegrationTest {

    private static final String FILE_RESOURCE = "csv_to_db_test.csv";

    @Test
    void execute_success() throws Exception {
        final var request = WorkerJobExecutionRequest.builder()
            .jobName(JobName.IMPORT_CSV_TO_DB)
            .jobExecutionMarker(UUID.randomUUID().toString())
            .fileStorageResource(FILE_RESOURCE)
            .build();

        final var jsonRequest = objectMapper.writeValueAsString(request);

        final var result = mvc.perform(
                post(RequestPath.BatchExecutionApi.ROOT_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest))
            .andReturn();

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    void execute_emptyJob_badRequest() throws Exception {
        final var request = WorkerJobExecutionRequest.builder()
            .jobName(StringUtils.EMPTY)
            .jobExecutionMarker(UUID.randomUUID().toString())
            .fileStorageResource(FILE_RESOURCE)
            .build();

        final var jsonRequest = objectMapper.writeValueAsString(request);

        final var result = mvc.perform(
                post(RequestPath.BatchExecutionApi.ROOT_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest))
            .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void execute_emptyMarker_badRequest() throws Exception {
        final var request = WorkerJobExecutionRequest.builder()
            .jobName(JobName.IMPORT_CSV_TO_DB)
            .jobExecutionMarker(StringUtils.EMPTY)
            .fileStorageResource(FILE_RESOURCE)
            .build();

        final var jsonRequest = objectMapper.writeValueAsString(request);

        final var result = mvc.perform(
                post(RequestPath.BatchExecutionApi.ROOT_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest))
            .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }
}