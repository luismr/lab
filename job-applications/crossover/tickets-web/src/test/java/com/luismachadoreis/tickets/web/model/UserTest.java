package com.luismachadoreis.tickets.web.model;

import static com.luismachadoreis.tickets.web.model.UserAssert.assertThat;

import org.junit.Test;

import com.luismachadoreis.tickets.web.model.AuthenticatorService;
import com.luismachadoreis.tickets.web.model.User;

/**
 * @author Luis Machado Reis
 */
public class UserTest {

	private static final String EMAIL = "luis@luismachadoreis.com";
	private static final String FIRST_NAME = "Luis";
	private static final String LAST_NAME = "Reis";
	private static final String PASSWORD = "2m@nys3cr3ts";
	private static final AuthenticatorService SIGN_IN_PROVIDER = AuthenticatorService.TWITTER;

	@Test
	public void build_SignedInByUsingSocialMediaService_ShouldCreateUser() {
		User user = User.getBuilder().email(EMAIL).firstName(FIRST_NAME).lastName(LAST_NAME)
				.signInProvider(SIGN_IN_PROVIDER).build();

		assertThat(user).hasNoId().hasEmail(EMAIL).hasFirstName(FIRST_NAME).hasLastName(LAST_NAME).isRegisteredUser()
				.isRegisteredByUsingSignInProvider(SIGN_IN_PROVIDER).hasNoPassword();
	}

	@Test
	public void build_RegisteredViaNormalRegistration_ShouldCreateUser() {
		User user = User.getBuilder().email(EMAIL).firstName(FIRST_NAME).lastName(LAST_NAME).password(PASSWORD).build();

		assertThat(user).hasNoId().hasEmail(EMAIL).hasFirstName(FIRST_NAME).hasLastName(LAST_NAME).hasPassword(PASSWORD)
				.isRegisteredUser().isRegisteredByUsingNormalRegistration();
	}

}
