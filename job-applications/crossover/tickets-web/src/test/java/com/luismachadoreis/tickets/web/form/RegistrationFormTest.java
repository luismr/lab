package com.luismachadoreis.tickets.web.form;

import org.junit.Test;

import com.luismachadoreis.tickets.web.form.RegistrationForm;
import com.luismachadoreis.tickets.web.model.AuthenticatorService;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Luis Machado Reis
 */
public class RegistrationFormTest {

    private static final AuthenticatorService SIGN_IN_PROVIDER = AuthenticatorService.TWITTER;

    @Test
    public void isNormalRegistration_SocialProviderNotSet_ShouldReturnTrue() {
        RegistrationForm form = new RegistrationFormBuilder().build();

        boolean isNormalRegistration = form.isNormalRegistration();

        assertThat(isNormalRegistration).isTrue();
    }

    @Test
    public void isNormalRegistration_SocialProviderSet_ShouldReturnFalse() {
        RegistrationForm form = new RegistrationFormBuilder()
                .signInProvider(SIGN_IN_PROVIDER)
                .build();

        boolean isNormalRegistration = form.isNormalRegistration();

        assertThat(isNormalRegistration).isFalse();
    }

    @Test
    public void isSocialSignIn_SocialProviderNotSet_ShouldReturnFalse() {
        RegistrationForm form = new RegistrationFormBuilder().build();

        boolean isSocialSignIn = form.isSocialSignIn();

        assertThat(isSocialSignIn).isFalse();
    }

    @Test
    public void isSocialSignIn_SocialProviderSet_ShouldReturnTrue() {
        RegistrationForm form = new RegistrationFormBuilder()
                .signInProvider(SIGN_IN_PROVIDER)
                .build();

        boolean isSocialSignIn = form.isSocialSignIn();

        assertThat(isSocialSignIn).isTrue();
    }
}
