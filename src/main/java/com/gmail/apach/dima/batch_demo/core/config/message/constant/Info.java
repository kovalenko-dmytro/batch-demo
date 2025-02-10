package com.gmail.apach.dima.batch_demo.core.config.message.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Info {

    BATCH_EXECUTOR_STARTED("info.batch.executor.started"),
    BATCH_PARAMETERS_VALIDATED("info.batch.parameters.validated"),
    JOB_INITIALIZED("info.job.initialized"),
    JOB_EXEC_MARK("info.job.execution.mark"),
    JOB_STEP_READER_STARTED("info.job.step.reader.started"),
    JOB_STEP_READER_COMPLETED("info.job.step.reader.completed"),
    JOB_STEP_PROCESSOR_STARTED("info.job.step.processor.started"),
    JOB_STEP_PROCESSOR_PROCESSED("info.job.step.processor.processed"),
    JOB_STEP_PROCESSOR_COMPLETED("info.job.step.processor.completed"),
    JOB_STEP_WRITER_STARTED("info.job.step.writer.started"),
    JOB_STEP_WRITER_PROCESSED("info.job.step.writer.processed"),
    JOB_STEP_WRITER_COMPLETED("info.job.step.writer.completed"),
    JOB_FINISHED("info.job.finished");

    private final String key;
}
