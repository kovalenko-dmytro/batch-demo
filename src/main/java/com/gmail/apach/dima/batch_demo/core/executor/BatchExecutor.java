package com.gmail.apach.dima.batch_demo.core.executor;

import com.gmail.apach.dima.batch_demo.core.config.message.constant.Error;
import com.gmail.apach.dima.batch_demo.core.config.message.constant.Info;
import com.gmail.apach.dima.batch_demo.core.model.batch.Parameter;
import com.gmail.apach.dima.batch_demo.core.model.batch.Parameters;
import com.gmail.apach.dima.batch_demo.core.service.message.MessageService;
import com.gmail.apach.dima.batch_demo.core.validator.structure.Validator;
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
public class BatchExecutor {

    public static final String JOB_EXEC_MARK = "JOB-EXEC-MARK";

    private final Validator<Parameters> batchParametersValidator;
    private final ApplicationContext context;
    private final JobLauncher jobLauncher;
    private final MessageService messageService;

    public void execute(Parameters parameters) {
        try {
            batchParametersValidator.validate(parameters);

            final var job = context.getBean(parameters.get(Parameter.BATCH_NAME), Job.class);
            log.info(messageService.message(Info.JOB_INITIALIZED, job.getName()));

            final var jobParamBuilder = parameters.fromProperties();
            final var jobExecutionMark = job.getName().concat(LocalDateTime.now().toString());
            jobParamBuilder.addString(JOB_EXEC_MARK, jobExecutionMark);
            log.info(messageService.message(Info.JOB_EXEC_MARK, jobExecutionMark));

            final var execution = jobLauncher.run(job, jobParamBuilder.toJobParameters());
            log.info(messageService.message(Info.JOB_FINISHED, job.getName(), execution.getStatus().name()));
        } catch (Exception e) {
            log.error(messageService.message(Error.JOB_FAILED, parameters.get(Parameter.BATCH_NAME), e.getMessage()));
        }
    }
}
