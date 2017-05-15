package pt.vcardoso.songs.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.vcardoso.songs.entities.Song;

public interface SongRepository extends MongoRepository<Song, String> {

	// get songs by title
	List<Song> findByTitle(String title);

}