package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.repository.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JobRegistryQuery {
    public static final String SELECT_JOBS_NAMES =
        "   SELECT                   " +
        "       job_name             " +
        "   FROM                     " +
        "       job_registry         ";

    public static final String JOB_NAME_EXISTS =
        "   SELECT                           " +
        "       EXISTS (                     " +
        "           SELECT 1                 " +
        "           FROM job_registry        " +
        "           WHERE job_name = ?       " +
        "       )                            ";
}
