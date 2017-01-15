package com.luismachadoreis.tickets.web.validation;

import static com.luismachadoreis.tickets.web.validation.PasswordsNotEqualAssert.assertThat;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.luismachadoreis.tickets.web.validator.PasswordsNotEqual;

/**
 * @author Luis Machado Reis
 */
@SuppressWarnings("unused")
public class PasswordsNotEqualValidatorTest {

    private static final String PASSWORD = "password";
    private static final String PASSWORD_VERIFICATION = "passwordVerification";

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void passwordsNotEqual_BothPasswordsAreNull_ShouldPassValidation() {
        PasswordsNotEqualData passesValidation = PasswordsNotEqualData.getBuilder().build();

        assertThat(validator.validate(passesValidation)).hasNoValidationErrors();
    }

    @Test
    public void passwordsNotEqual_PasswordIsNull_ShouldReturnValidationErrorsForBothFields() {
        PasswordsNotEqualData failsValidation = PasswordsNotEqualData.getBuilder()
                .passwordVerification(PASSWORD_VERIFICATION)
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(2)
                .hasValidationErrorForField("password")
                .hasValidationErrorForField("passwordVerification");
    }

    @Test
    public void passwordsNotEqual_PasswordVerificationIsNull_ShouldReturnValidationErrorsForBothFields() {
        PasswordsNotEqualData failsValidation = PasswordsNotEqualData.getBuilder()
                .password(PASSWORD)
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(2)
                .hasValidationErrorForField("password")
                .hasValidationErrorForField("passwordVerification");
    }

    @Test
    public void passwordsNotEqual_BothPasswordsAreEmpty_ShouldPassValidation() {
        PasswordsNotEqualData passesValidation = PasswordsNotEqualData.getBuilder()
                .password("")
                .passwordVerification("")
                .build();

        assertThat(validator.validate(passesValidation)).hasNoValidationErrors();
    }

    @Test
    public void passwordsNotEqual_PasswordIsEmpty_ShouldReturnValidationErrorsForBothFields() {
        PasswordsNotEqualData failsValidation = PasswordsNotEqualData.getBuilder()
                .password("")
                .passwordVerification(PASSWORD_VERIFICATION)
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(2)
                .hasValidationErrorForField("password")
                .hasValidationErrorForField("passwordVerification");
    }

    @Test
    public void passwordsNotEqual_PasswordVerificationIsEmpty_ShouldReturnValidationErrorsForBothFields() {
        PasswordsNotEqualData failsValidation = PasswordsNotEqualData.getBuilder()
                .password(PASSWORD)
                .passwordVerification("")
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(2)
                .hasValidationErrorForField("password")
                .hasValidationErrorForField("passwordVerification");
    }

    @Test
    public void passwordsNotEqual_PasswordMismatch_ShouldReturnValidationErrorsForBothFields() {
        PasswordsNotEqualData failsValidation = PasswordsNotEqualData.getBuilder()
                .password(PASSWORD)
                .passwordVerification(PASSWORD_VERIFICATION)
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(2)
                .hasValidationErrorForField("password")
                .hasValidationErrorForField("passwordVerification");
    }

    @Test
    public void passwordsNotEqual_PasswordsMatch_ShouldPassValidation() {
        PasswordsNotEqualData passesValidation = PasswordsNotEqualData.getBuilder()
                .password(PASSWORD)
                .passwordVerification(PASSWORD)
                .build();

        assertThat(validator.validate(passesValidation)).hasNoValidationErrors();
    }

    @Test(expected = ValidationException.class)
    public void passwordsNotEqual_InvalidPasswordField_ShouldThrowException() {
        InvalidPasswordFieldData invalid = new InvalidPasswordFieldData();

        validator.validate(invalid);
    }

    @Test(expected = ValidationException.class)
    public void passwordsNotEqual_InvalidPasswordVerificationField_ShouldThrowException() {
        InvalidPasswordVerificationFieldData invalid = new InvalidPasswordVerificationFieldData();

        validator.validate(invalid);
    }

    @PasswordsNotEqual(
            passwordFieldName = "password",
            passwordVerificationFieldName = "passwordVerification"
    )
    private class InvalidPasswordFieldData {
		private String passwordVerification;
    }

    @PasswordsNotEqual(
            passwordFieldName = "password",
            passwordVerificationFieldName = "passwordVerification"
    )
    private class InvalidPasswordVerificationFieldData {
        private String password;
    }
}
