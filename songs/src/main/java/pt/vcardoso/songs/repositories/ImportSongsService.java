package pt.vcardoso.songs.repositories;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.vcardoso.songs.dto.JsonData;
import pt.vcardoso.songs.entities.Song;

@Repository
public class ImportSongsService {

	private static final String URL_TO_IMPORT = "http://freemusicarchive.org/recent.json";

	public List<Song> importSongs() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonData data = mapper.readValue(getHTMLContent(), JsonData.class);
		System.out.println(data);
		return to(data);
	}

	private List<Song> to(JsonData data) {
		List<Song> result = new ArrayList<>();
		data.getaTracks().forEach(track -> {
			result.add(new Song(track.getTrack_title(), track.getArtist_name(), track.getAlbum_title()));
		});

		return result;
	}

	private static String getHTMLContent() throws Exception {

		StringBuilder result = new StringBuilder();
		URL url = new URL(URL_TO_IMPORT);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}
}
