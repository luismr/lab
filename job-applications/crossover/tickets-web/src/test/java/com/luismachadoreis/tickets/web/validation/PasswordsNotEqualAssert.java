package com.luismachadoreis.tickets.web.validation;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author Luis Machado Reis
 */
public class PasswordsNotEqualAssert extends ConstraintViolationAssert<PasswordsNotEqualData> {

    private static final String VALIDATION_ERROR_MESSAGE = "PasswordsNotEqual";

    public PasswordsNotEqualAssert(Set<ConstraintViolation<PasswordsNotEqualData>> actual) {
        super(PasswordsNotEqualAssert.class, actual);
    }

    public static PasswordsNotEqualAssert assertThat(Set<ConstraintViolation<PasswordsNotEqualData>> actual) {
        return new PasswordsNotEqualAssert(actual);
    }

    @Override
    protected String getErrorMessage() {
        return VALIDATION_ERROR_MESSAGE;
    }
}
