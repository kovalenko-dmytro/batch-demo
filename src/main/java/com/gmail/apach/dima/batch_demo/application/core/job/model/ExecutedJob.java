package com.gmail.apach.dima.batch_demo.application.core.job.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExecutedJob implements Serializable {

    @Serial
    private static final long serialVersionUID = 123456789L;

    private String jobExecutionMarker;
    private String jobName;
    private Integer jobInstanceId;
    private Integer jobExecutionId;
    private Integer version;
    private List<JobStep> steps;
    private LocalDateTime createTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BatchStatus status;
    private ExitCode exitCode;
    private String exitMessage;
    private LocalDateTime lastUpdated;
}
