package com.security.resttemplate;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping("/index")
	public String indexPage() {
		return "Index has successfully logged in!!!";
	}
	
	 @RequestMapping("/user") public String welcomeUser() { 
		 return "User has successfully logged in!!!";
	 }

	@RequestMapping("/admin")
	public String welcomeAdmin() {
		return "Admin has successfully logged in!!!";
	}


	@RequestMapping("/adminlogin")
	public String login() {
		return "Admin login has successfully called!!!";
	}

	@RequestMapping("/userlogin")
	public String userlogin() {
		return "User login has successfully called!!!";
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(Principal principal) {
		System.out.println("principal.getName()--->" + principal.getName());
		return principal.getName();
	}
}
