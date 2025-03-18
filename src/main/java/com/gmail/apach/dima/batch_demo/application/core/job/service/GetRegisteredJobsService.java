package com.gmail.apach.dima.batch_demo.application.core.job.service;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RegisteredJob;
import com.gmail.apach.dima.batch_demo.port.input.job.GetRegisteredJobsInputPort;
import com.gmail.apach.dima.batch_demo.port.output.db.job.GetRegisteredJobsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetRegisteredJobsService implements GetRegisteredJobsInputPort {

    private final GetRegisteredJobsOutputPort getRegisteredJobsOutputPort;

    @Override
    public List<RegisteredJob> getAll() {
        return getRegisteredJobsOutputPort.getAll();
    }
}
