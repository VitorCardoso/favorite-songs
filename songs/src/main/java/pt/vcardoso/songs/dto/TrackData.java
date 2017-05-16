package pt.vcardoso.songs.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackData implements Serializable {

    private static final long serialVersionUID = 1L;

    public TrackData() {
    }

    private String album_title;

    private String artist_name;

    private String track_title;

    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getTrack_title() {
        return track_title;
    }

    public void setTrack_title(String track_title) {
        this.track_title = track_title;
    }

    @Override
    public String toString() {
        return "TrackData [album_title=" + album_title + ", artist_name=" + artist_name + ", track_title=" + track_title + "]";
    }

}
