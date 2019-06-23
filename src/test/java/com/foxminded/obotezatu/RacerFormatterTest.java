package com.foxminded.obotezatu;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RacerFormatterTest {

	private RacerRepository racerRepository;
	private List<Racer> racers;

	@Before
	public void setUp() {
		racerRepository = new RacerRepository();
		racers = racerRepository.readRacers();
	}

	@Test
	public void testRacersSort() {
		Collections.sort(racers, (currentRacer, nextRacer) -> {
			return currentRacer.getLapTime().getLapDuration().compareTo(nextRacer.getLapTime().getLapDuration());
		});
		String expectedFirst = "Sebastian Vettel";
		String expectedLast = "Kevin Magnussen";
		String actualFirst = racers.get(0).getName();
		String actualLast = racers.get(racers.size() - 1).getName();

		assertEquals(expectedFirst, actualFirst);
		assertEquals(expectedLast, actualLast);
	}
}
