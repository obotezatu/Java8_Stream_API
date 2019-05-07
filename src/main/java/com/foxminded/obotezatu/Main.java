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
		/*final long MILLIS_PER_SEC = 1000;
		final long MINUTES_PER_HOUR = 60;
		final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SEC;
		final long MILLIS_PER_HOUR = MILLIS_PER_MINUTE * MINUTES_PER_HOUR * MILLIS_PER_SEC;*/

		//long duration = Duration.between(racer.getStartLap(), racer.getEndLap()).toMillis();
		Duration duration = Duration.between(racer.getStartLap(), racer.getEndLap());
		long minutes = duration.toMinutes();
		long secs = duration.minusMinutes(minutes).getSeconds();
		long millis = duration.minusMinutes(minutes).minusSeconds(secs).toMillis();
		//List <TemporalUnit> tempUnit = dur.getUnits();
		/*long minutes = ((duration % MILLIS_PER_HOUR) / MILLIS_PER_MINUTE);
		long secs = (duration % MILLIS_PER_MINUTE) / MILLIS_PER_SEC;
		long millis = (duration % MILLIS_PER_SEC);*/
		return String.format("%02d:%02d.%03d", minutes, secs, millis);
	}

}
