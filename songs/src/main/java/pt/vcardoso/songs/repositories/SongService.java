package pt.vcardoso.songs.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public List<Song> findSongByUuids(List<String> uuids) {
        return toList(this.repo.findAll(uuids));
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

    // find songs for each attribute
    public List<Song> getSongsListByMatch(String match) {

        if (match == null || match.isEmpty()) {
            return Collections.emptyList();
        }

        String[] matchArray = match.trim().toLowerCase().split(" ");

        List<Song> list = new ArrayList<>();

        StringBuilder regex = new StringBuilder();
        for (String word : matchArray) {
            regex.append("(?=.*").append(word).append(")");
        }
        regex.append(".*");

        Pattern pattern = Pattern.compile(regex.toString());

        this.findAll().forEach(song -> {
            String album = song.getAlbum().trim().toLowerCase();
            String artist = song.getArtist().trim().toLowerCase();
            String title = song.getTitle().trim().toLowerCase();
            if (pattern.matcher(album).matches() || pattern.matcher(artist).matches() || pattern.matcher(title.toString()).matches()) {
                list.add(song);
            }
        });
        return list;
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
