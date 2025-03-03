package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.command;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.common.constant.ShellArgument;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.common.constant.ShellCommand;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.common.dto.ExecuteJobShellArgsWrapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.common.mapper.ShellOptionsMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.ActiveProfile;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import com.gmail.apach.dima.batch_demo.port.input.job.JobExecutionInputPort;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.concurrent.CompletableFuture;

@Profile(ActiveProfile.SHELL)
@ShellComponent
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ExecuteJobCommand {

    private final ShellOptionsMapper shellOptionsMapper;
    private final JobExecutionInputPort jobExecutionInputPort;
    private final MessageUtil messageUtil;

    @ShellMethod(key = ShellCommand.EXECUTE_JOB)
    public String executeJob(
        @ShellOption(
            value = ShellArgument.JobName.NAME,
            help = ShellArgument.JobName.HELP) @NotBlank String jobName,
        @ShellOption(
            value = ShellArgument.FileStorageResource.NAME,
            help = ShellArgument.FileStorageResource.HELP,
            defaultValue = ShellOption.NULL) String fileStorageResource
    ) {
        final var wrapper = new ExecuteJobShellArgsWrapper(jobName, fileStorageResource);
        final var requestParameters = shellOptionsMapper.toParameters(wrapper);
        CompletableFuture.runAsync(() -> jobExecutionInputPort.execute(requestParameters));
        return messageUtil.getMessage(Info.JOB_SHELL_COMMAND_STARTED);
    }
}
