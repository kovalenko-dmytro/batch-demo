package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RegisteredJob;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.mapper.RegisteredJobMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.repository.JobRegistryRepository;
import com.gmail.apach.dima.batch_demo.port.output.db.job.GetRegisteredJobsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetRegisteredJobsAdapter implements GetRegisteredJobsOutputPort {

    private final JobRegistryRepository jobRegistryRepository;
    private final RegisteredJobMapper registeredJobMapper;

    @Override
    public List<RegisteredJob> getAll() {
        final var registeredJobNames = jobRegistryRepository.getRegisteredJobs();
        return registeredJobNames.stream().map(registeredJobMapper::toRegisteredJob).toList();
    }
}
