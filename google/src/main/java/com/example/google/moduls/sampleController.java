package com.example.google.moduls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sampleController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/google")
	public void google() {

	}
}
