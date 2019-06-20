package com.foxminded.obotezatu;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args){
		List<Racer> racersInfo = new RacerRepository().readRacers();
		RacerFormatter racerFormatter = new RacerFormatter();
		System.out.println(racerFormatter.format(racersInfo));
	}
}
