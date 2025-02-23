package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.batch;

import com.gmail.apach.dima.batch_demo.application.input.job.JobExecutionInputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.batch.dto.JobExecutionRequest;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.mapper.BatchRestMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.util.RestUriUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@Tag(name = OpenApiTag.BATCH_API)
@RestController
@RequestMapping(value = RequestPath.BATCH_API_ROOT_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class JobExecutionRestAdapter {

    private final BatchRestMapper batchRESTMapper;
    private final JobExecutionInputPort jobExecutionInputPort;

    @PostMapping
    public ResponseEntity<Void> execute(@Valid @RequestBody JobExecutionRequest request) throws URISyntaxException {
        final var requestParameters = batchRESTMapper.toRequestParameters(request);
        jobExecutionInputPort.execute(requestParameters);
        final var location = RestUriUtil.location(requestParameters);
        return ResponseEntity.created(location).build();
    }
}
