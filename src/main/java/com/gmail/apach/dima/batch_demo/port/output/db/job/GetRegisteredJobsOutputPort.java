package com.gmail.apach.dima.batch_demo.port.output.db.job;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RegisteredJob;

import java.util.List;

public interface GetRegisteredJobsOutputPort {
    List<RegisteredJob> get();
}
