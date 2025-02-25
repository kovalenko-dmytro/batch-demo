package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.file;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.port.input.file.DownloadFileInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = OpenApiTag.FILE_API)
@RestController
@RequestMapping(value = RequestPath.FILE_API_BY_STORAGE_KEY_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class DownloadFileRestAdapter {

    private final DownloadFileInputPort downloadFileInputPort;

    @GetMapping
    public ResponseEntity<byte[]> download(@PathVariable("fileStorageKey") String fileStorageKey) {
        final var storedResource = downloadFileInputPort.download(fileStorageKey);
        final var headerValues = "attachment; filename=\"" + storedResource.getFileName() + "\"";
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
            .body(storedResource.getPayload());
    }
}
