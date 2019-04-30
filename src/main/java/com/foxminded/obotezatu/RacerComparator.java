package com.foxminded.obotezatu;

import java.time.Duration;
import java.util.Comparator;

public class RacerComparator implements Comparator<Racer> {

	@Override
	public int compare(Racer currentRacer, Racer nextRacer) {
		long currentRacerLapDuration = Duration.between(currentRacer.getEndLap(), currentRacer.getStartLap()).toMillis();
		long nextRacerLapDuration = Duration.between(nextRacer.getEndLap(), nextRacer.getStartLap()).toMillis();
		return (int) (nextRacerLapDuration - currentRacerLapDuration);
	}
}
