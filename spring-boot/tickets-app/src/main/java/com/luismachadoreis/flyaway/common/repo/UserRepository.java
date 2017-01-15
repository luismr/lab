package com.luismachadoreis.flyaway.common.repo;

import com.luismachadoreis.flyaway.common.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
