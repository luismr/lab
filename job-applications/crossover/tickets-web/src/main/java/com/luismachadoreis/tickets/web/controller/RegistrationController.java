package com.luismachadoreis.tickets.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.luismachadoreis.tickets.web.DuplicateEmailException;
import com.luismachadoreis.tickets.web.form.RegistrationForm;
import com.luismachadoreis.tickets.web.model.AuthenticatorService;
import com.luismachadoreis.tickets.web.model.User;
import com.luismachadoreis.tickets.web.security.SecurityUtil;
import com.luismachadoreis.tickets.web.service.UserService;

/**
 * @author Luis Machado Reis
 */
@Controller
@SessionAttributes("user")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    protected static final String ERROR_CODE_EMAIL_EXIST = "NotExist.user.email";
    protected static final String MODEL_NAME_REGISTRATION_DATA = "user";
    protected static final String VIEW_NAME_REGISTRATION_PAGE = "user/registrationForm";

    private UserService service;

    @Autowired
    public RegistrationController(UserService service) {
        this.service = service;
    }

    /**
     * Renders the registration page.
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        LOGGER.debug("Rendering registration page.");

        @SuppressWarnings("deprecation")
		Connection<?> connection = ProviderSignInUtils.getConnection(request);

        RegistrationForm registration = createRegistrationData(connection);
        LOGGER.debug("Rendering registration form with information: {}", registration);

        model.addAttribute(MODEL_NAME_REGISTRATION_DATA, registration);

        return VIEW_NAME_REGISTRATION_PAGE;
    }

    /**
     * Creates the form object used in the registration form.
     * @param connection
     * @return  If a user is signing in by using a social provider, this method returns a form
     *          object populated by the values given by the provider. Otherwise this method returns
     *          an empty form object (normal form registration).
     */
    private RegistrationForm createRegistrationData(Connection<?> connection) {
        RegistrationForm data = new RegistrationForm();

        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            data.setEmail(socialMediaProfile.getEmail());
            data.setFirstName(socialMediaProfile.getFirstName());
            data.setLastName(socialMediaProfile.getLastName());

            ConnectionKey providerKey = connection.getKey();
            data.setSignInProvider(AuthenticatorService.valueOf(providerKey.getProviderId().toUpperCase()));
        }

        return data;
    }

    /**
     * Processes the form submissions of the registration form.
     */
    @SuppressWarnings("deprecation")
	@RequestMapping(value ="/user/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
                                      BindingResult result,
                                      WebRequest request) throws DuplicateEmailException {
        LOGGER.debug("Registering user account with information: {}", userAccountData);
        if (result.hasErrors()) {
            LOGGER.debug("Validation errors found. Rendering form view.");
            return VIEW_NAME_REGISTRATION_PAGE;
        }

        LOGGER.debug("No validation errors found. Continuing registration process.");

        User registered = createUserAccount(userAccountData, result);

        if (registered == null) {
            LOGGER.debug("An email address was found from the database. Rendering form view.");
            return VIEW_NAME_REGISTRATION_PAGE;
        }

        LOGGER.debug("Registered user account with information: {}", registered);

        SecurityUtil.logInUser(registered);
        LOGGER.debug("User {} has been signed in");

        ProviderSignInUtils.handlePostSignUp(registered.getEmail(), request);

        return "redirect:/";
    }

    /**
     * Creates a new user account by calling the service method. If the email address is found
     * from the database, this method adds a field error to the email field of the form object.
     */
    private User createUserAccount(RegistrationForm userAccountData, BindingResult result) {
        LOGGER.debug("Creating user account with information: {}", userAccountData);
        User registered = null;

        try {
            registered = service.registerNewUserAccount(userAccountData);
        }
        catch (DuplicateEmailException ex) {
            LOGGER.debug("An email address: {} exists.", userAccountData.getEmail());
            addFieldError(
                    MODEL_NAME_REGISTRATION_DATA,
                    RegistrationForm.FIELD_NAME_EMAIL,
                    userAccountData.getEmail(),
                    ERROR_CODE_EMAIL_EXIST,
                    result);
        }

        return registered;
    }

    private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
        LOGGER.debug(
                "Adding field error object's: {} field: {} with error code: {}",
                objectName,
                fieldName,
                errorCode
        );
        
        FieldError error = new FieldError(
                objectName,
                fieldName,
                fieldValue,
                false,
                new String[]{errorCode},
                new Object[]{},
                errorCode
        );

        result.addError(error);
        LOGGER.debug("Added field error: {} to binding result: {}", error, result);
    }
}
