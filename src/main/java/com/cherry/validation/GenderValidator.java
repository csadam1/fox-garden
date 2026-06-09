package com.cherry.validation;

import com.cherry.model.enumerate.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

    private final Set<String> allowed = Arrays.stream(Gender.values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return allowed.contains(value);
    }
}
