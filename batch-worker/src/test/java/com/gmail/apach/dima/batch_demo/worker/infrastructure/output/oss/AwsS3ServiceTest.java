package com.gmail.apach.dima.batch_demo.worker.infrastructure.output.oss;

import com.gmail.apach.dima.batch_demo.worker.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AwsS3ServiceTest extends AbstractIntegrationTest {

    private static final String FILE_PATH = "src/test/resources/file/oss/test.txt";

    @Test
    void saveFile_success() {
        final var actual = awsS3Service.save(new File(FILE_PATH));

        assertNotNull(actual);
        assertNotNull(actual.getStorageKey());
        assertNotNull(actual.getPayload());
    }

    @Test
    void get_success() {
        final var saved = awsS3Service.save(new File(FILE_PATH));

        assertNotNull(saved);

        final var actual = awsS3Service.get(saved.getStorageKey());

        assertNotNull(actual);
        assertEquals(saved.getStorageKey(), actual.getStorageKey());
        assertNotNull(actual.getPayload());
    }
}