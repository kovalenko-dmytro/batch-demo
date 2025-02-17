package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.trancate_work.task;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkExampleOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class TruncateWorkExampleTask implements Tasklet {

    private final WorkExampleOutputPort workExampleOutputPort;

    @NonNull
    @Override
    public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext context) {
        workExampleOutputPort.truncate();
        return RepeatStatus.FINISHED;
    }
}
