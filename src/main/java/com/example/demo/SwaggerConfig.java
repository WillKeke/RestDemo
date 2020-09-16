package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//Configuration
@Configuration
//Enable Swagger
@EnableSwagger2
public class SwaggerConfig {
	
	
	public static final Contact DEFAULT_CONTACT
    = new Contact(
    "THRE IS WHERE THE CONTACT NAME GOES(will k)",
    "THIS IS WHERE THE WEBSITE GOES",
    "EMAIL GOES HERE");
	
	 
public static final ApiInfo DEFAULT_API_INFO
    = new ApiInfo(
    "THIS IS WERE THE TITLE GOES",
    "THIS IS WHERE THE DOCUMENTATION GOES",
    "1.0",
    "urn:tos",
    DEFAULT_CONTACT,
    "Apache 2.0",
    "http://www.apache.org/licenses/LICENSE-2.0",
    new ArrayList<>());


private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
	new HashSet<String> (Arrays.asList("application/json",
		"application/xml"));
	//Bean - docket
		// Swagger 2	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(DEFAULT_API_INFO)
		.produces(DEFAULT_PRODUCES_AND_CONSUMES)
		.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}

	// All the paths
	// All The apis
	
	
	
}
