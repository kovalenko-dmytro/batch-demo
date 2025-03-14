package com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.util;

import com.gmail.apach.dima.batch_demo.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.common.exception.ApplicationServerException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestUriUtil {

    public static URI location(String rootPath, String ... paths) {
        final var request = ((ServletRequestAttributes)
            RequestContextHolder.currentRequestAttributes()).getRequest();

        final var resourcePath = Objects.nonNull(paths)
            ? String.join(Delimiter.SLASH, paths)
            : Delimiter.EMPTY;

        final var url = String.join(
            Delimiter.EMPTY,
            request.getScheme(),
            Delimiter.COLON,
            Delimiter.DOUBLE_SLASH,
            request.getServerName(),
            Delimiter.COLON,
            String.valueOf(request.getServerPort()),
            rootPath,
            Delimiter.SLASH,
            resourcePath);

        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new ApplicationServerException(e.getMessage());
        }
    }
}
