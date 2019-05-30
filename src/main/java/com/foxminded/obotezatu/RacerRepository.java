package com.foxminded.obotezatu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerRepository {

	private final Path START_FILE_PATH = getResourceFile("start.log");
	private final Path END_FILE_PATH = getResourceFile("end.log");
	private final Path ABBREVIATION_FILE_PATH = getResourceFile("abbreviations.txt");

	public List<Racer> readRacerInfo() throws IOException {
		return Files.lines(ABBREVIATION_FILE_PATH).map(new RacerRepository()::parseAbbreviation)
				.map(new RacerRepository()::writeRacer).collect(Collectors.toList());
	}

	private Map<String, Instant> readTime(Path path) throws IOException {
		return Files.lines(path).map(new RacerRepository()::parseTime).collect(Collectors.toMap(
				qualificationTime -> qualificationTime[0], qualificationTime -> Instant.parse(qualificationTime[1])));
	}

	private Racer parseAbbreviation(String line) {
		String[] fields = line.split("_");
		Racer racer = new Racer();
		racer.setId(fields[0]);
		racer.setName(fields[1]);
		racer.setTeam(fields[2]);
		return racer;
	}

	private String[] parseTime(String line) {
		String[] qualificationTime = new String[2];
		qualificationTime[0] = line.substring(0, 3);
		qualificationTime[1] = line.substring(3).replace('_', 'T').concat("Z");
		return qualificationTime;
	}

	private Racer writeRacer(Racer racer) {
		Map<String, Instant> startTime;
		Map<String, Instant> endTime;
		try {
			startTime = readTime(START_FILE_PATH);
			endTime = readTime(END_FILE_PATH);
			String racerID = racer.getId();
			racer.setLapTimes(startTime.get(racerID), endTime.get(racerID));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return racer;
	}

	private Path getResourceFile(String resourceFileName) {
		Path path = null;
		try {
			path = Paths.get(getClass().getClassLoader().getResource(resourceFileName).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return path;
	}
}
