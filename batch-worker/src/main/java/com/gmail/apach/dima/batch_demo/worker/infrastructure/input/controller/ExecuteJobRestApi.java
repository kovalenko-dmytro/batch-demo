package com.gmail.apach.dima.batch_demo.worker.infrastructure.input.controller;

import com.gmail.apach.dima.batch_demo.common.dto.BatchWorkerJobExecutionRequest;
import com.gmail.apach.dima.batch_demo.worker.infrastructure.input.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.worker.infrastructure.input.common.mapper.JobRestMapper;
import com.gmail.apach.dima.batch_demo.worker.infrastructure.input.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.worker.port.input.ExecuteJobInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = OpenApiTag.BATCH_EXECUTION_API)
@RestController
@RequestMapping(value = RequestPath.BatchExecutionApi.ROOT_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class ExecuteJobRestApi {

    private final JobRestMapper jobRestMapper;
    private final ExecuteJobInputPort executeJobInputPort;

    @PostMapping
    public ResponseEntity<Void> execute(@Valid @RequestBody BatchWorkerJobExecutionRequest request) {
        final var requestParameters = jobRestMapper.toRequestParameters(request);
        executeJobInputPort.execute(requestParameters);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
