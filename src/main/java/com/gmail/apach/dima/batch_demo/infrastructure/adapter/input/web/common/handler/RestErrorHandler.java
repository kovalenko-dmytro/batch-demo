package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.handler;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.dto.ErrorResponse;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.infrastructure.common.exception.ApplicationServerException;
import com.gmail.apach.dima.batch_demo.infrastructure.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.util.MimeType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@SuppressWarnings("unused")
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageUtil messageUtil;

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        @NonNull MethodArgumentNotValidException ex,
        @NonNull HttpHeaders headers,
        @NonNull HttpStatusCode status,
        @NonNull WebRequest request) {
        final var errors = new ArrayList<String>();
        ex.getBindingResult().getFieldErrors()
            .forEach(fieldError -> {
                fieldError.getDefaultMessage();
                errors.add(fieldError.getField()
                    .concat(Delimiter.COLON).concat(Delimiter.SPACE)
                    .concat(fieldError.getDefaultMessage()));
            });
        ex.getBindingResult().getGlobalErrors()
            .forEach(objectError -> {
                objectError.getDefaultMessage();
                errors.add(objectError.getObjectName()
                    .concat(Delimiter.COLON).concat(Delimiter.SPACE)
                    .concat(objectError.getDefaultMessage()));
            });
        final var params = new Object[]{ex.getBindingResult().getObjectName(), errors.size()};
        final var message = messageUtil.getMessage(Error.VALIDATION_REQUEST, params);
        final var responseDTO = buildResponse(message, HttpStatus.BAD_REQUEST, errors);
        return createResponseEntity(responseDTO);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        @NonNull MissingServletRequestParameterException ex,
        @NonNull HttpHeaders headers,
        @NonNull HttpStatusCode status,
        @NonNull WebRequest request) {
        final var error = messageUtil.getMessage(Error.MISSING_REQUEST_PARAMETER, ex.getParameterName());
        final var response = buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, List.of(error));
        return createResponseEntity(response);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        @NonNull NoHandlerFoundException ex,
        @NonNull HttpHeaders headers,
        @NonNull HttpStatusCode status,
        @NonNull WebRequest request) {
        final var param = String.join(Delimiter.SPACE, ex.getHttpMethod(), ex.getRequestURL());
        final var error = messageUtil.getMessage(Error.NO_HANDLER_FOUND, param);
        final var response = buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, List.of(error));
        return createResponseEntity(response);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        @NonNull HttpRequestMethodNotSupportedException ex,
        @NonNull HttpHeaders headers,
        @NonNull HttpStatusCode status,
        @NonNull WebRequest request) {
        ex.getSupportedHttpMethods();
        final var supportedMethods = ex.getSupportedHttpMethods().stream()
            .map(HttpMethod::name)
            .collect(Collectors.joining(Delimiter.SPACE));
        final var error = messageUtil.getMessage(Error.HTTP_METHOD_NOT_ALLOWED, ex.getMethod(), supportedMethods);
        final var response = buildResponse(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED, List.of(error));
        return createResponseEntity(response);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
        @NonNull HttpMediaTypeNotSupportedException ex,
        @NonNull HttpHeaders headers,
        @NonNull HttpStatusCode status,
        @NonNull WebRequest request) {
        final var mediaTypes = ex.getSupportedMediaTypes().stream()
            .map(MimeType::toString).collect(Collectors.joining(Delimiter.SPACE));
        final var error = messageUtil.getMessage(Error.MEDIA_TYPE_NOT_SUPPORTED, ex.getContentType(), mediaTypes);
        final var response = buildResponse(ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, List.of(error));
        return createResponseEntity(response);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex) {
        final var response = buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, List.of());
        return createResponseEntity(response);
    }

    @ExceptionHandler({ApplicationServerException.class, Exception.class})
    public ResponseEntity<Object> handleGeneralExceptions(Exception ex) {
        final var error = messageUtil.getMessage(Error.INTERNAL_SERVER_ERROR_OCCURRED);
        final var response = buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, List.of(error));
        return createResponseEntity(response);
    }

    private ResponseEntity<Object> createResponseEntity(ErrorResponse response) {
        return new ResponseEntity<>(response, new HttpHeaders(), response.status());
    }

    private ErrorResponse buildResponse(String exceptionMessage, HttpStatus httpStatus, List<String> errors) {
        return ErrorResponse.builder()
            .status(httpStatus)
            .message(exceptionMessage)
            .errors(errors)
            .timestamp(LocalDateTime.now().toString())
            .build();
    }
}
