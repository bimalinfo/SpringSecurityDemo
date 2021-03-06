package com.programmer.gate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal());
		return "/index";
	}
	
	@RequestMapping("/callback")
	public String callback() {
		System.out.println("<redirecting to home page.....>");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Auth Details"+auth.getDetails());
		return "/home";
	}
	
	/*@PostMapping("/logout")
	public String logout() {
		System.out.println("<redirecting to logout page.....>");
		return "/logout";
	}*/
	
	@GetMapping("/userdetail")
	public String callback1() {
		System.out.println("userdetail service redirecting to user details page");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Auth Details......"+auth.getDetails());
		System.out.println("Auth Principal Details....."+auth.getPrincipal().toString());
		System.out.println("Auth getName Details......."+auth.getName());
		return "/userdetails";
	}
 
}