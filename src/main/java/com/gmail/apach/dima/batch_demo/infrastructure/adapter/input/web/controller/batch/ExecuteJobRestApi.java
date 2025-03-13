package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.batch;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.mapper.ExecuteJobRestMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.util.RestUriUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.batch.dto.ExecuteJobRequest;
import com.gmail.apach.dima.batch_demo.port.input.job.ExecuteJobInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Tag(name = OpenApiTag.BATCH_API)
@RestController
@RequestMapping(value = RequestPath.BATCH_API_ROOT_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class ExecuteJobRestApi {

    private final ExecuteJobRestMapper executeJobRestMapper;
    private final ExecuteJobInputPort executeJobInputPort;

    @PostMapping
    public ResponseEntity<Void> execute(@Valid @RequestBody ExecuteJobRequest request) {
        final var requestParameters = executeJobRestMapper.toRequestParameters(request);
        CompletableFuture.runAsync(() -> executeJobInputPort.execute(requestParameters));
        final var location = RestUriUtil.location(requestParameters);
        return ResponseEntity.created(location).build();
    }
}
