package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.config.worker;

import com.gmail.apach.dima.batch_demo.common.constant.Delimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchWorkerClientUriConfig {

    private final BatchWorkerClientConfigProperties properties;

    public String executeJobUri() {
        return new StringBuilder()
            .append(properties.getHost()).append(Delimiter.COLON)
            .append(properties.getPort()).append(Delimiter.SLASH)
            .append(properties.getContextPath()).append(Delimiter.SLASH)
            .append(properties.getUriPath().getExecuteJob())
            .toString();
    }
}
