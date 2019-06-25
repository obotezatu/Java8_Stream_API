package com.foxminded.obotezatu;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RacerFormatter {
	
	public String format(List<Racer> racers, int limit) {
		StringBuilder formattedResult = new StringBuilder();
		racers = new RacerRepository().readRacers();
		AtomicInteger count = new AtomicInteger(1);
		racers.stream().limit(limit).forEach(racer ->formattedResult.append(formatOutput(racer,count)));
		if (count.get() >= limit) {
			formattedResult.append("--------------------------------------------------------------\n");
		}
		racers.stream().skip(limit).forEach(racer ->formattedResult.append(formatOutput(racer,count)));
		return formattedResult.toString();
	}

	private static String getFormattedLapTime(Racer racer) {
		Duration duration = racer.getBestLapTime();
		long minutes = duration.toMinutes();
		long secs = duration.minusMinutes(minutes).getSeconds();
		long millis = duration.minusMinutes(minutes).minusSeconds(secs).toMillis();
		return String.format("%02d:%02d.%03d", minutes, secs, millis);
	}
	
	private static String formatOutput(Racer racer, AtomicInteger count) {
		return String.format("%3d. %-17s | %-25s | %s%n",count.getAndIncrement(), racer.getName(), racer.getTeam(), getFormattedLapTime(racer));
	}
}
