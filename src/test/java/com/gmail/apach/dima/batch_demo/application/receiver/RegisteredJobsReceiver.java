package com.gmail.apach.dima.batch_demo.application.receiver;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RegisteredJob;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegisteredJobsReceiver {

    public static List<RegisteredJob> registeredJobs() {
        return List.of(
            RegisteredJob.builder()
                .name("job")
                .build(),
            RegisteredJob.builder()
                .name("another-job")
                .build()
        );
    }

    public static List<String> registeredJobsNames() {
        return List.of("job", "another-job");
    }
}
