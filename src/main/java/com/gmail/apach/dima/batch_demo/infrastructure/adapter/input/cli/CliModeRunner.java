package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli;

import com.gmail.apach.dima.batch_demo.application.input.job.JobExecutionInputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.mapper.CliMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.validation.CliRequestValidator;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.ActiveProfile;
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
public class CliModeRunner implements ApplicationRunner {

    private final CliMapper cliMapper;
    private final CliRequestValidator cliRequestValidator;
    private final JobExecutionInputPort jobExecutionInputPort;

    @Override
    public void run(ApplicationArguments args) {
        final var parameters = cliMapper.toParameters(args);
        cliRequestValidator.validate(parameters);
        jobExecutionInputPort.execute(parameters);
        System.exit(0);
    }
}
