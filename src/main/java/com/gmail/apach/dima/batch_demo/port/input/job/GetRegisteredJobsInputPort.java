package com.gmail.apach.dima.batch_demo.port.input.job;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RegisteredJob;

import java.util.List;

public interface GetRegisteredJobsInputPort {
    List<RegisteredJob> getAll();
}
