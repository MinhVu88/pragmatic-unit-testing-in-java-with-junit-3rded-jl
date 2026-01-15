package com.pragmatic.jefflangr.units;

import com.pragmatic.jefflangr.units.Location0.Direction;

public record Location1(int x, int y, Direction direction) {
	public Location1 move(int distance) {
		return switch(direction) {
			case North -> new Location1(x, y + distance, direction); 
			case East -> new Location1(x + distance, y, direction); 
			case South -> new Location1(x, y - distance, direction); 
			case West -> new Location1(x - distance, y, direction);
		};
	}

	double getDistanceFromOneLocationToAnother1(int anotherX, int anotherY) {
		return Math.sqrt(
			Math.pow(anotherX - x(), 2) + Math.pow(anotherY - y(), 2)
		);
	}
}
