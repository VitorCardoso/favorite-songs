package pt.vcardoso.users.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.vcardoso.users.entities.UserFavoriteSong;

public interface UserFavoriteSongRepository extends MongoRepository<UserFavoriteSong, String> {

    // get fav songs by user
    List<UserFavoriteSong> findByUserId(String userId);

}
