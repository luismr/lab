package com.luismachadoreis.tickets.web;

/**
 * The exception is thrown when the email given during the registration
 * phase is already found from the database.
 * 
 * @author Luis Machado Reis
 */
public class DuplicateEmailException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateEmailException(String message) {
        super(message);
    }
}
