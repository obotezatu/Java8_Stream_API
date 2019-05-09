package com.foxminded.obotezatu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		try {
			File file = new File("./src/main/resources/outputRacer.log");
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			List<Racer> racersInfo = QualificationRace.readRacerInfo();
			Collections.sort(racersInfo, new RacerComparator());
			int i = 1;
			for (Racer racer : racersInfo) {
				String formattedOutput = String.format("%3d. %-17s | %-25s | %s", i, racer.getName(), racer.getTeam(), getLapTime(racer));
				System.out.println(formattedOutput);
				printWriter.println(formattedOutput);
				if (i == 15) {
					System.out.println("--------------------------------------------------------------");
					printWriter.println("--------------------------------------------------------------");
				}
				i++;
			}
			printWriter.close();
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
