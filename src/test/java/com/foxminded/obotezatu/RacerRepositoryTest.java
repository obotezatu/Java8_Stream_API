package com.foxminded.obotezatu;

import java.util.List;

public class RacerRepositoryTest {

	public static void main(String[] args) {
		RacerRepository rcer = new RacerRepository();
		List<Racer> listRacer = rcer.readRecers();
		listRacer.stream().forEach(racer->System.out.println(racer.getName()));
	}
}
