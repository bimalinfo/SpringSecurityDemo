package com.javainuse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/test")
	public String test() {
		System.out.println("..called resource server test().......");
		return "Test::Hello World";
	}
	
	@RequestMapping("/login")
	public String login() {
		System.out.println("..called resource server login().......");
		return "Login::Hello World";
	}
}
