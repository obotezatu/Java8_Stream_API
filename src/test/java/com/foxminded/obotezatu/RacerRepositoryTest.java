package com.foxminded.obotezatu;

import java.time.Duration;
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
	public void testReadRacers() {
		Racer expectedRacer = new Racer();
		Duration duration = null;
		expectedRacer.setId("SVF");
		expectedRacer.setName("Sebastian Vettel");
		expectedRacer.setTeam("FERRARI");
		duration = Duration.ofSeconds(64).plus(Duration.ofNanos(415000000));
		expectedRacer.setBestLapTime(duration);
		Racer actualRacer = racers.get(0);
		
		assertEquals(expectedRacer.getId(), actualRacer.getId());
		assertEquals(expectedRacer.getBestLapTime(), actualRacer.getBestLapTime());
		assertEquals(expectedRacer.getName(), actualRacer.getName());
		assertEquals(expectedRacer.getTeam(), actualRacer.getTeam());
	}
}
