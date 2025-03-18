package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.repository;

import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.repository.query.JobExecutionQuery;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.repository.rowmapper.GetExecutedJobRowMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.view.JobView;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JobExecutionRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<JobView> get(String jobExecutionMarker) {
        final var rowMapper = new GetExecutedJobRowMapper();
        jdbcTemplate.query(JobExecutionQuery.SELECT_EXECUTED_JOB, rowMapper, jobExecutionMarker);
        return Optional.ofNullable(rowMapper.getCollector().get(jobExecutionMarker));
    }
}
