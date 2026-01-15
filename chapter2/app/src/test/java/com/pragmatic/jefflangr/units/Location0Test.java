package com.pragmatic.jefflangr.units;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pragmatic.jefflangr.units.Location0.Direction;

public class Location0Test {
	@Test
	void testYCoordinateWhenMovingNorth0() {
		// approach #1
		var location = new Location0(0, 0, Direction.North);
		
		location.move(43);
		
		assertEquals(0, location.getX());
		assertEquals(43, location.getY());
		assertEquals(Direction.North, location.getDirection());
	}

	@Test
	void testYCoordinateWhenMovingNorth1() {
		// approach #2
		var expectedLocation = new Location0(0, 43, Direction.North);

		var actualLocation = new Location0(0, 0, Direction.North);
		actualLocation.move(43);

		assertEquals(expectedLocation, actualLocation);
	}

	@Test
	void testYCoordinateWhenMovingSouth() {
		var expectedLocation = new Location0(-2, -4, Direction.South);

		var actualLocation = new Location0(-2, 5, Direction.South);
		actualLocation.move(9);

		assertEquals(expectedLocation, actualLocation);
	}

	@Test
	void testXCoordinateWhenMovingEast() {
		var expectedLocation = new Location0(3, 0, Direction.East);

		var actualLocation = new Location0(-2, 0, Direction.East);
		actualLocation.move(5);

		assertEquals(expectedLocation, actualLocation);
	}

	@Test
	void testXCoordinateWhenMovingWest() {
		var expectedLocation = new Location0(-14, 5, Direction.West);

		var actualLocation = new Location0(-2, 5, Direction.West);
		actualLocation.move(12);

		assertEquals(expectedLocation, actualLocation);
	}
}
