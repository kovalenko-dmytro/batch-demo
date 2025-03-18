package com.gmail.apach.dima.batch_demo.application.core.job.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisteredJob implements Serializable {

    @Serial
    private static final long serialVersionUID = 123456789L;

    private String name;
}
