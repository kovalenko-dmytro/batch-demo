package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job;

import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Resource;
import com.gmail.apach.dima.batch_demo.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.mapper.JobMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.repository.JobExecutionRepository;
import com.gmail.apach.dima.batch_demo.port.output.db.GetJobOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetJobAdapter implements GetJobOutputPort {

    private final JobExecutionRepository jobExecutionRepository;
    private final JobMapper jobMapper;
    private final MessageUtil messageUtil;

    @Override
    public ExecutedJob get(String jobExecutionMarker) {
        final var job = jobExecutionRepository.get(jobExecutionMarker)
            .orElseThrow(() -> new ResourceNotFoundException(buildErrorMessage(jobExecutionMarker)));
        return jobMapper.toExecutedJob(job);
    }

    private String buildErrorMessage(String jobExecutionMarker) {
        return messageUtil.getMessage(
            Error.RESOURCE_NOT_FOUND,
            Resource.JOB.getName(), Resource.Attribute.JOB_EXEC_MARK.getName(), jobExecutionMarker);
    }
}
