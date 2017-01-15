package com.luismachadoreis.tickets.web.model;

import org.springframework.test.util.ReflectionTestUtils;

import com.luismachadoreis.tickets.web.model.AuthenticatorService;
import com.luismachadoreis.tickets.web.model.User;

/**
 * @author Luis Machado Reis
 */
public class UserBuilder {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private AuthenticatorService signInProvider;

    public UserBuilder() {

    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder signInProvider(AuthenticatorService signInProvider) {
        this.signInProvider = signInProvider;
        return this;
    }

    public User build() {
        User user = User.getBuilder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .signInProvider(signInProvider)
                .build();

        ReflectionTestUtils.setField(user, "id", id);

        return user;
    }
}
