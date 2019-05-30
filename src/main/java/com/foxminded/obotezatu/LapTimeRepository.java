package com.foxminded.obotezatu;

import java.time.Duration;
import java.time.Instant;

public class LapTimeRepository {

	private Instant startLap;
	private Instant endLap;

	public LapTimeRepository(Instant startLap, Instant endLap) {
		this.startLap = startLap;
		this.endLap = endLap;
	}

	public Instant getStartLap() {
		return startLap;
	}

	public Instant getEndLap() {
		return endLap;
	}

	public Duration getLapDuration() {
		return Duration.between(getStartLap(), getEndLap());
	}
}
