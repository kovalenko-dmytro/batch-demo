package com.gmail.apach.dima.batch_demo.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "com.gmail.apach.dima.batch_demo.worker",
        "com.gmail.apach.dima.batch_demo.common"
    }
)
public class BatchWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchWorkerApplication.class, args);
    }
}
