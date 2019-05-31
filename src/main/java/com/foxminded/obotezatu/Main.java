package com.foxminded.obotezatu;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) {
		try {
			List<Racer> racersInfo = new RacerRepository().readRacerInfo();
			Collections.sort(racersInfo);
			AtomicInteger count = new AtomicInteger(1);
			racersInfo.stream().limit(15).forEach(racer ->print(racer,count));
			System.out.println("--------------------------------------------------------------");
			racersInfo.stream().skip(15).forEach(racer ->print(racer,count));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getLapTime(Racer racer) {
		Duration duration = racer.getLapTimes().getLapDuration();
		long minutes = duration.toMinutes();
		long secs = duration.minusMinutes(minutes).getSeconds();
		long millis = duration.minusMinutes(minutes).minusSeconds(secs).toMillis();
		return String.format("%02d:%02d.%03d", minutes, secs, millis);
	}
	
	private static void print(Racer racer, AtomicInteger count) {
		String formattedOutput = String.format("%3d. %-17s | %-25s | %s",count.getAndIncrement(), racer.getName(), racer.getTeam(), getLapTime(racer));
		System.out.println(formattedOutput);
		
	}
}
