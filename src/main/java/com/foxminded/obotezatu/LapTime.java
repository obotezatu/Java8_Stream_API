package com.foxminded.obotezatu;

import java.time.Duration;
import java.time.LocalDateTime;

public class LapTime {

	private LocalDateTime start;
	private LocalDateTime end;

	public LapTime(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public LocalDateTime getStartLap() {
		return start;
	}

	public LocalDateTime getEndLap() {
		return end;
	}

	public Duration getLapDuration() {
		return Duration.between(getStartLap(), getEndLap());
	}
}
