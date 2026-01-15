package com.pragmatic.jefflangr.units;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.pragmatic.jefflangr.units.Location0.Direction;

public class Location1Test {
	@Test
	void testYCoordinateWhenMovingNorth() {
		assertEquals(
			new Location1(0, 43, Direction.North), 
			new Location1(0, 0, Direction.North).move(43)
		);
	}

	@Test
	void testYCoordinateWhenMovingSouth() {
		assertEquals(
			new Location1(-2, -4, Direction.South), 
			new Location1(-2, 5, Direction.South).move(9)
		);
	}

	@Test
	void testXCoordinateWhenMovingEast() {
		assertEquals(
			new Location1(3, 0, Direction.East), 
			new Location1(-2, 0, Direction.East).move(5)
		);
	}

	@Test
	void testXCoordinateWhenMovingWest() {
		assertEquals(
			new Location1(-14, 5, Direction.West), 
			new Location1(-2, 5, Direction.West).move(12)
		);
	}

	@Nested
	class DistanceCalculation {
		// direction is required to construct Location1 objects, but irrelevant to most of the tests
		final static Direction DEFAULT_DIRECTION = Direction.East; 
		static final double DELTA = 0.0001;

		@Test
		@DisplayName("location A & location B are at the same position")
		void testZeroDistances() {
			Location1 location = new Location1(8, 8, DEFAULT_DIRECTION);

			double expectedDistance = 0.0;
			double actualDistance = location.getDistanceFromOneLocationToAnother1(8, 8);

			assertEquals(expectedDistance, actualDistance, DELTA);
		}

		@Test
		@DisplayName("only the x-axis/coordinates changed")
		void testHorizontalDistance() {
			Location1 location = new Location1(0, 0, DEFAULT_DIRECTION);
			
			double expectedDistance = 10.0;
			double actualDistance0 = location.getDistanceFromOneLocationToAnother1(10, 0);
			double actualDistance1 = location.getDistanceFromOneLocationToAnother1(-10, 0);

			assertEquals(expectedDistance, actualDistance0, DELTA);
			assertEquals(expectedDistance, actualDistance1, DELTA);
		}

		@Test
		@DisplayName("only the y-axis/coordinates changed")
		void testVerticalDistance() {
			Location1 location = new Location1(0, 0, DEFAULT_DIRECTION);
			
			double expectedDistance = 5.0;
			double actualDistance0 = location.getDistanceFromOneLocationToAnother1(0, 5);
			double actualDistance1 = location.getDistanceFromOneLocationToAnother1(0, -5);

			assertEquals(expectedDistance, actualDistance0, DELTA);
			assertEquals(expectedDistance, actualDistance1, DELTA);
		}

		@Test
		@DisplayName("distance btwn 2 locations should be symmetrical (A -> B == B -> A)")
		void testSymmetryOfDistance() {
			Location1 location = new Location1(1, 2, DEFAULT_DIRECTION);

			double expectedDistance = location.getDistanceFromOneLocationToAnother1(4, 6);
			double actualDistance = Math.sqrt(Math.pow(1 - 4, 2) + Math.pow(2 - 6, 2));

			assertEquals(expectedDistance, actualDistance, DELTA);
		}

		@Test
		@DisplayName("ensure squaring negative coordinates is handled correctly")
		void testNegativeCoordinates() {
			// From location-A (-2, -2) to location-B (-5, -6)
      // DeltaX = -3, DeltaY = -4 -> Distance should be 5.0
			Location1 location = new Location1(-2, -2, Direction.West);

			double expectedDistance = 5.0;
			double actualDistance = location.getDistanceFromOneLocationToAnother1(-5, -6);

			assertEquals(expectedDistance, actualDistance, DELTA);
		}

		@Test
		@DisplayName("using a delta to account for floating-point math")
		void testNonIntegerDistance() {
			// from (0,0) to (1,1) -> sqrt(1^2 + 1^2) = sqrt(2) ≈ 1.4142
			Location1 location = new Location1(0, 0, DEFAULT_DIRECTION);

			double expectedDistance = Math.sqrt(2);
			double actualDistance = location.getDistanceFromOneLocationToAnother1(1, 1);

			assertEquals(expectedDistance, actualDistance, DELTA);
		}

		@Test
		@DisplayName("Diagonal Distance: 3-4-5 Triangle (Standard Pythagorean Trip)")
		void testPythagoreanTriple() {
			// from (0,0) to (3,4) should be exactly 5.0
			Location1 location = new Location1(0, 0, DEFAULT_DIRECTION);

			double expectedDistance = 5.0;
			double actualDistance = location.getDistanceFromOneLocationToAnother1(3, 4);

			assertEquals(expectedDistance, actualDistance, DELTA);
		}
	}
}
