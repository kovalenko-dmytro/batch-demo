package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.job.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JobView {

    private String jobExecutionMarker;
    private String jobName;
    private Integer jobInstanceId;
    private Integer jobExecutionId;
    private Integer version;
    private List<JobStepView> steps;
    private LocalDateTime createTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String exitCode;
    private String exitMessage;
    private LocalDateTime lastUpdated;
}
