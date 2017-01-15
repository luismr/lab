package br.com.singularideas.labs.finance.utils.service;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import br.com.singularideas.labs.finance.utils.RestServiceException;

@Service
public class RestService {

	public String get(final String url) {
		if (url == null) {
			throw new RestServiceException("URL cannot be null!");
		}
		
		HttpResponse<String> response;
		try {
			response = Unirest.get(url).asString();
		} catch (Exception e) {
			throw new RestServiceException(e.getMessage(), e);
		}
		
		return response.getBody();
	}
	
	
}
