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
import pt.vcardoso.users.entities.UserFavoriteSong;
import pt.vcardoso.users.repositories.UserService;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UserService service;

    // Get all users
    @GetMapping("/users")
    public List<User> getUsers() {
        return this.service.findAllUsers();
    }

    // Get a user
    @GetMapping("/users/{uuid}")
    public ResponseEntity<User> getUser(@PathVariable("uuid") String uuid) {
        User user = this.service.findUserByKey(uuid);
        return new ResponseEntity<User>(user, user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // Create a user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(this.service.createUser(user), HttpStatus.CREATED);
    }

    // Update a user
    @PutMapping("/users/{uuid}")
    public ResponseEntity<User> updateSong(@PathVariable("uuid") String uuid, @RequestBody User song) {
        User updatedUser = this.service.updateUser(uuid, song);

        return new ResponseEntity<User>(updatedUser, updatedUser != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // Remove a user
    @DeleteMapping("/users/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("uuid") String uuid) {
        this.service.removeUser(uuid);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // Get all user favorite songs
    @GetMapping("/users/{uuid}/songs")
    public ResponseEntity<List<UserFavoriteSong>> getUserSongs(@PathVariable("uuid") String uuid) {
        User user = this.service.findUserByKey(uuid);
        if (user == null) {
            return new ResponseEntity<List<UserFavoriteSong>>(HttpStatus.NOT_FOUND);
        }
        List<UserFavoriteSong> listSongs = this.service.findUserFavoriteSongByUserId(user.getUuid());

        return new ResponseEntity<List<UserFavoriteSong>>(listSongs, HttpStatus.OK);
    }

    // Add a song to user favorite songs list
    @PostMapping("/users/songs")
    public ResponseEntity<UserFavoriteSong> createUserFavoriteSong(@RequestBody UserFavoriteSong userFavoriteSong) {
        UserFavoriteSong createUserFavoriteSongByUserId = this.service.createUserFavoriteSongByUserId(userFavoriteSong);
        return new ResponseEntity<UserFavoriteSong>(createUserFavoriteSongByUserId, createUserFavoriteSongByUserId == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
    }

    // Remove a song from user favorite songs list
    @DeleteMapping("/users/{uuid}/songs/{id}")
    public ResponseEntity<Void> deleteUserFavoriteSong(@PathVariable("uuid") String uuid, @PathVariable("id") String songId) {
        boolean result = this.service.removeUserFavoriteSongById(uuid, songId);
        return new ResponseEntity<Void>(result ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
    }

}
