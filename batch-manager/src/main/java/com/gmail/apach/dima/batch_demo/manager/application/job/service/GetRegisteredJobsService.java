package com.gmail.apach.dima.batch_demo.manager.application.job.service;

import com.gmail.apach.dima.batch_demo.manager.application.job.model.RegisteredJob;
import com.gmail.apach.dima.batch_demo.manager.port.input.job.GetRegisteredJobsInputPort;
import com.gmail.apach.dima.batch_demo.manager.port.output.db.GetRegisteredJobsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRegisteredJobsService implements GetRegisteredJobsInputPort {

    private final GetRegisteredJobsOutputPort getRegisteredJobsOutputPort;

    @Override
    public List<RegisteredJob> getAll() {
        return getRegisteredJobsOutputPort.getAll();
    }
}
