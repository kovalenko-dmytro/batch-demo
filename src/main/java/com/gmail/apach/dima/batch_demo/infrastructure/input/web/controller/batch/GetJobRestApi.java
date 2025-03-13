package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch;

import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.mapper.GetJobRestMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto.GetJobResponse;
import com.gmail.apach.dima.batch_demo.port.input.job.GetJobInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = OpenApiTag.BATCH_API)
@RestController
@RequestMapping(value = RequestPath.GET_JOB_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class GetJobRestApi {

    private final GetJobInputPort getJobInputPort;
    private final GetJobRestMapper getJobRestMapper;

    @GetMapping
    public ResponseEntity<GetJobResponse> getJob(@PathVariable @NotBlank String jobExecutionMarker) {
        final var job = getJobInputPort.get(jobExecutionMarker);
        final var response = getJobRestMapper.toGetJobResponse(job);
        return ResponseEntity.ok().body(response);
    }
}
