package com.gmail.apach.dima.batch_demo;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.junit5.api.DBRider;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.ActiveProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@ActiveProfiles(ActiveProfile.TEST)
@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DBRider
@DBUnit(
    caseInsensitiveStrategy = Orthography.LOWERCASE,
    batchedStatements = true,
    allowEmptyFields = true,
    alwaysCleanBefore = true,
    alwaysCleanAfter = true
)
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

    @AfterEach
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

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
}
