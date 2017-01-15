package com.luismachadoreis.tickets.web.validation;

import com.luismachadoreis.tickets.web.validator.PasswordsNotEmpty;

/**
 * @author Luis Machado Reis
 */
@PasswordsNotEmpty(
        triggerFieldName = "trigger",
        passwordFieldName = "password",
        passwordVerificationFieldName = "passwordVerification"
)
@SuppressWarnings("unused")
public class PasswordsNotEmptyData {

	private String password;
    private String passwordVerification;
    private String trigger;

    public static PasswordsNotEmptyDataBuilder getBuilder() {
        return new PasswordsNotEmptyDataBuilder();
    }

    public static class PasswordsNotEmptyDataBuilder {

        private PasswordsNotEmptyData form;

        public PasswordsNotEmptyDataBuilder() {
            form = new PasswordsNotEmptyData();
        }

        public PasswordsNotEmptyDataBuilder password(String password) {
            form.password = password;
            return this;
        }

        public PasswordsNotEmptyDataBuilder passwordVerification(String passwordVerification) {
            form.passwordVerification = passwordVerification;
            return this;
        }

        public PasswordsNotEmptyDataBuilder trigger(String trigger) {
            form.trigger = trigger;
            return this;
        }

        public PasswordsNotEmptyData build() {
            return form;
        }
    }

}
