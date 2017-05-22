package pt.vcardoso.songs.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import pt.vcardoso.songs.entities.Song;

public class Teste {
    public static List<Song> data = new ArrayList<>();

    public static void main(String[] args) {

        data.add(new Song());
        data.add(new Song("asd", "asd", null));
        data.add(new Song("12", "12", "12"));

        getSongsListByMatch("a").forEach(song -> System.out.println(song));

    }

    public static List<Song> getSongsListByMatch(String match) {

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

        // improve this
        data.forEach(song -> {
            String album = song.getAlbum() != null ? song.getAlbum().trim().toLowerCase() : "";
            String artist = song.getArtist() != null ? song.getArtist().trim().toLowerCase() : "";
            String title = song.getTitle() != null ? song.getTitle().trim().toLowerCase() : "";
            if (pattern.matcher(album).matches() || pattern.matcher(artist).matches() || pattern.matcher(title).matches()) {
                list.add(song);
            }
        });
        return list;
    }

}
