package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.repository.rowmapper;

import com.gmail.apach.dima.batch_demo.infrastructure.output.db.common.converter.JdbcColumnConverter;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.view.JobStepView;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.view.JobView;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetExecutedJobRowMapper implements RowMapper<JobView> {

    @Getter
    private final Map<String, JobView> collector = new HashMap<>();

    @Override
    public JobView mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        final var jobExecutionMarker = rs.getString("job_execution_marker");
        var jobView = collector.get(jobExecutionMarker);

        if (Objects.isNull(jobView)) {
            jobView = mapRowToJobView(rs);
            collector.put(jobExecutionMarker, jobView);
        }

        jobView.getSteps().add(mapRowToStepView(rs));

        return null;
    }

    private JobView mapRowToJobView(ResultSet rs) throws SQLException {
        return JobView.builder()
            .jobExecutionMarker(rs.getString("job_execution_marker"))
            .jobExecutionId(rs.getInt("job_execution_id"))
            .version(rs.getInt("batch_job_execution_version"))
            .jobInstanceId(rs.getInt("job_instance_id"))
            .jobName(rs.getString("job_name"))
            .steps(new ArrayList<>())
            .createTime(JdbcColumnConverter.toLocalDateTime(rs, "batch_job_execution_create_time"))
            .startTime(JdbcColumnConverter.toLocalDateTime(rs, "batch_job_execution_start_time"))
            .endTime(JdbcColumnConverter.toLocalDateTime(rs, "batch_job_execution_end_time"))
            .status(rs.getString("batch_job_execution_status"))
            .exitCode(rs.getString("batch_job_execution_exit_code"))
            .exitMessage(rs.getString("batch_job_execution_exit_message"))
            .lastUpdated(JdbcColumnConverter.toLocalDateTime(rs, "batch_job_execution_last_updated"))
            .build();
    }

    private JobStepView mapRowToStepView(ResultSet rs) throws SQLException {
        return JobStepView.builder()
            .jobExecutionId(rs.getInt("job_execution_id"))
            .version(rs.getInt("batch_step_execution_version"))
            .stepName(rs.getString("step_name"))
            .createTime(JdbcColumnConverter.toLocalDateTime(rs, "batch_step_execution_create_time"))
            .startTime(JdbcColumnConverter.toLocalDateTime(rs, "batch_step_execution_start_time"))
            .endTime(JdbcColumnConverter.toLocalDateTime(rs, "batch_step_execution_end_time"))
            .status(rs.getString("batch_step_execution_status"))
            .exitCode(rs.getString("batch_step_execution_exit_code"))
            .exitMessage(rs.getString("batch_step_execution_exit_message"))
            .lastUpdated(JdbcColumnConverter.toLocalDateTime(rs, "batch_step_execution_last_updated"))
            .build();
    }
}
