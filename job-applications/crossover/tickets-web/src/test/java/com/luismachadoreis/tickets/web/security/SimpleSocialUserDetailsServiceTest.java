package com.luismachadoreis.tickets.web.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.luismachadoreis.tickets.web.model.Role;
import com.luismachadoreis.tickets.web.security.CurrentUserDetails;
import com.luismachadoreis.tickets.web.security.SimpleSocialUserDetailsService;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Luis Machado Reis
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleSocialUserDetailsServiceTest {

    private static final String USER_ID = "john.smith@gmail.com";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final String PASSWORD = "password";

    private SimpleSocialUserDetailsService service;

    @Mock
    private UserDetailsService userDetailsServicemock;

    @Before
    public void setUp() {
        service = new SimpleSocialUserDetailsService(userDetailsServicemock);
    }

    @Test
    public void loadByUserId_UserDetailsNotFound_ShouldThrowException() {
        when(userDetailsServicemock.loadUserByUsername(USER_ID)).thenThrow(new UsernameNotFoundException(""));

        catchException(service).loadUserByUserId(USER_ID);

        assertThat(caughtException())
                .isExactlyInstanceOf(UsernameNotFoundException.class)
                .hasNoCause();

        verify(userDetailsServicemock, times(1)).loadUserByUsername(USER_ID);
        verifyNoMoreInteractions(userDetailsServicemock);
    }

    @Test
    public void loadByUserId_UserDetailsFound_ShouldReturnTheFoundUserDetails() {
        CurrentUserDetails found = CurrentUserDetails.getBuilder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .password(PASSWORD)
                .username(USER_ID)
                .role(Role.ROLE_USER)
                .build();
        when(userDetailsServicemock.loadUserByUsername(USER_ID)).thenReturn(found);

        UserDetails actual = service.loadUserByUserId(USER_ID);

        assertThat(actual).isEqualTo(found);

        verify(userDetailsServicemock, times(1)).loadUserByUsername(USER_ID);
        verifyNoMoreInteractions(userDetailsServicemock);
    }
}
