package pt.vcardoso.users.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.vcardoso.users.entities.User;
import pt.vcardoso.users.repositories.UserService;

@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	private UserService service;

	@GetMapping("/ping")
	public String index() {
		return "Song REST API: OK";
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return this.service.findAll();
	}

	@GetMapping("/users/{uuid}")
	public ResponseEntity<User> getUser(@PathVariable("uuid") String uuid) {
		User user = this.service.findByKey(uuid);
		return new ResponseEntity<User>(user, user != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(this.service.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/users/{uuid}")
	public ResponseEntity<User> updateSong(@PathVariable("uuid") String uuid, @RequestBody User song) {
		User updatedUser = this.service.updateUser(uuid, song);
		return new ResponseEntity<User>(updatedUser, updatedUser != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/{uuid}")
	public ResponseEntity<Void> deleteUser(@PathVariable("uuid") String uuid) {
		this.service.removeUser(uuid);
		
		//delete to the list of favorite songs
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}