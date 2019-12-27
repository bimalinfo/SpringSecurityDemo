package com.aetna;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/cool")
	public String goodCars() {
		return "Helo";
    }

}
