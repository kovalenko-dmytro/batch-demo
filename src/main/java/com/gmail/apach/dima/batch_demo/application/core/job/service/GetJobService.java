package com.gmail.apach.dima.batch_demo.application.core.job.service;

import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.port.input.job.GetJobInputPort;
import com.gmail.apach.dima.batch_demo.port.output.db.GetJobOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetJobService implements GetJobInputPort {

    private final GetJobOutputPort getJobOutputPort;

    @Override
    public ExecutedJob get(String jobExecutionMarker) {
        return getJobOutputPort.get(jobExecutionMarker);
    }
}
