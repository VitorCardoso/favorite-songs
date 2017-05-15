package pt.vcardoso.songs.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pt.vcardoso.songs.entities.Song;
import pt.vcardoso.songs.repositories.ImportSongsService;

public class ApplicationTest {
	private ImportSongsService service = new ImportSongsService();

	@Test
	public void test() throws Exception {
		List<Song> importedSongs = service.importSongs();
		Assert.assertFalse(importedSongs.isEmpty());
	}
}