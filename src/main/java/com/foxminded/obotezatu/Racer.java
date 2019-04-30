package com.foxminded.obotezatu;

import java.time.Instant;

public class Racer {
	
	private String id;
	private String name;
	private String team;
	private Instant startLap;
	private Instant endLap;
	
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
	public Instant getStartLap() {
		return startLap;
	}
	public void setStartLap(Instant startLap) {
		this.startLap = startLap;
	}
	public Instant getEndLap() {
		return endLap;
	}
	public void setEndLap(Instant endLap) {
		this.endLap = endLap;
	}
}
