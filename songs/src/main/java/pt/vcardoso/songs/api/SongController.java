package pt.vcardoso.songs.api;

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

import pt.vcardoso.songs.entities.Song;
import pt.vcardoso.songs.repositories.SongService;

@RestController
@RequestMapping("/api")
public class SongController {

	@Autowired
	private SongService service;

	@GetMapping("/songs")
	public List<Song> getSongs() {
		return this.service.findAll();
	}

	@GetMapping("/songs/{uuid}")
	public ResponseEntity<Song> getSong(@PathVariable("uuid") String uuid) {
		Song song = this.service.findByKey(uuid);
		return new ResponseEntity<Song>(song, song != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@PostMapping("/songs")
	public ResponseEntity<Song> createSong(@RequestBody Song song) {
		return new ResponseEntity<Song>(this.service.createSong(song), HttpStatus.CREATED);
	}

	@PutMapping("/songs/{uuid}")
	public ResponseEntity<Song> updateSong(@PathVariable("uuid") String uuid, @RequestBody Song song) {
		Song updatedSong = this.service.updateSong(uuid, song);
		return new ResponseEntity<Song>(updatedSong, updatedSong != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/songs/{uuid}")
	public ResponseEntity<Void> deleteSong(@PathVariable("uuid") String uuid) {
		this.service.removeSong(uuid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}