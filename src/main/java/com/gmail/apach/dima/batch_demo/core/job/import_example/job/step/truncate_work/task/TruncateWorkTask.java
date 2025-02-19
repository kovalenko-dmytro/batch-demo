package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.truncate_work.task;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkExampleOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class TruncateWorkTask implements Tasklet {

    private final WorkExampleOutputPort workExampleOutputPort;

    @NonNull
    @Override
    public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext context) {
        workExampleOutputPort.truncate();
        return RepeatStatus.FINISHED;
    }
}
