package com.foxminded.obotezatu;

import static java.lang.System.lineSeparator;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RacerFormatter {
	
	public String format(List<Racer> racers, int limit) {
		StringBuilder formattedResult = new StringBuilder();
		AtomicInteger count = new AtomicInteger(1);
		racers.stream().limit(limit).forEach(racer ->formattedResult.append(formatOutput(racer,count)));
		if (count.get() >= limit) {
			formattedResult.append("--------------------------------------------------------------").append(lineSeparator());
		}
		racers.stream().skip(limit).forEach(racer ->formattedResult.append(formatOutput(racer,count)));
		return formattedResult.toString();
	}

	private String getFormattedLapTime(Racer racer) {
		Duration duration = racer.getBestLapTime();
		long minutes = duration.toMinutes();
		long seconds = duration.minusMinutes(minutes).getSeconds();
		long millis = duration.minusMinutes(minutes).minusSeconds(seconds).toMillis();
		return String.format("%02d:%02d.%03d", minutes, seconds, millis);
	}
	
	private  String formatOutput(Racer racer, AtomicInteger count) {
		return String.format("%3d. %-17s | %-25s | %s%s",count.getAndIncrement(), racer.getName(), racer.getTeam(), getFormattedLapTime(racer),lineSeparator());
	}
}
