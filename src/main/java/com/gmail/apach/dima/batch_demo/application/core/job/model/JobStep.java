package com.gmail.apach.dima.batch_demo.application.core.job.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JobStep implements Serializable {

    @Serial
    private static final long serialVersionUID = 123456789L;

    private Integer jobExecutionId;
    private Integer version;
    private String stepName;
    private LocalDateTime createTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BatchStatus status;
    private ExitCode exitCode;
    private String exitMessage;
    private LocalDateTime lastUpdated;
}
