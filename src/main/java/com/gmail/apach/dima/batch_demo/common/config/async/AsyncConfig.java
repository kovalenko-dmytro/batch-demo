package com.gmail.apach.dima.batch_demo.common.config.async;

import com.gmail.apach.dima.batch_demo.common.constant.ActiveProfile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

@Profile(ActiveProfile.NOT_TEST)
@Configuration
@EnableAsync
public class AsyncConfig {
}
