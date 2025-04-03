package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.db.execution;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Resource;
import com.gmail.apach.dima.batch_demo.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.manager.application.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.db.execution.mapper.ExecutedJobMapper;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.db.execution.repository.JobExecutionRepository;
import com.gmail.apach.dima.batch_demo.manager.port.output.db.GetExecutedJobOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetExecutedJobDbService implements GetExecutedJobOutputPort {

    private final JobExecutionRepository jobExecutionRepository;
    private final ExecutedJobMapper executedJobMapper;
    private final MessageUtil messageUtil;

    @Override
    public ExecutedJob get(String jobExecutionMarker) {
        final var job = jobExecutionRepository.get(jobExecutionMarker)
            .orElseThrow(() -> new ResourceNotFoundException(buildErrorMessage(jobExecutionMarker)));
        return executedJobMapper.toExecutedJob(job);
    }

    private String buildErrorMessage(String jobExecutionMarker) {
        return messageUtil.getMessage(
            Error.RESOURCE_NOT_FOUND,
            Resource.JOB.getName(), Resource.Attribute.JOB_EXEC_MARK.getName(), jobExecutionMarker);
    }
}
