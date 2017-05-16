package pt.vcardoso.users.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.vcardoso.users.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

	// get users by name
	List<User> findByName(String name);

}