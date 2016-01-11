package br.com.singularideas.labs.knowhub.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import br.com.singularideas.labs.knowhub.common.vo.LoginRequest;
import br.com.singularideas.labs.knowhub.common.vo.Profile;
import br.com.singularideas.labs.knowhub.common.vo.StandardProfile;

@Service
public class LoginService {

	private static final String ACCEPT = "accept";
	private static final String APPLICATION_JSON = "application/json";
	private static final String PATH_LOGIN = "/login";
	private static final String CONTENT_TYPE = "Content-Type";
	
	public static final String DEFAULT_BASEURL = "http://localhost:8080/knowhub-web";

	@Autowired
	private Gson gson;
	
	public Profile login(final String email, final String password) {
		return login(DEFAULT_BASEURL, email, password);
	}
	
	public Profile login(final String baseurl, final String email, final String password) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(baseurl), "baseurl must not be empty!");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(email), "email must not be empty!");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "password must not be empty!");

		LoginRequest request = new LoginRequest();
		request.setEmail(email);
		request.setPassword(password);
		
		Profile profile = null;
		
		try {
			HttpResponse<String> response = Unirest.post(baseurl + PATH_LOGIN)
											.header(ACCEPT, APPLICATION_JSON)
											.header(CONTENT_TYPE, APPLICATION_JSON)
											.body(gson.toJson(request))
											.asString();
			profile = gson.fromJson(response.getBody(), StandardProfile.class);
		} catch (Exception e) {
			throw new ApiException("Cannot login", e);
		}
		
		return profile;
	}

	public Profile refresh(final Profile profile) {
		return refresh(DEFAULT_BASEURL, profile);
	}
	
	public Profile refresh(final String baseurl, final Profile profile) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(baseurl), "baseurl must not be empty!");
		Preconditions.checkArgument(profile != null, "profile must not be null!");
		Preconditions.checkArgument(profile.getSubscriber() != null, "profile must not be null!");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(profile.getSubscriber().getEmail()), "email must not be empty!");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(profile.getSubscriber().getPassword()), "password must not be empty!");
		
		return login(baseurl, profile.getSubscriber().getEmail(), profile.getSubscriber().getPassword());
	}
	
}
