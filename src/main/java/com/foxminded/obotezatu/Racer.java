package com.foxminded.obotezatu;

import java.time.Duration;

public class Racer  {

	private String id;
	private String name;
	private String team;
	private Duration bestLapTime;

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

	public Duration getBestLapTime() {
		return bestLapTime;
	}

	public void setBestLapTime(Duration lapTime) {
		this.bestLapTime = lapTime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Racer racer = (Racer) obj;
		return id.equals(racer.id) && (name.equals(racer.getName())) && (team.equals(racer.getTeam())) && (bestLapTime.equals(racer.getBestLapTime()));
	}
}
