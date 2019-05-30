package com.foxminded.obotezatu;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		try {
			RacerRepository qualificationRace = new RacerRepository();
			List<Racer> racersInfo = qualificationRace.readRacerInfo();
			Collections.sort(racersInfo);
			int i = 1;
			racersInfo.stream().map(mapper).forEach(element ->{print(i,element); i++;});
			/*for (Racer racer : racersInfo) {
				String formattedOutput = String.format("%3d. %-17s | %-25s | %s", i, racer.getName(), racer.getTeam(), getLapTime(racer));
				System.out.println(formattedOutput);
				if (i == 15) {
					System.out.println("--------------------------------------------------------------");
				}
				i++;
			}*/
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
	
	private static void print(int i, Racer racer) {
		String formattedOutput = String.format("%3d. %-17s | %-25s | %s", i, racer.getName(), racer.getTeam(), getLapTime(racer));
		System.out.println(formattedOutput);
		
	}

}
