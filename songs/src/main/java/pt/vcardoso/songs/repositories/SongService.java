package pt.vcardoso.songs.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

	@Autowired
	private SongRepository repo;
}
