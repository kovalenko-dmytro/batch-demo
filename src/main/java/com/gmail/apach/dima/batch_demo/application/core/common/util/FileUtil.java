package com.gmail.apach.dima.batch_demo.application.core.common.util;

import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.infrastructure.common.exception.ApplicationServerException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtil {

    public static String getFileTempPath(@NonNull String fileName, @NonNull String suffix) {
        final var prefix = FilenameUtils.removeExtension(fileName);
        try {
            final var tmpdir = Files.createTempDirectory(UUID.randomUUID().toString()).toString();
            final var preparedFileName = String.join(Delimiter.EMPTY, prefix, suffix);
            return Files.createFile(Paths.get(tmpdir, preparedFileName)).toString();
        } catch (IOException e) {
            throw new ApplicationServerException(e.getMessage());
        }
    }

    public static void deleteTempFile(@NonNull String exportFileTempPath) {
        try {
            FileUtils.deleteDirectory(Path.of(exportFileTempPath).getParent().toFile());
        } catch (IOException e) {
            throw new ApplicationServerException(e.getMessage());
        }
    }
}
