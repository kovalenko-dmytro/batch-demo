package com.gmail.apach.dima.batch_demo.infrastructure.output.oss;

import com.gmail.apach.dima.batch_demo.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AwsS3ServiceTest extends AbstractIntegrationTest {

    private static final String FILE_PATH = "src/test/resources/file/oss/test.txt";
    private static final String FILE_NAME = "file";
    private static final String ORIGINAL_FILE_NAME = "test.txt";
    private static final MockMultipartFile multipartFile;

    static {
        final var file = new File(FILE_PATH);
        try {
            multipartFile = new MockMultipartFile(FILE_NAME, ORIGINAL_FILE_NAME,
                MediaType.TEXT_PLAIN_VALUE,
                Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void saveMultipartFile_success() {
        final var actual = awsS3Service.save(multipartFile);

        assertNotNull(actual);
        assertNotNull(actual.getStorageKey());
        assertNotNull(actual.getPayload());

        awsS3Service.delete(actual.getStorageKey());
    }

    @Test
    void saveFile_success() {
        final var actual = awsS3Service.save(new File(FILE_PATH));

        assertNotNull(actual);
        assertNotNull(actual.getStorageKey());
        assertNotNull(actual.getPayload());

        awsS3Service.delete(actual.getStorageKey());
    }


    @Test
    void get_success() {
        final var saved = awsS3Service.save(multipartFile);

        assertNotNull(saved);

        final var actual = awsS3Service.get(saved.getStorageKey());

        assertNotNull(actual);
        assertEquals(saved.getStorageKey(), actual.getStorageKey());
        assertNotNull(actual.getPayload());

        awsS3Service.delete(actual.getStorageKey());
    }

    @Test
    void delete_success() {
        final var saved = awsS3Service.save(multipartFile);

        assertNotNull(saved);

        awsS3Service.delete(saved.getStorageKey());
    }
}