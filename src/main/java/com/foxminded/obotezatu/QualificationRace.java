package com.foxminded.obotezatu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QualificationRace {

	private final static String START_FILE_PATH = "./src/main/resources/start.log";
	private final static String END_FILE_PATH = "./src/main/resources/end.log";
	private final static String ABBREVIATION_FILE_PATH = "./src/main/resources/abbreviations.txt";

	public static List<Racer> readRacerInfo() throws IOException {
		return Files.lines(Paths.get(ABBREVIATION_FILE_PATH)).map(QualificationRace::parseAbbreviation)
				.map(QualificationRace::writeRacer).collect(Collectors.toList());
	}

	private static Map<String, Instant> readTime(String path) throws IOException {
		return Files.lines(Paths.get(path)).map(QualificationRace::parseTime).collect(Collectors.toMap(
				qualificationTime -> qualificationTime[0], qualificationTime -> Instant.parse(qualificationTime[1])));
	}

	private static Racer parseAbbreviation(String line) {
		String[] fields = line.split("_");
		Racer racer = new Racer();
		racer.setId(fields[0]);
		racer.setName(fields[1]);
		racer.setTeam(fields[2]);
		return racer;
	}

	private static String[] parseTime(String line) {
		String[] qualificationTime = new String[2];
		qualificationTime[0] = line.substring(0, 3);
		qualificationTime[1] = line.substring(3).replace('_', 'T').concat("Z");
		return qualificationTime;
	}

	private static Racer writeRacer(Racer racer) {
		Map<String, Instant> startTime;
		Map<String, Instant> endTime;
		try {
			startTime = readTime(START_FILE_PATH);
			endTime = readTime(END_FILE_PATH);
			String racerID = racer.getId();
			racer.setStartLap(startTime.get(racerID));
			racer.setEndLap(endTime.get(racerID));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return racer;
	}
}
