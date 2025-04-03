package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch;

import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.common.mapper.JobRestMapper;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto.ExecuteJobRequest;
import com.gmail.apach.dima.batch_demo.manager.port.input.job.ExecuteJobInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> execute(@Valid @RequestBody ExecuteJobRequest request) {
        final var requestParameters = jobRestMapper.toRequestParameters(request);
        final var status = executeJobInputPort.execute(requestParameters);
        return ResponseEntity.status(status).build();
    }
}
