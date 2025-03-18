package com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch;

import com.gmail.apach.dima.batch_demo.AbstractRestApiIntegrationTest;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto.GetExecutedJobResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Sql({"classpath:sql/get_job.sql"})
class GetExecutedJobRestApiTest extends AbstractRestApiIntegrationTest {

    private static final String BASE_PATH = "/api/v1/batches/";
    private static final String CORRECT_EXEC_UNIQUE_MARK = "f8ec8765-308a-4c26-9543-a84a369ba81f";
    private static final String INCORRECT_EXEC_UNIQUE_MARK = "f8ec8765-308a-4c26-9543-a84a369ba812";

    @Test
    void getExecutedJob_success() throws Exception {
        final var result = mvc.perform(
                get(BASE_PATH + CORRECT_EXEC_UNIQUE_MARK)
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        final var content = result.getResponse().getContentAsString();

        assertNotNull(content);

        final var response = objectMapper.readValue(content, GetExecutedJobResponse.class);

        assertNotNull(response);
        assertEquals(CORRECT_EXEC_UNIQUE_MARK, response.jobExecutionMarker());
    }

    @Test
    void getExecutedJob_notFound() throws Exception {
        final var result = mvc.perform(
                get(BASE_PATH + INCORRECT_EXEC_UNIQUE_MARK)
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}