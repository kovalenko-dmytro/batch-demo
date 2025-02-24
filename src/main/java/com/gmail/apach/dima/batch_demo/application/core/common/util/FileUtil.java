package com.gmail.apach.dima.batch_demo.application.core.common.util;

import com.gmail.apach.dima.batch_demo.infrastructure.common.exception.ApplicationServerException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtil {

    public static String getFileTempPath(@NonNull String fileName, @NonNull String suffix) {
        final var prefix = FilenameUtils.removeExtension(fileName);
        try {
            final var tmpdir = Files.createTempDirectory(UUID.randomUUID().toString());
            return Files.createTempFile(tmpdir, prefix, suffix).toString();
        } catch (IOException e) {
            throw new ApplicationServerException(e.getMessage());
        }
    }

    public static void deleteTempFile(@NonNull String exportFileTempPath) {
        try {
            Files.deleteIfExists(Path.of(exportFileTempPath));
        } catch (IOException e) {
            throw new ApplicationServerException(e.getMessage());
        }
    }
}
