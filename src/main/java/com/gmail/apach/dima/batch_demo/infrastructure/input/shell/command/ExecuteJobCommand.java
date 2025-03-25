package com.gmail.apach.dima.batch_demo.infrastructure.input.shell.command;

import com.gmail.apach.dima.batch_demo.common.constant.ActiveProfile;
import com.gmail.apach.dima.batch_demo.common.constant.Info;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.input.shell.common.constant.Command;
import com.gmail.apach.dima.batch_demo.infrastructure.input.shell.common.constant.CommandOption;
import com.gmail.apach.dima.batch_demo.infrastructure.input.shell.common.dto.ExecuteJobOptionsWrapper;
import com.gmail.apach.dima.batch_demo.infrastructure.input.shell.common.mapper.CommandOptionsMapper;
import com.gmail.apach.dima.batch_demo.port.input.job.ExecuteJobInputPort;
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

    private final CommandOptionsMapper commandOptionsMapper;
    private final ExecuteJobInputPort executeJobInputPort;
    private final MessageUtil messageUtil;

    @ShellMethod(key = Command.EXECUTE_JOB)
    public String executeJob(
        @ShellOption(
            value = CommandOption.JobName.NAME,
            help = CommandOption.JobName.HELP) @NotBlank String jobName,
        @ShellOption(
            value = CommandOption.JobExecutionMarker.NAME,
            help = CommandOption.JobExecutionMarker.HELP,
            defaultValue = ShellOption.NULL) String jobExecutionMarker,
        @ShellOption(
            value = CommandOption.FileStorageResource.NAME,
            help = CommandOption.FileStorageResource.HELP,
            defaultValue = ShellOption.NULL) String fileStorageResource
    ) {
        final var wrapper = new ExecuteJobOptionsWrapper(jobName, jobExecutionMarker, fileStorageResource);
        final var requestParameters = commandOptionsMapper.toRequestParameters(wrapper);
        CompletableFuture.runAsync(() -> executeJobInputPort.execute(requestParameters));
        return messageUtil.getMessage(Info.JOB_SHELL_COMMAND_STARTED);
    }
}
