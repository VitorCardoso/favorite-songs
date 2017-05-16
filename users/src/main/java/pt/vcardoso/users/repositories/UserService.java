package pt.vcardoso.users.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.vcardoso.users.entities.User;
import pt.vcardoso.users.entities.UserFavoriteSong;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserFavoriteSongRepository userFavRepo;

    public List<User> findAllUsers() {
        return this.repo.findAll();
    }

    public User findUserByKey(String id) {
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
        if (id == null) {
            return null;
        }
        User originalUser = this.findUserByKey(id);
        if (user.getEmail() != null) {
            originalUser.setEmail(user.getEmail());
        }
        if (user.getName() != null) {
            originalUser.setName(user.getName());
        }
        return this.repo.save(originalUser);
    }

    @Transactional
    public void removeUser(String id) {
        this.repo.delete(id);
        
        // deletes the list of favorite songs
        this.userFavRepo.delete(findUserFavoriteSongByUserId(id));
    }

    @Transactional
    public UserFavoriteSong createUserFavoriteSongByUserId(String userId, UserFavoriteSong userFavoriteSong) {
        if (userId == null || userFavoriteSong == null) {
            return null;
        }
        User user = this.findUserByKey(userId);
        if (user == null) {
            return null;
        }
        if (userFavoriteSong.getSongId() == null || userFavoriteSong.getUserId() == null) {
            return null;
        }
        return this.userFavRepo.save(userFavoriteSong);
    }

    public List<UserFavoriteSong> findUserFavoriteSongByUserId(String userId) {
        return this.userFavRepo.findByUserId(userId);
    }

    @Transactional
    public boolean removeUserFavoriteSongById(String userId, String songId) {
        if (userId == null || songId == null) {
            return false;
        }
        User user = this.findUserByKey(userId);
        if (user == null) {
            return false;
        }
        this.userFavRepo.delete(songId);
        return true;
    }

}
