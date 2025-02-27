package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.file;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.mapper.FileRestMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.swagger.OpenApiTag;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.file.dto.FileResponse;
import com.gmail.apach.dima.batch_demo.port.input.file.UploadFileInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = OpenApiTag.FILE_API)
@RestController
@RequestMapping(value = RequestPath.FILE_API_ROOT_PATH)
@RequiredArgsConstructor
@Validated
@SuppressWarnings("unused")
public class UploadFileRestAdapter {

    private final UploadFileInputPort uploadFileInputPort;
    private final FileRestMapper fileRestMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileResponse> upload(@RequestParam(value = "file") MultipartFile file) {
        final var storedResource = uploadFileInputPort.upload(file);
        final var response = fileRestMapper.toFileResponse(storedResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
