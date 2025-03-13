package com.gmail.apach.dima.batch_demo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Info {

    JOB_SHELL_COMMAND_STARTED("info.job.shell.command.started"),
    JOB_INITIALIZED("info.job.initialized"),
    JOB_STEP_STARTED("info.job.step.started"),
    JOB_STEP_COMPLETED("info.job.step.completed"),
    CLEANUP_ON_FAILURE("info.job.cleanup.on.failure"),
    JOB_STEP_EXPORT_FILE_UPLOADED("info.job.export.file.uploaded"),
    JOB_FINISHED("info.job.finished");

    private final String key;
}
