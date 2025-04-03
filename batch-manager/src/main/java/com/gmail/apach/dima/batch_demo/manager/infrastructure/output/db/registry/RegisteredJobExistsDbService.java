package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.db.registry;

import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.db.registry.repository.JobRegistryRepository;
import com.gmail.apach.dima.batch_demo.manager.port.output.db.RegisteredJobExistsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisteredJobExistsDbService implements RegisteredJobExistsOutputPort {

    private final JobRegistryRepository jobRegistryRepository;

    @Override
    public boolean exist(String jobName) {
        return jobRegistryRepository.exist(jobName);
    }
}
