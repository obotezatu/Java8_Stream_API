package com.foxminded.obotezatu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QualificationRace {

	public static List<Racer> readRacerInfo() throws IOException {
		List<Racer> racers = new ArrayList<>();
		Map<String, Instant> startTime = readTime("./src/main/resources/start.log");
		Map<String, Instant> endTime = readTime("./src/main/resources/end.log");
		racers = Files.lines(Paths.get("./src/main/resources/abbreviations.txt"))
				.map(QualificationRace::parseAbbreviation).map(racer -> {
					String racerID = racer.getId();
					racer.setStartLap(startTime.get(racerID));
					racer.setEndLap(endTime.get(racerID));
					return racer;
				}).collect(Collectors.toList());
		return racers;
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
}
