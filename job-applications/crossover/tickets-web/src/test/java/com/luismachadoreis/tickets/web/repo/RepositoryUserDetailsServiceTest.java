package com.luismachadoreis.tickets.web.repo;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.luismachadoreis.tickets.web.security.ExampleUserDetailsAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.luismachadoreis.tickets.web.model.AuthenticatorService;
import com.luismachadoreis.tickets.web.model.User;
import com.luismachadoreis.tickets.web.model.UserBuilder;
import com.luismachadoreis.tickets.web.security.CurrentUserDetails;
import com.luismachadoreis.tickets.web.security.RepositoryUserDetailsService;

/**
 * @author Luis Machado Reis
 */
@RunWith(MockitoJUnitRunner.class)
public class RepositoryUserDetailsServiceTest {

	private static final Long ID = 1L;
	private static final String EMAIL = "luis@luismachadoreis.com";
	private static final String FIRST_NAME = "Luis";
	private static final String LAST_NAME = "Reis";
	private static final String PASSWORD = "2m@nys3cr3ts";

    private RepositoryUserDetailsService service;

    @Mock
    private UserRepository repositoryMock;

    @Before
    public void setUp() {
        service = new RepositoryUserDetailsService(repositoryMock);
    }

    @Test
    public void loadByUsername_UserNotFound_ShouldThrowException() {
        when(repositoryMock.findByEmail(EMAIL)).thenReturn(null);

        catchException(service).loadUserByUsername(EMAIL);
        Assertions.assertThat(caughtException())
                .isExactlyInstanceOf(UsernameNotFoundException.class)
                .hasMessage("No user found with username: " + EMAIL)
                .hasNoCause();

        verify(repositoryMock, times(1)).findByEmail(EMAIL);
        verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void loadByUsername_UserRegisteredByUsingFormRegistration_ShouldReturnCorrectUserDetails() {
        User found = new UserBuilder()
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .id(ID)
                .lastName(LAST_NAME)
                .password(PASSWORD)
                .build();

        when(repositoryMock.findByEmail(EMAIL)).thenReturn(found);

        UserDetails user = service.loadUserByUsername(EMAIL);
        CurrentUserDetails actual = (CurrentUserDetails) user;

        assertThat(actual)
                .hasFirstName(FIRST_NAME)
                .hasId(ID)
                .hasLastName(LAST_NAME)
                .hasPassword(PASSWORD)
                .hasUsername(EMAIL)
                .isActive()
                .isRegisteredUser()
                .isRegisteredByUsingFormRegistration();

        verify(repositoryMock, times(1)).findByEmail(EMAIL);
        verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void loadByUsername_UserSignedInByUsingSocialSignInProvider_ShouldReturnCorrectUserDetails() {
        User found = new UserBuilder()
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .id(ID)
                .lastName(LAST_NAME)
                .signInProvider(AuthenticatorService.TWITTER)
                .build();

        when(repositoryMock.findByEmail(EMAIL)).thenReturn(found);

        UserDetails user = service.loadUserByUsername(EMAIL);
        CurrentUserDetails actual = (CurrentUserDetails) user;

        assertThat(actual)
                .hasFirstName(FIRST_NAME)
                .hasId(ID)
                .hasLastName(LAST_NAME)
                .hasUsername(EMAIL)
                .isActive()
                .isRegisteredUser()
                .isSignedInByUsingSocialSignInProvider(AuthenticatorService.TWITTER);

        verify(repositoryMock, times(1)).findByEmail(EMAIL);
        verifyNoMoreInteractions(repositoryMock);
    }
}
