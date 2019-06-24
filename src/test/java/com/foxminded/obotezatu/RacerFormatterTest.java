package com.foxminded.obotezatu;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static java.lang.System.lineSeparator;
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
		String expectedLast = "Daniel Ricciardo";
		String actualFirst = racers.get(0).getName();
		String actualLast = racers.get(racers.size() - 1).getName();

		assertEquals(expectedFirst, actualFirst);
		assertEquals(expectedLast, actualLast);
	}

	@Test
	public void testRacersFormat() {
		RacerFormatter racerFormatter = new RacerFormatter();
		StringBuilder expected = new StringBuilder();
		expected.append("  1. Sebastian Vettel  | FERRARI                   | 01:04.415").append(lineSeparator())
				.append("  2. Daniel Ricciardo  | RED BULL RACING TAG HEUER | 01:12.013").append(lineSeparator());
		String actual = racerFormatter.format(racers);
		assertEquals(expected.toString(), actual);
	}
}
