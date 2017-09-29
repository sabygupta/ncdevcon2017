package com.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestApplication {

	@RequestMapping(value = "/greet",
	        method = RequestMethod.GET)
	public String helloGreeting() {
		return "Hello REST";
	}

}
