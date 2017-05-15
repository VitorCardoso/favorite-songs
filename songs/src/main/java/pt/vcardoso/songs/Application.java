package pt.vcardoso.songs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pt.vcardoso.songs.entities.Song;
import pt.vcardoso.songs.repositories.SongService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private SongService service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Run setup database here");

		// push data here
		service.createSong(new Song("ola", "artist", "album"));
	}

}