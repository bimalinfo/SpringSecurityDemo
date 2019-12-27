package com.example.restclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomQuoteClientTests {

	private static final Logger logger = LoggerFactory.getLogger(RandomQuoteClientTests.class);
	
	@Autowired
	RandomQuoteClient client;
	
	@Test
	public void get() {
		Quote q = client.get();
		logger.info(q.toString());
	}
}
