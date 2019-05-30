package com.foxminded.obotezatu;

import java.time.Instant;

public class Racer implements Comparable<Racer> {

	private String id;
	private String name;
	private String team;
	private LapTimeRepository lapTimes;	
	
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
	
	public LapTimeRepository getLapTimes() {
		return lapTimes;
	}

	public void setLapTimes(Instant startLap, Instant endLap) {
		this.lapTimes = new LapTimeRepository(startLap, endLap);
	}
	
	@Override
	public int compareTo(Racer nextRacer) {
		return  this.lapTimes.getLapDuration().compareTo(nextRacer.lapTimes.getLapDuration());
	}

}
