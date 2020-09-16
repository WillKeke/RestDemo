package com.example.demo.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	
	//retreiveAllUsers
	@GetMapping(path ="/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	
	//retrieve specific users
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}
	
	//CREATED
	// input -details of user
	// output - Created and return the created the URI
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id-"+ id);
		
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> created(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		//NEED TO MAKE A CREATED STATUS
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")// path lets you append to the uri
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
