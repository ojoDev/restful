package com.ojodev.rest.webservices.restfulwebservices.user;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		//--HATEOAS--
		Resource<User> resource = new Resource<User>(user.get());
		
		//Enlace al método "/users" refiriéndonos al mapping del método de esta misma clase.
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		
		//Añadimos el link a la salida con el id "all-users"
		resource.add(linkTo.withRel("all-users")); //
		
		return resource;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder // Compone URIs
				.fromCurrentRequest() // Localiza el path del request actual -> /users
				.path("/{id}").buildAndExpand(savedUser.getId()) // Añade un parámetro al request con el id creado -> /5
				.toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOpt = userRepository.findById(id);
		if (!userOpt.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		User user = userOpt.get();
		
		post.setUser(user);
		postRepository.save(post);
		URI uri = ServletUriComponentsBuilder // Compone URIs
				.fromCurrentRequest() // Localiza el path del request actual -> /users
				.path("/{id}").buildAndExpand(post.getId()) // Añade un parámetro al request con el id creado -> /5
				.toUri();
		return ResponseEntity.created(uri).build();
	}
		

	@DeleteMapping("/jpa/users/{id}")
	private void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	private List<Post> retrieveAllPostFromUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		return user.get().getPost();
	}

}
