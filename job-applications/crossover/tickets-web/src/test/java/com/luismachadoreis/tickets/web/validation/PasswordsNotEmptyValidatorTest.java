package com.luismachadoreis.tickets.web.validation;

import static com.luismachadoreis.tickets.web.validation.PasswordsNotEmptyAssert.assertThat;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.luismachadoreis.tickets.web.validator.PasswordsNotEmpty;

/**
 * @author Luis Machado Reis
 */
@SuppressWarnings("unused")
public class PasswordsNotEmptyValidatorTest {

    private static final String PASSWORD = "password";
    private static final String PASSWORD_VERIFICATION = "passwordVerification";
    private static final String TRIGGER = "trigger";

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void passwordsNotEmpty_TriggerFieldIsNotNull_ShouldValidateNothing() {
        PasswordsNotEmptyData notValidated = PasswordsNotEmptyData.getBuilder()
                .trigger(TRIGGER)
                .build();

        assertThat(validator.validate(notValidated)).hasNoValidationErrors();
    }

    @Test
    public void passwordsNotEmpty_TriggerFieldNullAndPasswordFieldsHaveValues_ShouldPassValidation() {
        PasswordsNotEmptyData passesValidation = PasswordsNotEmptyData.getBuilder()
                .password(PASSWORD)
                .passwordVerification(PASSWORD_VERIFICATION)
                .trigger(TRIGGER)
                .build();

        assertThat(validator.validate(passesValidation)).hasNoValidationErrors();
    }

    @Test
    public void passwordsNotEmpty_TriggerFieldAndPasswordFieldsAreNull_ShouldReturnValidationErrorForPasswordField() {
        PasswordsNotEmptyData failsValidation = PasswordsNotEmptyData.getBuilder()
                .passwordVerification(PASSWORD_VERIFICATION)
                .build();

            assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(1)
                .hasValidationErrorForField("password");
    }

    @Test
    public void passwordsNotEmpty_TriggerFieldIsNullAndPasswordFieldIsEmpty_ShouldReturnValidationErrorForPasswordField() {
        PasswordsNotEmptyData failsValidation = PasswordsNotEmptyData.getBuilder()
                .password("")
                .passwordVerification(PASSWORD_VERIFICATION)
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(1)
                .hasValidationErrorForField("password");
    }


    @Test
    public void passwordsNotEmpty_TriggerFieldAndPasswordVerificationFieldsAreNull_ShouldReturnValidationErrorForPasswordVerificationField() {
        PasswordsNotEmptyData failsValidation = PasswordsNotEmptyData.getBuilder()
                .password(PASSWORD)
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(1)
                .hasValidationErrorForField("passwordVerification");
    }

    @Test
    public void passwordsNotEmpty_TriggerFieldIsNullAndPasswordVerificationFieldIsEmpty_ShouldReturnValidationErrorForPasswordVerificationField() {
        PasswordsNotEmptyData failsValidation = PasswordsNotEmptyData.getBuilder()
                .password(PASSWORD)
                .passwordVerification("")
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(1)
                .hasValidationErrorForField("passwordVerification");
    }

    @Test
    public void passwordsNotEmpty_TriggerFieldIsNullAndBothPasswordFieldsAreNull_ShouldReturnValidationErrorsForBothFields() {
        PasswordsNotEmptyData failsValidation = PasswordsNotEmptyData.getBuilder().build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(2)
                .hasValidationErrorForField("password")
                .hasValidationErrorForField("passwordVerification");
    }

    @Test
    public void passwordsNotEmpty_TriggerFieldIsNullAndBothPasswordFieldsAreEmpty_ShouldReturnValidationErrorsForBothFields() {
        PasswordsNotEmptyData failsValidation = PasswordsNotEmptyData.getBuilder()
                .password("")
                .passwordVerification("")
                .build();

        assertThat(validator.validate(failsValidation))
                .numberOfValidationErrorsIs(2)
                .hasValidationErrorForField("password")
                .hasValidationErrorForField("passwordVerification");
    }

    @Test(expected = ValidationException.class)
    public void passwordsNotEmpty_InvalidTriggerField_ShouldThrowException() {
        InvalidTriggerFieldData invalid = new InvalidTriggerFieldData();

        validator.validate(invalid);
    }

    @Test(expected = ValidationException.class)
    public void passwordsNotEmpty_InvalidPasswordField_ShouldThrowException() {
        InvalidPasswordFieldData invalid = new InvalidPasswordFieldData();

        validator.validate(invalid);
    }

    @Test(expected = ValidationException.class)
    public void passwordsNotEmpty_InvalidPasswordVerificationField_ShouldThrowException() {
        InvalidPasswordVerificationFieldData invalid = new InvalidPasswordVerificationFieldData();

        validator.validate(invalid);
    }

    @PasswordsNotEmpty(
            triggerFieldName = "trigger",
            passwordFieldName = "password",
            passwordVerificationFieldName = "passwordVerification"
    )
    private class InvalidTriggerFieldData {
		private String password;
        private String passwordVerification;
    }

    @PasswordsNotEmpty(
            triggerFieldName = "trigger",
            passwordFieldName = "password",
            passwordVerificationFieldName = "passwordVerification"
    )
    private class InvalidPasswordFieldData {

        private String trigger;
        private String passwordVerification;
    }

    @PasswordsNotEmpty(
            triggerFieldName = "trigger",
            passwordFieldName = "password",
            passwordVerificationFieldName = "passwordVerification"
    )
    private class InvalidPasswordVerificationFieldData {

        private String trigger;
        private String password;
    }

}
