package com.cherry.provider;

import com.cherry.exception.ApiException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException ex) {
        return Response.status(ex.getStatus())
                .entity(Map.of("error", ex.getMessage()))
                .build();
    }
}
