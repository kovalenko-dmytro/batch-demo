package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.batch;

import com.gmail.apach.dima.batch_demo.application.input.JobExecutionInputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.batch.dto.JobExecutionRequest;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.mapper.BatchRestMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.util.RestUriUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@Tag(name = "Batch API")
@RestController
@RequestMapping(value = RequestPath.BATCH_API_ROOT_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class JobExecutionRestAdapter {

    private final BatchRestMapper batchRESTMapper;
    private final JobExecutionInputPort jobExecutionInputPort;

    @PostMapping
    public ResponseEntity<Void> execute(
        @Valid @RequestBody JobExecutionRequest request,
        HttpServletRequest httpRequest
    ) throws URISyntaxException {
        final var requestParameters = batchRESTMapper.toRequestParameters(request);
        jobExecutionInputPort.execute(requestParameters);
        final var createdUri = RestUriUtil.buildCreatedUri(httpRequest, requestParameters);
        return ResponseEntity.created(createdUri).build();
    }
}
