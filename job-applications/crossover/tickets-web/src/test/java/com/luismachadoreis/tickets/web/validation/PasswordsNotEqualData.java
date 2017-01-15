package com.luismachadoreis.tickets.web.validation;

import com.luismachadoreis.tickets.web.validator.PasswordsNotEqual;

/**
 * @author Luis Machado Reis
 */
@PasswordsNotEqual(
        passwordFieldName = "password",
        passwordVerificationFieldName = "passwordVerification"
)
@SuppressWarnings("unused")
public class PasswordsNotEqualData {

	private String password;
    private String passwordVerification;

    public static PasswordsNotEqualDataBuilder getBuilder() {
        return new PasswordsNotEqualDataBuilder();
    }

    public static class PasswordsNotEqualDataBuilder {

        private PasswordsNotEqualData form;

        public PasswordsNotEqualDataBuilder() {
            form = new PasswordsNotEqualData();
        }

        public PasswordsNotEqualDataBuilder password(String password) {
            form.password = password;
            return this;
        }

        public PasswordsNotEqualDataBuilder passwordVerification(String passwordVerification) {
            form.passwordVerification = passwordVerification;
            return this;
        }

        public PasswordsNotEqualData build() {
            return form;
        }
    }

}
