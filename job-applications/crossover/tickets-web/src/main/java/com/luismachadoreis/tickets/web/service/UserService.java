package com.luismachadoreis.tickets.web.service;

import com.luismachadoreis.tickets.web.DuplicateEmailException;
import com.luismachadoreis.tickets.web.form.RegistrationForm;
import com.luismachadoreis.tickets.web.model.User;

/**
 * @author Luis Machado Reis
 */
public interface UserService {

    /**
     * Creates a new user account to the service.
     * 
     * @param userAccountData   The information of the created user account.
     * @return  The information of the created user account.
     * @throws DuplicateEmailException Thrown when the email address is found from the database.
     */
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
}
