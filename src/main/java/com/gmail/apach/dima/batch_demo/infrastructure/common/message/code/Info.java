package com.gmail.apach.dima.batch_demo.infrastructure.common.message.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Info {

    BATCH_PARAMETERS_VALIDATED("info.batch.parameters.validated"),
    JOB_INITIALIZED("info.job.initialized"),
    JOB_EXEC_MARK("info.job.execution.mark"),
    JOB_STEP_STARTED("info.job.step.started"),
    JOB_STEP_PROCESSED("info.job.step.processed"),
    JOB_READER_FILE_RESOURCE_READ("info.job.step.reader.file.resource.read"),
    JOB_STEP_COMPLETED("info.job.step.completed"),
    JOB_STEP_READER_STARTED("info.job.step.reader.started"),
    JOB_STEP_READER_PROCESSED("info.job.step.reader.processed"),
    JOB_STEP_READER_COMPLETED("info.job.step.reader.completed"),
    JOB_STEP_PROCESSOR_STARTED("info.job.step.processor.started"),
    JOB_STEP_PROCESSOR_COMPLETED("info.job.step.processor.completed"),
    JOB_STEP_WRITER_STARTED("info.job.step.writer.started"),
    JOB_STEP_WRITER_PROCESSED("info.job.step.writer.processed"),
    JOB_STEP_WRITER_COMPLETED("info.job.step.writer.completed"),
    JOB_STEP_ROLLBACK_PROCESSED("info.job.step.rollback.processed"),
    CLEANUP_ON_FAILURE("info.job.cleanup.on.failure"),
    JOB_FINISHED("info.job.finished");

    private final String key;
}
