package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry;

import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.repository.JobRegistryRepository;
import com.gmail.apach.dima.batch_demo.port.output.db.job.RegisteredJobExistsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisteredJobExistsAdapter implements RegisteredJobExistsOutputPort {

    private final JobRegistryRepository jobRegistryRepository;

    @Override
    public boolean exist(String jobName) {
        return jobRegistryRepository.exist(jobName);
    }
}
