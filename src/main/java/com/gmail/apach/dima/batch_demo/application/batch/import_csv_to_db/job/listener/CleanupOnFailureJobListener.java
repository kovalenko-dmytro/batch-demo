package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.listener;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common.ImportCsvToDbTable;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import com.gmail.apach.dima.batch_demo.port.output.db.MasterTableOutputPort;
import com.gmail.apach.dima.batch_demo.port.output.db.WorkTableOutputPort;
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

    private final WorkTableOutputPort workTableOutputPort;
    private final MasterTableOutputPort masterTableOutputPort;
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
        final var insertedWorkIds = workTableOutputPort.count();
        if (insertedWorkIds > 0) {
            workTableOutputPort.truncate();
            log.info(messageUtil.getMessage(
                Info.CLEANUP_ON_FAILURE,
                jobName, jobId, ImportCsvToDbTable.WORK_TABLE.getName(), insertedWorkIds));
        }

        final var insertedMasterIds =
            (List<String>) jobExecution.getExecutionContext().get(JobExecutionContextKey.INSERTED_IDS);
        if (CollectionUtils.isNotEmpty(insertedMasterIds)) {
            masterTableOutputPort.delete(insertedMasterIds);
            log.info(messageUtil.getMessage(
                Info.CLEANUP_ON_FAILURE,
                jobName, jobId, ImportCsvToDbTable.MASTER_TABLE.getName(), insertedMasterIds));
        }
    }
}
