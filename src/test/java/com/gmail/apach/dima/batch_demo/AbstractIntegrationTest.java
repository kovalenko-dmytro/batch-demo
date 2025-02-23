package com.gmail.apach.dima.batch_demo;

import com.gmail.apach.dima.batch_demo.core.base.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.core.base.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.oss.AwsS3Adapter;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest(classes = BatchDemoApplication.class)
@SpringBatchTest
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class
})
public abstract class AbstractIntegrationTest {

    private static final String BUCKET_NAME = "test-bucket";

    protected static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
        new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    protected static final LocalStackContainer LOCAL_STACK_CONTAINER =
        new LocalStackContainer(DockerImageName.parse("localstack/localstack"))
            .withCopyFileToContainer(
                MountableFile.forClasspathResource("script/init-s3-bucket.sh"),
                "/etc/localstack/init/ready.d/init-s3-bucket.sh")
            .withServices(LocalStackContainer.Service.S3)
            .waitingFor(Wait.forLogMessage(".*Executed init-s3-bucket.sh.*", 1));

    static {
        POSTGRESQL_CONTAINER.start();
        LOCAL_STACK_CONTAINER.start();
    }

    @Autowired
    protected JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    protected JobRepositoryTestUtils jobRepositoryTestUtils;
    @Autowired
    private AwsS3Adapter awsS3Adapter;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private ApplicationContext context;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("DB_URL", POSTGRESQL_CONTAINER.getJdbcUrl());
        System.setProperty("DB_USERNAME", POSTGRESQL_CONTAINER.getUsername());
        System.setProperty("DB_PASSWORD", POSTGRESQL_CONTAINER.getPassword());

        System.setProperty("spring.cloud.aws.credentials.access-key", LOCAL_STACK_CONTAINER.getAccessKey());
        System.setProperty("spring.cloud.aws.credentials.secret-key", LOCAL_STACK_CONTAINER.getSecretKey());
        System.setProperty("spring.cloud.aws.s3.region", LOCAL_STACK_CONTAINER.getRegion());
        System.setProperty("spring.cloud.aws.s3.endpoint", String.valueOf(LOCAL_STACK_CONTAINER.getEndpoint()));
        System.setProperty("aws.s3.bucket-name", BUCKET_NAME);
    }

    @AfterEach
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    protected JobExecution launchJob(String jobName, JobParameters jobParameters) throws Exception {
        final var job = context.getBean(jobName, Job.class);
        return jobLauncher.run(job, jobParameters);
    }

    protected StoredResource uploadFile(String filePath) throws IOException {
        final var file = new File(filePath);
        final var originalFileName = filePath.substring(filePath.lastIndexOf(Delimiter.SLASH));
        final var fileName = FilenameUtils.removeExtension(originalFileName);
        final var multipartFile = new MockMultipartFile(
                fileName,
                originalFileName,
                MediaType.TEXT_PLAIN_VALUE,
                Files.readAllBytes(file.toPath()));
        return awsS3Adapter.save(multipartFile);
    }
}
