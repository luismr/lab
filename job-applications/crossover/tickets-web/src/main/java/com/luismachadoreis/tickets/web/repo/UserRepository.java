package com.luismachadoreis.tickets.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luismachadoreis.tickets.web.model.User;

/**
 * @author Luis Machado Reis
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    
}
