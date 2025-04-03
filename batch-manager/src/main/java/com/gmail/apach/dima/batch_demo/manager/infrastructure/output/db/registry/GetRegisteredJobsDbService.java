package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.db.registry;

import com.gmail.apach.dima.batch_demo.manager.application.job.model.RegisteredJob;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.db.registry.mapper.RegisteredJobMapper;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.db.registry.repository.JobRegistryRepository;
import com.gmail.apach.dima.batch_demo.manager.port.output.db.GetRegisteredJobsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetRegisteredJobsDbService implements GetRegisteredJobsOutputPort {

    private final JobRegistryRepository jobRegistryRepository;
    private final RegisteredJobMapper registeredJobMapper;

    @Override
    public List<RegisteredJob> getAll() {
        final var registeredJobNames = jobRegistryRepository.getRegisteredJobs();
        return registeredJobNames.stream().map(registeredJobMapper::toRegisteredJob).toList();
    }
}
