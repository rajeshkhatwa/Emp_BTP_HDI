package com.rk.employee;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {
	
	@RequestMapping("/hello")
	public String sayHello()
	{
 return "I am at port 8090 check it 26gmm";
}
	
	
/*
 * Request mapping will allow me to use it in url like 
 * http://localhost:8089/hello/rajesh	
 */
	@RequestMapping("/hello/{name}")
	public String sayHelloName(@PathVariable String name)
	{
 return "Hi " + name + " Kaise ho ?";
}
	

/*
 * Now, another option is to use @RequestParam	
 * use it like this http://localhost:8089/hi?name=Raj  ---> Parameter Passes
 */
	@RequestMapping("/hi")
public String sayhi(@RequestParam ("name") String name) {
	return "Hi" + name;	
	}	
}
