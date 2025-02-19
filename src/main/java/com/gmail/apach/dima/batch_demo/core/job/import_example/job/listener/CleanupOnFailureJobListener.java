package com.gmail.apach.dima.batch_demo.core.job.import_example.job.listener;

import com.gmail.apach.dima.batch_demo.application.output.db.MasterExampleOutputPort;
import com.gmail.apach.dima.batch_demo.application.output.db.WorkExampleOutputPort;
import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CleanupOnFailureJobListener implements JobExecutionListener {

    private final WorkExampleOutputPort workExampleOutputPort;
    private final MasterExampleOutputPort masterExampleOutputPort;
    private final MessageUtil messageUtil;

    @Override
    @SuppressWarnings("unchecked")
    public void afterJob(JobExecution jobExecution) {
        final var allFailureExceptions = jobExecution.getAllFailureExceptions();
        if (CollectionUtils.isEmpty(allFailureExceptions)) {
            return;
        }

        final var jobName = jobExecution.getJobInstance().getJobName();
        final var jobId = jobExecution.getJobInstance().getInstanceId();
        final var insertedWorkIds = workExampleOutputPort.count();
        if (insertedWorkIds > 0) {
            workExampleOutputPort.truncate();
            log.info(messageUtil.getMessage(
                Info.CLEANUP_ON_FAILURE, jobName, jobId, "work_examples", insertedWorkIds));
        }

        final var insertedMasterIds =
            (List<String>) jobExecution.getExecutionContext().get(JobExecutionContextKey.INSERTED_IDS);
        if (CollectionUtils.isNotEmpty(insertedMasterIds)) {
            masterExampleOutputPort.delete(insertedMasterIds);
            log.info(messageUtil.getMessage(
                Info.CLEANUP_ON_FAILURE, jobName, jobId, "master_examples", insertedMasterIds));
        }
    }
}
