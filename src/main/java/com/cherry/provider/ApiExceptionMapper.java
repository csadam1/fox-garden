package com.cherry.provider;

import com.cherry.exception.ApiException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    private static final String ERROR_PROPERTY = "error";
    private static final String ERROR_OCCURRED = "Error occurred";

    private static final Logger logger = Logger.getLogger(ApiExceptionMapper.class.getName());

    @Override
    public Response toResponse(ApiException ex) {
        logger.log(Level.SEVERE, ERROR_OCCURRED, ex);

        return Response.status(ex.getStatus())
                .entity(Map.of(ERROR_PROPERTY, ex.getMessage()))
                .build();
    }
}
