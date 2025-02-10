package com.gmail.apach.dima.batch_demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BatchDemoApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BatchDemoApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
	}
}
