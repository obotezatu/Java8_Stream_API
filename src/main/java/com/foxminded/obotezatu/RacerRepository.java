package com.foxminded.obotezatu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerRepository {

	private final Path START_FILE_PATH = getResourceFile("start.log");
	private final Path END_FILE_PATH = getResourceFile("end.log");
	private final Path ABBREVIATION_FILE_PATH = getResourceFile("abbreviations.txt");

	public List<Racer> readRacers() {
		List<Racer> racers = null;
		try {
			racers = Files.lines(ABBREVIATION_FILE_PATH).map(this::parseAbbreviation)
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
		try {
			startTime = readTime(START_FILE_PATH);
			endTime = readTime(END_FILE_PATH);
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
		String dateTimeFormatPattern = "yyyy-MM-dd_HH:mm:ss.SSS";
		ZoneId defaultZoneId = ZoneId.systemDefault();
		DateFormat format = new SimpleDateFormat(dateTimeFormatPattern);
		LocalDateTime parsedDateTime = null;
		try {
			parsedDateTime = format.parse(dateTime).toInstant().atZone(defaultZoneId).toLocalDateTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDateTime;
	}
}
