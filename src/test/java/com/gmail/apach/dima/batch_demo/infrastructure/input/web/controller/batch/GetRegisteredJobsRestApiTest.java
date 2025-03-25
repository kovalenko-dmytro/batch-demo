package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch;

import com.gmail.apach.dima.batch_demo.AbstractRestApiIntegrationTest;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.constant.RequestPath;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto.GetRegisteredJobsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Sql({"classpath:sql/job_registry.sql"})
class GetRegisteredJobsRestApiTest extends AbstractRestApiIntegrationTest {

    @Test
    void getRegisteredJobs_success() throws Exception {
        final var result = mvc.perform(
                get(RequestPath.BatchRegistryApi.ROOT_PATH).contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        final var content = result.getResponse().getContentAsString();

        assertNotNull(content);

        final var response = objectMapper.readValue(content, GetRegisteredJobsResponse.class);

        assertNotNull(response);
        assertNotNull(response.jobNames());
        assertFalse(response.jobNames().isEmpty());
    }
}