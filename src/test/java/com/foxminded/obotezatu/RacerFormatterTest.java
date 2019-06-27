package com.foxminded.obotezatu;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

public class RacerFormatterTest {

	private List<Racer> racers;

	@Before
	public void setUp() {
		racers = new ArrayList<>();
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
	}

	@Test
	public void testRacersFormat() {
		RacerFormatter racerFormatter = new RacerFormatter();
		StringBuilder expected = new StringBuilder();
		int limit = 1;
		expected.append("  1. Sebastian Vettel  | FERRARI                   | 01:04.415").append(lineSeparator())
				.append("--------------------------------------------------------------").append(lineSeparator())
				.append("  2. Daniel Ricciardo  | RED BULL RACING TAG HEUER | 01:12.013").append(lineSeparator());
		String actual = racerFormatter.format(racers,limit);
		assertEquals(expected.toString(), actual);
	}
}
