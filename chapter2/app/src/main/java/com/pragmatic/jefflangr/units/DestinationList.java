package com.pragmatic.jefflangr.units;

import java.util.ArrayList;
import java.util.List;

import com.pragmatic.jefflangr.units.Location0.Direction;

public class DestinationList {
	private List<Location1> locations = new ArrayList<>();

	public void addDistinct(Location1 location) {
		if(this.locations.contains(location)) return;
		this.locations.add(location);
	}

	/* 
	- This method iterates all locations & updates those being moved towards a common direction.
	-	The update involves changing to new x & y coordinates.
	- Each location is mapped either to the same location, if its direction does not match,
		or to a new Location1 object with updated coordinates (since such objects are immutable).
	*/
	@SuppressWarnings("null")
	public void moveTowards(Direction direction, int x, int y) {
		this.locations = this.locations.stream()
																	 .map(
																			location -> location.direction().equals(direction) 
																			? new Location1(x, y, direction) 
																			: location
																	 ).toList();
	}

	/*
	- This method calculates the Euclidean Distance (the straight-line distance) 
		from 1 location (a Location1 object) to another (via their x & y coordinates).
	- However, such distance calculation/logic should be a concept better 
		associated with a geometric object (Location0 or Location1).
	- The job of DestinationList is to manage a list of destinations, 
		not to calculate distances between 2 locations.
	- That's why in discardExceedingLocation1, getDistanceFromOneLocationToAnother1, which's 
		defined in Location1 (recommended & more appropriate), is imported & utilized efficiently.			
	- https://www.geeksforgeeks.org/maths/euclidean-distance/
	- https://www.cuemath.com/euclidean-distance-formula/	
	*/
	private double getDistanceFromOneLocationToAnother0(
		Location1 location, 
		int anotherX, 
		int anotherY
	) {
		return Math.sqrt(
			Math.pow(anotherX - location.x(), 2) + Math.pow(anotherY - location.y(), 2)
		);
	}

	public void discardExceedingLocation0(
		int x, 
		int y, 
		int maxAllowedDistanceBtwn2Locations
	) {
		this.locations = this.locations.stream()
																	 .filter(
																			location -> getDistanceFromOneLocationToAnother0(location, x, y) < maxAllowedDistanceBtwn2Locations
																		)
																	 .toList();
	}

	public void discardExceedingLocation1(
		int x, 
		int y, 
		int maxAllowedDistanceBtwn2Locations
	) {
		this.locations = this.locations.stream()
																	 .filter(
																			location -> location.getDistanceFromOneLocationToAnother1(x, y) < maxAllowedDistanceBtwn2Locations
																		)
																	 .toList();
	}

	public List<Location1> getLocations() {
		return this.locations;
	}
}
