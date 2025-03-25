package com.gmail.apach.dima.batch_demo.infrastructure.input.cli.runner;

import com.gmail.apach.dima.batch_demo.common.constant.ActiveProfile;
import com.gmail.apach.dima.batch_demo.infrastructure.input.cli.common.mapper.CliParametersMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.input.cli.common.validation.CliParametersValidator;
import com.gmail.apach.dima.batch_demo.port.input.job.ExecuteJobInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(ActiveProfile.CLI)
@ConditionalOnNotWebApplication
@Component
@RequiredArgsConstructor
public class ExecuteJobCliRunner implements ApplicationRunner {

    private final CliParametersMapper cliParametersMapper;
    private final CliParametersValidator cliParametersValidator;
    private final ExecuteJobInputPort executeJobInputPort;

    @Override
    public void run(ApplicationArguments args) {
        final var parameters = cliParametersMapper.toRequestParameters(args);
        cliParametersValidator.validate(parameters);
        executeJobInputPort.execute(parameters);
        System.exit(0);
    }
}
