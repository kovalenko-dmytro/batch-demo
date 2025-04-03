package com.gmail.apach.dima.batch_demo.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "com.gmail.apach.dima.batch_demo.manager",
        "com.gmail.apach.dima.batch_demo.common"
    }
)
public class BatchManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchManagerApplication.class, args);
    }
}
