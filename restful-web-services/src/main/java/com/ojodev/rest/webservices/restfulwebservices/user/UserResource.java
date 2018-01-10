package com.ojodev.rest.webservices.restfulwebservices.user;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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

	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}

		//--HATEOAS--
		Resource<User> resource = new Resource<User>(user);
		
		//Enlace al método "/users" refiriéndonos al mapping del método de esta misma clase.
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		
		//Añadimos el link a la salida con el id "all-users"
		resource.add(linkTo.withRel("all-users")); //
		
		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI uri = ServletUriComponentsBuilder // Compone URIs
				.fromCurrentRequest() // Localiza el path del request actual -> /users
				.path("/{id}").buildAndExpand(savedUser.getId()) // Añade un parámetro al request con el id creado -> /5
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/users/{id}")
	private void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
	}

}
