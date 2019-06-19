package com.foxminded.obotezatu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RacerRepositoryTest {

	private RacerRepository racerRepository;
	private List<Racer> racers;

	@Before
	public void setUp() {
		racerRepository = new RacerRepository();
		racers = racerRepository.readRacers();
	}

	@Test
	public void testReadRacersListSize() {
		Path ABBREVIATION_FILE_PATH;
		try {
			ABBREVIATION_FILE_PATH = Paths.get(getClass().getClassLoader().getResource("abbreviations.txt").toURI());
			long expected = Files.lines(ABBREVIATION_FILE_PATH).count();

			assertEquals(expected, racers.size());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	@Test(expected = NullPointerException.class)
	public void testReadRacersWrongFileName() {
		try {
			Path ABBREVIATION_FILE_PATH = Paths.get(getClass().getClassLoader().getResource("abbr.txt").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
