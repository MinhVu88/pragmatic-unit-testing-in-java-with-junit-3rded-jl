package com.pragmatic.jefflangr.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pragmatic.jefflangr.units.Location0.Direction;

public class DestinationListTest {
	static final Location1 DESTINATION_0 = new Location1(1, 2, Direction.North);
	static final Location1 DESTINATION_1 = new Location1(1, 3, Direction.East);
	private DestinationList list;

	@BeforeEach
	void init() {
		list = new DestinationList();
	}

	@Test
	void testAddingZeroDestinations() {
		assertTrue(list.getLocations().isEmpty());
	}

	@Test
	void testAddingMultipleDestinations0() {
		// approach #1 (locally declared test objects)
		var destination0 = new Location1(1, 2, Direction.North);
		var destination1 = new Location1(1, 3, Direction.East);

		list.addDistinct(destination0);
		list.addDistinct(destination1);
		
		var expectedDestinations = List.of(destination0, destination1);
		var actualDestinations = list.getLocations();
		
		assertEquals(expectedDestinations, actualDestinations);
	}

	@Test
	void testAddingMultipleDestinations1() {
		// approach #2 (globally accessible test objects)
		list.addDistinct(DESTINATION_0);
		list.addDistinct(DESTINATION_1);

		var expectedDestinations = List.of(DESTINATION_0, DESTINATION_1);
		var actualDestinations = list.getLocations();

		assertEquals(expectedDestinations, actualDestinations);
	}

	@Test
	void testAddingMultipleDestinations2() {
		// approach #3 (raw inline declarations)
		// Arrange step
		list.addDistinct(new Location1(0, 0, Direction.East));
		list.addDistinct(new Location1(3, 3, Direction.North));

		// Act step
		list.addDistinct(new Location1(0, 0, Direction.East));

		// Assert step
		assertEquals(
			List.of(
				new Location1(0, 0, Direction.East), 
				new Location1(3, 3, Direction.North)
			),
			list.getLocations()
		);
	}

	@Test
	void testTargetedLocationsAtUpdatedCoordinates() {
		var targetedDirection = Direction.East;
		list.addDistinct(new Location1(1, 0, targetedDirection));
		list.addDistinct(new Location1(3, 2, Direction.North));
		list.addDistinct(new Location1(5, 4, targetedDirection));

		list.moveTowards(targetedDirection, 2, 3);

		var expectedDestinations = List.of(
			new Location1(2, 3, targetedDirection),
			new Location1(3, 2, Direction.North),
			new Location1(2, 3, targetedDirection)
		);
		var actualDestinations = list.getLocations();
		assertEquals(expectedDestinations, actualDestinations);
	}

	@Test
	void testLocationExceedingMaxAllowedDistance() {
		int maxAllowedDistance = 9;
		list.addDistinct(new Location1(0, 5, Direction.North));
		list.addDistinct(new Location1(0, 10, Direction.North));
		list.addDistinct(new Location1(0, 15, Direction.North));

		list.discardExceedingLocation0(0, 0, maxAllowedDistance);
		list.discardExceedingLocation1(0, 0, maxAllowedDistance);

		assertEquals(
			List.of(new Location1(0, 5, Direction.North)), 
			list.getLocations()
		);
	}
}
