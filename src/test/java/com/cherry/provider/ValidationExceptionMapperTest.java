package com.cherry.provider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationExceptionMapperTest {

    private static final String ERRORS_PROPERTY = "errors";
    private static final String NAME_MUST_NOT_BE_NULL = "name must not be null";
    private static final String GENDER_IS_INVALID = "gender is invalid";

    @Mock
    private ConstraintViolation<?> violation1;

    @Mock
    private ConstraintViolation<?> violation2;

    @InjectMocks
    private ValidationExceptionMapper mapper;

    @Test
    void shouldMapConstraintViolationExceptionToResponse() {
        // Given
        when(violation1.getMessage()).thenReturn(NAME_MUST_NOT_BE_NULL);
        when(violation2.getMessage()).thenReturn(GENDER_IS_INVALID);

        ConstraintViolationException exception =
                new ConstraintViolationException(Set.of(violation1, violation2));

        // When
        Response response = mapper.toResponse(exception);

        // Then
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

        Map<String, List<String>> body = (Map<String, List<String>>) response.getEntity();

        List<String> errors = body.get(ERRORS_PROPERTY);

        assertEquals(2, errors.size());
        assertTrue(errors.contains(NAME_MUST_NOT_BE_NULL));
        assertTrue(errors.contains(GENDER_IS_INVALID));
    }
}
