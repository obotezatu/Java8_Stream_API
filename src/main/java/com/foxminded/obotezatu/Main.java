package com.foxminded.obotezatu;

import java.io.IOException;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		try {
			List<Racer> racersInfo = QualificationRace.readRacerInfo().sort((racer1,racer2)->{
				long first = Duration.between(racer1.getStartLap().toLocalTime(), racer1.getEndLap().toLocalTime()).toMillis();
				long second = Duration.between(racer2.getStartLap().toLocalTime(), racer2.getEndLap().toLocalTime()).toMillis();
				
				
			});
			int i = 1;
			for (Racer racer : racersInfo) {
				System.out.println(String.format("%3d. %-17s | %-25s | %s", i, racer.getName(), racer.getTeam(),
						racer.getEndLap().toLocalTime().toString()));
				i++;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
