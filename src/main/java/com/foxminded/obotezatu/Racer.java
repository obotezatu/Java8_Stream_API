package com.foxminded.obotezatu;

import java.time.LocalDateTime;

public class Racer  {

	private String id;
	private String name;
	private String team;
	private LapTime bestLapTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public LapTime getLapTime() {
		return bestLapTime;
	}

	public void setLapTime(LocalDateTime start, LocalDateTime end) {
		this.bestLapTime = new LapTime(start, end);
	}
}
