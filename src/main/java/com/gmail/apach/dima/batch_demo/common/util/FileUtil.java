package com.gmail.apach.dima.batch_demo.common.util;

import com.gmail.apach.dima.batch_demo.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.common.constant.Extension;
import com.gmail.apach.dima.batch_demo.common.exception.ApplicationServerException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.lingala.zip4j.ZipFile;
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

    public static String createTempFile(@NonNull String fileName, @NonNull String extension) {
        final var prefix = FilenameUtils.removeExtension(fileName);
        try {
            final var tmpdir = Files.createTempDirectory(UUID.randomUUID().toString()).toString();
            final var preparedFileName = String.join(Delimiter.EMPTY, prefix, extension);
            return Files.createFile(Paths.get(tmpdir, preparedFileName)).toString();
        } catch (IOException e) {
            throw new ApplicationServerException(e.getMessage());
        }
    }

    public static void deleteTempFile(@NonNull String tempFilePath) {
        try {
            FileUtils.deleteDirectory(Path.of(tempFilePath).getParent().toFile());
        } catch (IOException e) {
            throw new ApplicationServerException(e.getMessage());
        }
    }

    public static void deleteTempDir(@NonNull String tempPath) {
        try {
            FileUtils.deleteDirectory(Path.of(tempPath).toFile());
        } catch (IOException e) {
            throw new ApplicationServerException(e.getMessage());
        }
    }

    public static String unpackZip(byte[] payload) throws IOException {
        final var zipFileTempPath = createTempFile(UUID.randomUUID().toString(), Extension.ZIP);
        Files.write(Path.of(zipFileTempPath), payload);

        final var tempDirPath = Files.createTempDirectory(UUID.randomUUID().toString()).toString();
        final var zipFile = new ZipFile(zipFileTempPath);
        zipFile.extractAll(tempDirPath);
        zipFile.close();
        FileUtil.deleteTempFile(zipFileTempPath);
        return tempDirPath;
    }
}
