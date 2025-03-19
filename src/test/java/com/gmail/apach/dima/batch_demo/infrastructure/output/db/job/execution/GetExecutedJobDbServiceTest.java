package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution;

import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Resource;
import com.gmail.apach.dima.batch_demo.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.mapper.ExecutedJobMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.repository.JobExecutionRepository;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.view.JobView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetExecutedJobDbServiceTest {

    private static final String EXEC_UNIQUE_MARK = "5a8d68c8-2f28-4b53-ac5a-2db586512440";

    @InjectMocks
    private GetExecutedJobDbService getExecutedJobDbService;
    @Mock
    private JobExecutionRepository jobExecutionRepository;
    @Mock
    private ExecutedJobMapper executedJobMapper;
    @Mock
    private MessageUtil messageUtil;

    @Test
    void getJob_success() {
        final var jobView = JobView.builder()
            .jobExecutionMarker(EXEC_UNIQUE_MARK)
            .build();
        final var executedJob = ExecutedJob.builder()
            .jobExecutionMarker(EXEC_UNIQUE_MARK)
            .build();

        when(jobExecutionRepository.get(EXEC_UNIQUE_MARK)).thenReturn(Optional.of(jobView));
        when(executedJobMapper.toExecutedJob(jobView)).thenReturn(executedJob);

        final var actual = getExecutedJobDbService.get(EXEC_UNIQUE_MARK);

        assertNotNull(actual);
        assertEquals(EXEC_UNIQUE_MARK, actual.getJobExecutionMarker());
    }

    @Test
    void getJob_fail() {
        when(jobExecutionRepository.get(EXEC_UNIQUE_MARK)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> getExecutedJobDbService.get(EXEC_UNIQUE_MARK));
        verify(messageUtil, times(1))
            .getMessage(
                Error.RESOURCE_NOT_FOUND,
                Resource.JOB.getName(),
                Resource.Attribute.JOB_EXEC_MARK.getName(),
                EXEC_UNIQUE_MARK);
    }
}