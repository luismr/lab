package br.com.singularideas.labs.knowhub.web.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import br.com.singularideas.labs.knowhub.common.vo.LoginRequest;
import br.com.singularideas.labs.knowhub.common.vo.Profile;
import br.com.singularideas.labs.knowhub.model.service.GenericAuthenticationService;
import br.com.singularideas.labs.knowhub.web.pojo.JsonProfile;

@Controller
@RequestMapping
public class AuthenticationController {

	@Autowired
	private GenericAuthenticationService authenticationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Profile login(final @RequestBody LoginRequest request) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getEmail()), "email must not be empty!");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getPassword()), "password must not be empty!");
		
		Profile profile = authenticationService.login(request.getEmail(), request.getPassword());
		profile = new JsonProfile(profile);
		
		return profile;	
	}
	
}
