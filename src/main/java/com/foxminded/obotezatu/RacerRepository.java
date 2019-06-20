package com.foxminded.obotezatu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerRepository {
	
	private static final String START_FILE = "start.log"; 
	private static final String END_FILE = "end.log";
	private static final String ABBREVIATION_FILE = "abbreviations.txt";
	private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd_HH:mm:ss.SSS";

	public List<Racer> readRacers() {
		List<Racer> racers = null;
		Path abbreviationPath = getResourceFile(ABBREVIATION_FILE);
		try {
			racers = Files.lines(abbreviationPath).map(this::parseAbbreviation)
					.map(this::setBestLapTime).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return racers;
	}

	private Map<String, LocalDateTime> readTime(Path path) throws IOException {
		return Files.lines(path).map(this::parseLine).collect(Collectors.toMap(
				qualificationTime -> qualificationTime[0], qualificationTime -> parseTime(qualificationTime[1])));
	}

	private Racer parseAbbreviation(String line) {
		String[] fields = line.split("_");
		Racer racer = new Racer();
		racer.setId(fields[0]);
		racer.setName(fields[1]);
		racer.setTeam(fields[2]);
		return racer;
	}

	private String[] parseLine(String line) {
		String[] qualificationTime = new String[2];
		qualificationTime[0] = line.substring(0, 3);
		qualificationTime[1] = line.substring(3);
		return qualificationTime;
	}

	private Racer setBestLapTime(Racer racer) {
		Map<String, LocalDateTime> startTime;
		Map<String, LocalDateTime> endTime;
		Path startPath = getResourceFile(START_FILE);
		Path endPath = getResourceFile(END_FILE);
		try {
			startTime = readTime(startPath);
			endTime = readTime(endPath);
			String racerID = racer.getId();
			racer.setLapTime(startTime.get(racerID), endTime.get(racerID));
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

	private LocalDateTime parseTime(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
		return localDateTime;
	}
}
