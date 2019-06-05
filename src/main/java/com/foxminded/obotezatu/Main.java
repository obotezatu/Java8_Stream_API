package com.foxminded.obotezatu;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args){
		List<Racer> racersInfo = new RacerRepository().readRecers();
		Collections.sort(racersInfo, (currentRacer,nextRacer)->{
			return currentRacer.getLapTime().getLapDuration().compareTo(nextRacer.getLapTime().getLapDuration());
			});
		AtomicInteger count = new AtomicInteger(1);
		racersInfo.stream().limit(15).forEach(racer ->print(racer,count));
		System.out.println("--------------------------------------------------------------");
		racersInfo.stream().skip(15).forEach(racer ->print(racer,count));
	}

	private static String getFormattedLapTime(Racer racer) {
		Duration duration = racer.getLapTime().getLapDuration();
		long minutes = duration.toMinutes();
		long secs = duration.minusMinutes(minutes).getSeconds();
		long millis = duration.minusMinutes(minutes).minusSeconds(secs).toMillis();
		return String.format("%02d:%02d.%03d", minutes, secs, millis);
	}
	
	private static void print(Racer racer, AtomicInteger count) {
		String formattedOutput = String.format("%3d. %-17s | %-25s | %s",count.getAndIncrement(), racer.getName(), racer.getTeam(), getFormattedLapTime(racer));
		System.out.println(formattedOutput);
		
	}
}
