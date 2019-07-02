package com.foxminded.obotezatu;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RacerRepositoryTest {

	private RacerRepository racerRepository;

	@Before
	public void setUp() {
		racerRepository = new RacerRepository();
	}

	@Test
	public void testReadRacersListCreationAndSorting() {
		List<Racer> expected = createTestList();

		List<Racer> actual = racerRepository.readRacers();

		assertEquals(expected, actual);
	}

	public List<Racer> createTestList() {
		List<Racer> racers = new ArrayList<>();
		Racer racerSebastian = new Racer();
		racerSebastian.setId("SVF");
		racerSebastian.setName("Sebastian Vettel");
		racerSebastian.setTeam("FERRARI");
		racerSebastian.setBestLapTime(Duration.ofSeconds(64).plus(Duration.ofNanos(415000000)));
		racers.add(racerSebastian);
		Racer racerDaniel = new Racer();
		racerDaniel.setId("DRR");
		racerDaniel.setName("Daniel Ricciardo");
		racerDaniel.setTeam("RED BULL RACING TAG HEUER");
		racerDaniel.setBestLapTime(Duration.ofSeconds(72).plus(Duration.ofNanos(13000000)));
		racers.add(racerDaniel);
		return racers;
	}
}
