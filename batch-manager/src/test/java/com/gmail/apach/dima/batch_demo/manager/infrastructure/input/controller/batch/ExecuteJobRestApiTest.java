package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch;

import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.common.mapper.JobRestMapper;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto.ExecuteJobRequest;
import com.gmail.apach.dima.batch_demo.manager.port.input.job.ExecuteJobInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExecuteJobRestApiTest {

    private final static String IMPORT_CSV_TO_DB = "import-csv-to-db";
    private final static String MARKER = "job-execution-marker";
    private final static String FILE_RESOURCE = "file-resource";

    @InjectMocks
    private ExecuteJobRestApi executeJobRestApi;
    @Mock
    private ExecuteJobInputPort executeJobInputPort;
    @Mock
    private JobRestMapper jobRestMapper;

    @Test
    void execute_success() {
        final var request = ExecuteJobRequest.builder()
            .jobName(IMPORT_CSV_TO_DB)
            .jobExecutionMarker(MARKER)
            .fileStorageResource(FILE_RESOURCE)
            .build();
        final var requestParameters =
            new RequestParameters(
                Map.of(
                    RequestParameter.JOB_NAME, request.jobName(),
                    RequestParameter.JOB_EXECUTION_MARKER, request.jobExecutionMarker(),
                    RequestParameter.FILE_STORAGE_RESOURCE, request.fileStorageResource()
                ));

        when(jobRestMapper.toRequestParameters(request))
            .thenReturn(requestParameters);
        when(executeJobInputPort.execute(requestParameters))
            .thenReturn(HttpStatus.CREATED);

        final var actual = executeJobRestApi.execute(request);

        assertEquals(HttpStatus.CREATED.value(), actual.getStatusCode().value());
    }
}