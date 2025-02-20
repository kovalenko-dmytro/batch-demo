package com.gmail.apach.dima.batch_demo.core.base.job.executor;

import com.gmail.apach.dima.batch_demo.application.input.JobExecutionInputPort;
import com.gmail.apach.dima.batch_demo.core.base.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobParameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchExecutor implements JobExecutionInputPort {

    private final ApplicationContext context;
    private final JobLauncher jobLauncher;
    private final MessageUtil messageUtil;

    @Override
    public void execute(RequestParameters parameters) {
        try {
            final var job = context.getBean(parameters.get(RequestParameter.JOB_NAME), Job.class);
            log.info(messageUtil.getMessage(Info.JOB_INITIALIZED, job.getName()));

            final var jobParamBuilder = parameters.builderFromRequestParameters();
            final var jobExecutionMark = job.getName().concat(Delimiter.DASH).concat(LocalDateTime.now().toString());
            jobParamBuilder.addString(JobParameter.JOB_EXEC_MARK, jobExecutionMark);
            log.info(messageUtil.getMessage(Info.JOB_EXEC_MARK, jobExecutionMark));

            final var execution = jobLauncher.run(job, jobParamBuilder.toJobParameters());
            log.info(messageUtil.getMessage(Info.JOB_FINISHED, job.getName(), execution.getStatus().name()));
        } catch (Exception e) {
            log.error(messageUtil
                .getMessage(Error.JOB_FAILED, parameters.get(RequestParameter.JOB_NAME), e.getMessage()));
        }
    }
}
