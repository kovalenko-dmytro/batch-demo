package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.runner;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.common.mapper.CliMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.common.validation.CliRequestValidator;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.ActiveProfile;
import com.gmail.apach.dima.batch_demo.port.input.job.ExecuteJobInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Profile(ActiveProfile.CLI)
@ConditionalOnNotWebApplication
@Component
@RequiredArgsConstructor
public class CliModeRunner implements ApplicationRunner {

    private final CliMapper cliMapper;
    private final CliRequestValidator cliRequestValidator;
    private final ExecuteJobInputPort executeJobInputPort;

    @Override
    public void run(ApplicationArguments args) {
        final var parameters = cliMapper.toParameters(args);
        cliRequestValidator.validate(parameters);
        CompletableFuture.runAsync(() -> executeJobInputPort.execute(parameters)).join();
        System.exit(0);
    }
}
