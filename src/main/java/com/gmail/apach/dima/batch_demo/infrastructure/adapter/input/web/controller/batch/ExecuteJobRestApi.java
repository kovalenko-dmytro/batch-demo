package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.batch;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.mapper.BatchRestMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.util.RestUriUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.batch.dto.JobExecutionRequest;
import com.gmail.apach.dima.batch_demo.port.input.job.JobExecutionInputPort;
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
import java.util.concurrent.CompletableFuture;

@Tag(name = OpenApiTag.BATCH_API)
@RestController
@RequestMapping(value = RequestPath.BATCH_API_ROOT_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class ExecuteJobRestApi {

    private final BatchRestMapper batchRESTMapper;
    private final JobExecutionInputPort jobExecutionInputPort;

    @PostMapping
    public ResponseEntity<Void> execute(@Valid @RequestBody JobExecutionRequest request) throws URISyntaxException {
        final var requestParameters = batchRESTMapper.toRequestParameters(request);
        CompletableFuture.runAsync(() -> jobExecutionInputPort.execute(requestParameters));
        final var location = RestUriUtil.location(requestParameters);
        return ResponseEntity.created(location).build();
    }
}
