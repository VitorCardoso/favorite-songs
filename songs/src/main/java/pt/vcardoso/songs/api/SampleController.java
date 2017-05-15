package pt.vcardoso.songs.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.vcardoso.songs.repositories.SongService;

@RestController
@RequestMapping("/api")
public class SampleController {

	@Autowired
	private SongService songService;

	@GetMapping("/")
	public String index() {
		return "Song service REST API: ok";
	}

}