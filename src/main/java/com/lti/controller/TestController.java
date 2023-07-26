package com.lti.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "Welcome Hello";
		
		//http://localhost:8081/UserRestApp/myapp/test/hello
	}
	
	
//	@RequestMapping("/helloname/{name}")
//	public String sayHelloName(@PathVariable String name)
//	{
//		return "hello "+ name;
//		
//		//http://localhost:8081/UserRestApp/myapp/test/hello/rutika
//	}
//	
	
	@RequestMapping("/helloname/{name}/{lname}")
	public String sayHelloName(@PathVariable String name,@PathVariable("lname") String lastname)
	{
		return "hello "+ name +lastname;
		
		//http://localhost:8081/UserRestApp/myapp/test/helloname/rutika/shetye
	}
	
	@RequestMapping("/hellofullname")
	public String sayHelloFullName(@RequestParam("fname") String name,@RequestParam("lname") String lastname)
	{
		return "hello "+ name +lastname;
		
		//http://localhost:8081/UserRestApp/myapp/test/hellofullname?fname=rutika&lname=shetye
	}
}
