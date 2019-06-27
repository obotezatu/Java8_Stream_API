package com.foxminded.obotezatu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
		try {
			Map<String, LocalDateTime> start = readTime(getResourceFile(START_FILE));
			Map<String, LocalDateTime> end = readTime(getResourceFile(END_FILE));
			racers = Files.lines(getResourceFile(ABBREVIATION_FILE))
					.map(this::parseAbbreviation)
					.map(racer -> setLapTime(racer, start, end))
					.sorted(Comparator.comparing(Racer::getBestLapTime))
					.collect(Collectors.toList());
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

	private Racer setLapTime(Racer racer, Map<String, LocalDateTime> start, Map<String, LocalDateTime> end) {
		String racerId = racer.getId();
		LapTime lapTime = new LapTime(start.get(racerId), end.get(racerId));
		racer.setBestLapTime(lapTime.getLapDuration());
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
		return LocalDateTime.parse(dateTime, formatter);
	}
}
