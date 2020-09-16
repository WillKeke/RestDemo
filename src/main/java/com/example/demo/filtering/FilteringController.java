package com.example.demo.filtering;

import java.awt.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1","value2","value3");
	}
	
	@GetMapping("/filtering-list")
	public java.util.List<SomeBean>  retrieveListOfSomeBean() {
		return Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value11","value22","value33"));
	}
	
}
