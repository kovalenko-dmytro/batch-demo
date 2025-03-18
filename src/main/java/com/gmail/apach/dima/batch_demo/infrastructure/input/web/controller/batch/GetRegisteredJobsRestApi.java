package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch;

import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.mapper.JobRestMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto.GetRegisteredJobsResponse;
import com.gmail.apach.dima.batch_demo.port.input.job.GetRegisteredJobsInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = OpenApiTag.JOB_REGISTRY_API)
@RestController
@RequestMapping(value = RequestPath.JOB_REGISTRY_ROOT_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class GetRegisteredJobsRestApi {

    private final GetRegisteredJobsInputPort getRegisteredJobsInputPort;
    private final JobRestMapper jobRestMapper;

    @GetMapping
    public ResponseEntity<GetRegisteredJobsResponse> getRegisteredJobs() {
        final var jobs = getRegisteredJobsInputPort.getAll();
        final var response = new GetRegisteredJobsResponse(jobRestMapper.toRegisteredJobNames(jobs));
        return ResponseEntity.ok().body(response);
    }
}
