package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.repository;

import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.repository.query.GetJobQuery;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.repository.rowmapper.GetJobRowMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.view.JobView;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JobExecutionRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<JobView> get(String jobExecutionMarker) {
        final var rowMapper = new GetJobRowMapper();
        jdbcTemplate.query(GetJobQuery.QUERY, rowMapper, jobExecutionMarker);
        final var collector = rowMapper.getCollector();
        return Optional.ofNullable(collector.get(jobExecutionMarker));
    }
}
