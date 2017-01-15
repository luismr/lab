package com.luismachadoreis.tickets.web.form;

import com.luismachadoreis.tickets.web.model.AuthenticatorService;

/**
 * @author Luis Machado Reis
 */
public class RegistrationFormBuilder {

    private RegistrationForm form;

    public RegistrationFormBuilder() {
        form = new RegistrationForm();
    }

    public RegistrationFormBuilder email(String email) {
        form.setEmail(email);
        return this;
    }

    public RegistrationFormBuilder firstName(String firstName) {
        form.setFirstName(firstName);
        return this;
    }

    public RegistrationFormBuilder lastName(String lastName) {
        form.setLastName(lastName);
        return this;
    }

    public RegistrationFormBuilder password(String password) {
        form.setPassword(password);
        return this;
    }

    public RegistrationFormBuilder passwordVerification(String passwordVerification) {
        form.setPasswordVerification(passwordVerification);
        return this;
    }

    public RegistrationFormBuilder signInProvider(AuthenticatorService signInProvider) {
        form.setSignInProvider(signInProvider);
        return this;
    }

    public RegistrationForm build() {
        return form;
    }
}
