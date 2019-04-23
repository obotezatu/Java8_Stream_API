package com.foxminded.obotezatu;

import java.util.Date;

public class Racer {
	
	private String id;
	private String firstName;
	private String lastName;
	private String team;
	private Date startLap;
	private Date endLap;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Date getStartLap() {
		return startLap;
	}
	public void setStartLap(Date startLap) {
		this.startLap = startLap;
	}
	public Date getEndLap() {
		return endLap;
	}
	public void setEndLap(Date endLap) {
		this.endLap = endLap;
	}
}
