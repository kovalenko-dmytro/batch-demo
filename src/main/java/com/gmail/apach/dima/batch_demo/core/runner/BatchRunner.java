package com.gmail.apach.dima.batch_demo.core.runner;

import com.gmail.apach.dima.batch_demo.core.executor.BatchExecutor;
import com.gmail.apach.dima.batch_demo.core.model.batch.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchRunner implements ApplicationRunner {


    private final BatchExecutor batchExecutor;

    @Override
    public void run(ApplicationArguments args) {
        final var parameters = new Parameters(args);
        batchExecutor.execute(parameters);
    }
}
