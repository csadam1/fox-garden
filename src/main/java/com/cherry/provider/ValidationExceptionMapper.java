package com.cherry.provider;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final String VALIDATION_FAILED_MESSAGE = "Validation failed: {0}";
    private static final String ERRORS_PROPERTY = "errors";

    private static final Logger logger = Logger.getLogger(ValidationExceptionMapper.class.getName());

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> errorMessages = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        Map<String, List<String>> responseBody = new HashMap<>();
        responseBody.put(ERRORS_PROPERTY, errorMessages);

        logger.log(Level.SEVERE, VALIDATION_FAILED_MESSAGE, errorMessages);

        return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                .type(MediaType.APPLICATION_JSON)
                .entity(responseBody)
                .build();
    }
}
