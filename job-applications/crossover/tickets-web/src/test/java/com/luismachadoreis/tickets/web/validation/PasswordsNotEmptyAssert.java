package com.luismachadoreis.tickets.web.validation;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author Luis Machado Reis
 */
public class PasswordsNotEmptyAssert extends ConstraintViolationAssert<PasswordsNotEmptyData> {

    private static final String VALIDATION_ERROR_MESSAGE = "PasswordsNotEmpty";

    public PasswordsNotEmptyAssert(Set<ConstraintViolation<PasswordsNotEmptyData>> actual) {
        super(PasswordsNotEmptyAssert.class, actual);
    }

    public static PasswordsNotEmptyAssert assertThat(Set<ConstraintViolation<PasswordsNotEmptyData>> actual) {
        return new PasswordsNotEmptyAssert(actual);
    }

    @Override
    protected String getErrorMessage() {
        return VALIDATION_ERROR_MESSAGE;
    }
}
