package com.foxminded.obotezatu;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class Racer {
	
	private String id;
	private String name;
	//private String lastName;
	private String team;
	private LocalDateTime startLap;
	private LocalDateTime endLap;
	
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
	/*public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}*/
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public LocalDateTime getStartLap() {
		return startLap;
	}
	public void setStartLap(LocalDateTime startLap) {
		this.startLap = startLap;
	}
	public LocalDateTime getEndLap() {
		return endLap;
	}
	public void setEndLap(LocalDateTime endLap) {
		this.endLap = endLap;
	}
}
