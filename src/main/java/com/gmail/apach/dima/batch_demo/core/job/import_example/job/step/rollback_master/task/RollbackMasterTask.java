package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.rollback_master.task;

import com.gmail.apach.dima.batch_demo.application.output.db.MasterExampleOutputPort;
import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class RollbackMasterTask implements Tasklet {

    private final MasterExampleOutputPort masterExampleOutputPort;
    private final MessageUtil messageUtil;

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public RepeatStatus execute(@NonNull StepContribution contribution, @NonNull ChunkContext context) {
        final var jobExecution = contribution
            .getStepExecution().getJobExecution();
        final var insertedIds = (List<String>) jobExecution
            .getExecutionContext()
            .get(JobExecutionContextKey.INSERTED_IDS, List.class, Collections.emptyList());

        if (CollectionUtils.isNotEmpty(insertedIds)) {
            masterExampleOutputPort.delete(insertedIds);

            log.info(messageUtil.getMessage(
                Info.JOB_STEP_ROLLBACK_PROCESSED,
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getJobInstance().getInstanceId(),
                contribution.getStepExecution().getStepName(),
                insertedIds.size()));
        }

        return RepeatStatus.FINISHED;
    }
}
