package pt.vcardoso.songs.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class FavoriteSong implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String userId;
	private String songId;

}
