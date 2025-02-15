package com.gmail.apach.dima.batch_demo;

import com.gmail.apach.dima.batch_demo.core.base.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.RunMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BatchDemoApplication {

    public static void main(String[] args) {
        run(args, getRunMode(args));
    }

    private static void run(String[] args, String runMode) {
        switch (RunMode.from(runMode)) {
            case CLI ->
                new SpringApplicationBuilder(BatchDemoApplication.class)
                    .web(WebApplicationType.NONE)
                    .run(args);
            case WEB ->
                SpringApplication
                    .run(BatchDemoApplication.class, args);
            default ->
                throw new IllegalStateException("Run mode: <" + runMode + "> has not supported");
        }
    }

    private static String getRunMode(String[] args) {
        if (args.length == 0) {
            throw new IllegalStateException("Application arguments should be set");
        }

        final var runMode = args[0].split(Delimiter.EQUAL)[1];
        if (!RunMode.modes.contains(runMode)) {
            throw new IllegalStateException("One of any <" + RunMode.modes + "> profiles should be set");
        }
        return runMode;
    }
}
