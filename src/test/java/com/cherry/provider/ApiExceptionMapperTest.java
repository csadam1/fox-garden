package com.cherry.provider;

import com.cherry.exception.ApiException;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiExceptionMapperTest {

    private static final int BAD_REQUEST_CODE = Response.Status.BAD_REQUEST.getStatusCode();
    private static final String MESSAGE = "Fox not found with id 1";
    private static final String ERROR = "error";

    private static final ApiExceptionMapper mapper = new ApiExceptionMapper();

    @Test
    void shouldMapApiExceptionToResponse() {
        // Given
        ApiException exception = new ApiException(MESSAGE, BAD_REQUEST_CODE);

        // When
        Response response = mapper.toResponse(exception);

        // Then
        assertEquals(BAD_REQUEST_CODE, response.getStatus());

        Map<String, Object> entity = (Map<String, Object>) response.getEntity();
        assertEquals(MESSAGE, entity.get(ERROR));
    }
}
