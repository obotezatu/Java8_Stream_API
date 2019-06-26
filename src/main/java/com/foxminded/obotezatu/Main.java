package com.foxminded.obotezatu;

import java.util.List;

public class Main {

	public static void main(String[] args){
		List<Racer> racers = new RacerRepository().readRacers();
		int limit = 15;
		RacerFormatter racerFormatter = new RacerFormatter();
		System.out.println(racerFormatter.format(racers, limit));
	}
}
