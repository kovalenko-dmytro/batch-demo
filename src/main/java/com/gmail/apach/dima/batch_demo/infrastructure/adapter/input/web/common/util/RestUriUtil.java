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

    public static URI location(HttpServletRequest request, RequestParameters params) throws URISyntaxException {
        final var joined = String.join(
            Delimiter.EMPTY,
            request.getScheme(),
            Delimiter.COLON,
            Delimiter.DOUBLE_SLASH,
            request.getServerName(),
            Delimiter.COLON,
            String.valueOf(request.getServerPort()),
            RequestPath.BATCH_API_ROOT_PATH,
            Delimiter.SLASH,
            params.get(RequestParameter.JOB_EXEC_MARK));
        return new URI(joined);
    }
}
