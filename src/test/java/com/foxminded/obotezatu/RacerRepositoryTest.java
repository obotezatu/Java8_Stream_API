package com.foxminded.obotezatu;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

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
		/*
		 * Racer racer1 = new Racer(); Duration duration = null; racer1.setId("1");
		 * racer1.setName("Sebastian Vettel"); racer1.setTeam("FERRARI");
		 * duration.ofSeconds(72); duration.ofNanos(13000000);
		 * racer1.setBestLapTime(duration);
		 */
		Gson gson = new Gson();
		Racer racer1 = racers.get(0);
		try {
			gson.toJson(racer1,new FileWriter("D:\\Books\\java\\Java 2018 USM\\Tasks\\Java8_Stream_API\\src\\test\\resources\\racer.json"));
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(racer1.getBestLapTime(), racers.get(0).getBestLapTime());
		assertEquals(racer1.getName(), racers.get(0).getBestLapTime());
	}
}
