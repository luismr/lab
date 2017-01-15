package com.luismachadoreis.flyaway.common.session;

import com.luismachadoreis.flyaway.common.data.User;
import com.luismachadoreis.flyaway.common.repo.UserRepository;
import com.luismachadoreis.flyaway.common.session.SimpleUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RepoSimpleUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public RepoSimpleUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        UserDetails principal = SimpleUserDetails
                .getBuilder()
                    .firstName(user.getFirstName())
                    .id(user.getId())
                    .lastName(user.getLastName())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .providerService(user.getCredentialType())
                    .username(user.getEmail())
                .build();

        return principal;
    }

}
