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

	@Test(expected = NullPointerException.class)
	public void testReadRacersWrongFileName() {
		try {
			Path abbreviationPath = Paths.get(getClass().getClassLoader().getResource("abbr.txt").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
