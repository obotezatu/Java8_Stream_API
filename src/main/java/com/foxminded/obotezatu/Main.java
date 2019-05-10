package com.foxminded.obotezatu;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		try {
			List<Racer> racersInfo = QualificationRace.readRacerInfo();
			Collections.sort(racersInfo, new RacerComparator());
			int i = 1;
			for (Racer racer : racersInfo) {
				String formattedOutput = String.format("%3d. %-17s | %-25s | %s", i, racer.getName(), racer.getTeam(), getLapTime(racer));
				System.out.println(formattedOutput);
				if (i == 15) {
					System.out.println("--------------------------------------------------------------");
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getLapTime(Racer racer) {
		Duration duration = Duration.between(racer.getStartLap(), racer.getEndLap());
		long minutes = duration.toMinutes();
		long secs = duration.minusMinutes(minutes).getSeconds();
		long millis = duration.minusMinutes(minutes).minusSeconds(secs).toMillis();
		return String.format("%02d:%02d.%03d", minutes, secs, millis);
	}

}
