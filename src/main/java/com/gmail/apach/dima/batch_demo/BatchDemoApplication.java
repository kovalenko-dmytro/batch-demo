package com.gmail.apach.dima.batch_demo;

import com.gmail.apach.dima.batch_demo.infrastructure.common.run.ApplicationRunHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchDemoApplication {

    public static void main(String[] args) {
        ApplicationRunHandler.run(args);
    }
}
