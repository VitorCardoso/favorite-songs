package pt.vcardoso.users.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.vcardoso.users.entities.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return this.repo.findAll();
	}

	public User findByKey(String id) {
		return this.repo.findOne(id);
	}

	public List<User> findUserByName(String name) {
		return this.repo.findByName(name);
	}

	@Transactional
	public User createUser(User song) {
		return this.repo.save(song);
	}

	@Transactional
	public User updateUser(String id, User user) {
		if (id != null) {
			User originalUser = this.findByKey(id);
			if (user.getEmail() != null) {
				originalUser.setEmail(user.getEmail());
			}
			if (user.getName() != null) {
				originalUser.setName(user.getName());
			}
			return this.repo.save(originalUser);
		}
		return null;
	}

	@Transactional
	public void removeUser(String id) {
		this.repo.delete(id);
	}
}
