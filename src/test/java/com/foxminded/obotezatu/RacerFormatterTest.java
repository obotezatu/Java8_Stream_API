package com.foxminded.obotezatu;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

public class RacerFormatterTest {

	private List<Racer> racers;

	@Before
	public void setUp() {
		racers = new RacerRepositoryTest().createTestList();
	}

	@Test
	public void testGivenTwoRacersAndLimitOne_whenFormat_thenFirstPlayerInTopRacers() {
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
