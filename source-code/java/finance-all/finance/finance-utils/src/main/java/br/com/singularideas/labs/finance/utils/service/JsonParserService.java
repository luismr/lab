package br.com.singularideas.labs.finance.utils.service;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class JsonParserService {

	public String encode(final Object obj) {
		return createGson().toJson(obj);
	}
	
	public <T> T decode(final String payload, Class<T> clazz) {
		return createGson().fromJson(payload, clazz);
	}
	
	private Gson createGson() {
		GsonBuilder builder= new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Gson gson = builder.create();
		return gson;
	}
 	
}
