package com.cherry.provider;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> errorMessages = new ArrayList<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errorMessages.add(violation.getMessage());
        }

        Map<String, List<String>> responseBody = new HashMap<>();
        responseBody.put("errors", errorMessages);

        return Response.status(400)
                .type(MediaType.APPLICATION_JSON)
                .entity(responseBody)
                .build();
    }
}
