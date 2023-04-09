package com.example.google.moduls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/fail")
	public String fail() {
		return "fail";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/oauth2/redirect")
	public String redirct(@RequestParam String accessToken) {
		System.out.println(accessToken);
		return "oauth";
	}

}
