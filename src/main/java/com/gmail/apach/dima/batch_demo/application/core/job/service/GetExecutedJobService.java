package com.gmail.apach.dima.batch_demo.application.core.job.service;

import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.port.input.job.GetExecutedJobInputPort;
import com.gmail.apach.dima.batch_demo.port.output.db.job.GetExecutedJobOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetExecutedJobService implements GetExecutedJobInputPort {

    private final GetExecutedJobOutputPort getExecutedJobOutputPort;

    @Override
    public ExecutedJob get(String jobExecutionMarker) {
        return getExecutedJobOutputPort.get(jobExecutionMarker);
    }
}
