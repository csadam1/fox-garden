package com.cherry.validation;

import com.cherry.model.enumerate.Gender;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class GenderValidatorTest {

    private static final String INVALID_GENDER = "INVALID_GENDER";

    private final GenderValidator validator = new GenderValidator();

    @Test
    void shouldReturnFalse_whenValueIsNull() {
        boolean result = validator.isValid(null, mock(ConstraintValidatorContext.class));
        assertFalse(result);
    }

    @Test
    void shouldReturnTrue_whenValueIsMaleGender() {
        boolean result = validator.isValid(Gender.MALE.name(), mock(ConstraintValidatorContext.class));
        assertTrue(result);
    }

    @Test
    void shouldReturnTrue_whenValueIsFemaleGender() {
        boolean result = validator.isValid(Gender.FEMALE.name(), mock(ConstraintValidatorContext.class));
        assertTrue(result);
    }

    @Test
    void shouldReturnFalse_whenValueIsInvalidString() {
        boolean result = validator.isValid(INVALID_GENDER, mock(ConstraintValidatorContext.class));
        assertFalse(result);
    }
}
