package com.example.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RandomQuoteClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	public Quote get() { 
		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		return quote;
	}
}
