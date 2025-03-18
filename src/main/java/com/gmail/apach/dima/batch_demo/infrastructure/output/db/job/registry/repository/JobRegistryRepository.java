package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.repository;

import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.repository.query.JobRegistryQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobRegistryRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<String> getRegisteredJobs() {
        return jdbcTemplate.query(
            JobRegistryQuery.SELECT_JOBS_NAMES,
            (rs, rowNum) -> rs.getString("job_name"));
    }

    public boolean exist(String jobName) {
        return jdbcTemplate.queryForObject(JobRegistryQuery.JOB_NAME_EXISTS, Boolean.class, jobName);
    }
}
