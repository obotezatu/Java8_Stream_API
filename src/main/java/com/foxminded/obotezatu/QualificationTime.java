package com.foxminded.obotezatu;

import java.time.Instant;

public class QualificationTime {
	
	private String racerID;
	private Instant time;
	
	public String getRacerID() {
		return racerID;
	}
	public Instant getTime() {
		return time;
	}
	public void setRacerID(String racerID) {
		this.racerID = racerID;
	}
	public void setTime(Instant time) {
		this.time = time;
	}
}
