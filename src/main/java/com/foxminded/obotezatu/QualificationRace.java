package com.foxminded.obotezatu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QualificationRace {
	
	public static void readTime() throws IOException {
		
		List<Racer> racerList = Files.lines(Paths.get("./src/main/resources/start.log")).map(QualificationRace::transform).collect(Collectors.toList());
	}
	
	private static  Racer transform(String line) {
		Racer racer = new Racer();
		
		racer.setId(line.substring(0, 3));
		String time = line.substring(3).replace('_', 'T').concat("Z");
		Instant instant = Instant.parse(time);
		return racer;
	}
}
