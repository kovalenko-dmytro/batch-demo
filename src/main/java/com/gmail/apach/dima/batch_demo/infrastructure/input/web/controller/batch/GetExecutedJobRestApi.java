package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch;

import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.mapper.JobRestMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto.GetExecutedJobResponse;
import com.gmail.apach.dima.batch_demo.port.input.job.GetExecutedJobInputPort;
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
public class GetExecutedJobRestApi {

    private final GetExecutedJobInputPort getExecutedJobInputPort;
    private final JobRestMapper jobRestMapper;

    @GetMapping
    public ResponseEntity<GetExecutedJobResponse> getJob(
        @PathVariable("job-execution-marker") @NotBlank String jobExecutionMarker
    ) {
        final var job = getExecutedJobInputPort.get(jobExecutionMarker);
        final var response = jobRestMapper.toGetExecutedJobResponse(job);
        return ResponseEntity.ok().body(response);
    }
}
