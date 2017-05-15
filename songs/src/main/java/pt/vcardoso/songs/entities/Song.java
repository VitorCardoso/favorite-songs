package pt.vcardoso.songs.entities;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Song implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;
	private String title;
	private String artist;
	private String album;

	public Song() {
		this.uuid = UUID.randomUUID().toString();
	}

	public Song(String title, String artist, String album) {
		this.uuid = UUID.randomUUID().toString();
		this.title = title;
		this.artist = artist;
		this.album = album;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Song [uuid=" + uuid + ", title=" + title + ", artist=" + artist + ", album=" + album + "]";
	}
}
