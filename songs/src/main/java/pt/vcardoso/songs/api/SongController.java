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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.vcardoso.songs.entities.Song;
import pt.vcardoso.songs.repositories.SongService;

@RestController
@RequestMapping("/api")
public class SongController {

    @Autowired
    private SongService service;

    // get all songs
    @GetMapping("/songs")
    public List<Song> getSongs() {
        return this.service.findAll();
    }

    // get a song by id
    @GetMapping("/songs/{uuid}")
    public ResponseEntity<Song> getSong(@PathVariable("uuid") String uuid) {
        Song song = this.service.findByKey(uuid);
        return new ResponseEntity<Song>(song, song != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // get a song by multiple ids
    @GetMapping("/songs/find")
    public List<Song> getSongs(@RequestParam(value = "id") List<String> uuids) {
        return this.service.findSongByUuids(uuids);
    }

    // search a song by all fields
    @GetMapping("/songs/search/{kw}")
    public List<Song> searchSong(@PathVariable("kw") String kw) {
        return this.service.getSongsListByMatch(kw);
    }

    // create a song
    @PostMapping("/songs")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        return new ResponseEntity<Song>(this.service.createSong(song), HttpStatus.CREATED);
    }

    // update a song
    @PutMapping("/songs/{uuid}")
    public ResponseEntity<Song> updateSong(@PathVariable("uuid") String uuid, @RequestBody Song song) {
        Song updatedSong = this.service.updateSong(uuid, song);
        return new ResponseEntity<Song>(updatedSong, updatedSong != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // delete a song
    @DeleteMapping("/songs/{uuid}")
    public ResponseEntity<Void> deleteSong(@PathVariable("uuid") String uuid) {
        this.service.removeSong(uuid);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
