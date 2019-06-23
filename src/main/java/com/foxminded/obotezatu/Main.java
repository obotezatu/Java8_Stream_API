package com.foxminded.obotezatu;

import java.util.List;

public class Main {

	public static void main(String[] args){
		List<Racer> racersInfo = new RacerRepository().readRacers();
		RacerFormatter racerFormatter = new RacerFormatter();
		System.out.println(racerFormatter.format(racersInfo));
	}
}
