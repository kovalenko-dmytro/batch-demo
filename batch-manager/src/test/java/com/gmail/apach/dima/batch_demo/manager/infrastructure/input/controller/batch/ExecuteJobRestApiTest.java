package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch;

import com.gmail.apach.dima.batch_demo.common.model.JobExecutionInfo;
import com.gmail.apach.dima.batch_demo.common.model.JobExecutionResult;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.manager.application.job.model.BatchStatus;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExecuteJobRestApiTest {

    private final static String JOB_NAME = "import-csv-to-db";
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
            .jobName(JOB_NAME)
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

        final var workerJobExecution = JobExecutionResult.builder()
            .info(JobExecutionInfo.builder()
                .jobName(JOB_NAME)
                .jobExecutionMarker(MARKER)
                .batchStatus(BatchStatus.COMPLETED.getStatus())
                .build())
            .build();

        when(jobRestMapper.toRequestParameters(request))
            .thenReturn(requestParameters);
        when(executeJobInputPort.execute(requestParameters))
            .thenReturn(workerJobExecution);

        final var actual = executeJobRestApi.execute(request);
        assertNotNull(actual);
        assertEquals(HttpStatus.CREATED.value(), actual.getStatusCode().value());
    }
}