package com.gmail.apach.dima.batch_demo.core.base.job.executor;

import com.gmail.apach.dima.batch_demo.application.input.JobExecutionInputPort;
import com.gmail.apach.dima.batch_demo.core.base.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.core.base.common.constant.JobParameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.Parameter;
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
    private final MessageUtil messageService;

    @Override
    public void execute(RequestParameters parameters) {
        try {
            final var job = context.getBean(parameters.get(Parameter.BATCH_NAME), Job.class);
            log.info(messageService.getMessage(Info.JOB_INITIALIZED, job.getName()));

            final var jobParamBuilder = parameters.fromProperties();
            final var jobExecutionMark = job.getName().concat(Delimiter.DASH).concat(LocalDateTime.now().toString());
            jobParamBuilder.addString(JobParameter.JOB_EXEC_MARK, jobExecutionMark);
            log.info(messageService.getMessage(Info.JOB_EXEC_MARK, jobExecutionMark));

            final var execution = jobLauncher.run(job, jobParamBuilder.toJobParameters());
            log.info(messageService.getMessage(Info.JOB_FINISHED, job.getName(), execution.getStatus().name()));
        } catch (Exception e) {
            log.error(messageService.getMessage(Error.JOB_FAILED, parameters.get(Parameter.BATCH_NAME), e.getMessage()));
        }
    }
}
