package pt.vcardoso.songs.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.vcardoso.songs.entities.Song;

@Service
public class SongService {

    @Autowired
    private SongRepository repo;

    @Autowired
    private ImportSongsService importSongsService;

    public List<Song> findAll() {
        return this.repo.findAll();
    }

    public Song findByKey(String id) {
        return this.repo.findOne(id);
    }

    public List<Song> findSongByTitle(String title) {
        return this.repo.findByTitle(title);
    }

    @Transactional
    public Song createSong(Song song) {
        return this.repo.save(song);
    }

    @Transactional
    public Song updateSong(String id, Song song) {
        if (id == null) {
            return null;
        }
        Song originalSong = this.findByKey(id);
        if (song.getAlbum() != null) {
            originalSong.setAlbum(song.getAlbum());
        }
        if (song.getArtist() != null) {
            originalSong.setArtist(song.getArtist());
        }
        if (song.getTitle() != null) {
            originalSong.setTitle(song.getTitle());
        }
        return this.repo.save(originalSong);
    }

    @Transactional
    public void removeSong(String id) {
        this.repo.delete(id);
    }

    @Transactional
    public void importSongs() {
        try {
            List<Song> importedSongs = this.importSongsService.importSongs();
            this.repo.save(importedSongs);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
