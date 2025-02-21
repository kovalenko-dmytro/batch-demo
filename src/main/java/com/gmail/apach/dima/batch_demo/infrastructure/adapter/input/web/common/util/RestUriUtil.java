package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.util;

import com.gmail.apach.dima.batch_demo.core.base.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.constant.RequestPath;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.URISyntaxException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestUriUtil {

    public static URI buildCreatedUri(
        HttpServletRequest httpRequest,
        RequestParameters parameters
    ) throws URISyntaxException {
        final var stringBuilder = new StringBuilder()
            .append(httpRequest.getScheme())
            .append(Delimiter.COLON)
            .append(Delimiter.DOUBLE_SLASH)
            .append(httpRequest.getServerName())
            .append(Delimiter.COLON)
            .append(httpRequest.getServerPort())
            .append(RequestPath.BATCH_API_ROOT_PATH)
            .append(Delimiter.SLASH)
            .append(parameters.get(RequestParameter.JOB_EXEC_MARK));
        return new URI(stringBuilder.toString());
    }
}
