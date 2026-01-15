package com.pragmatic.jefflangr.units;

import java.util.Objects;

public class Location0 {
	enum Direction {North, East, South, West};
	
	private int x, y;
	private Direction direction;

	public Location0(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getX() { return this.x; } 
	public int getY() { return this.y; } 
	public Direction getDirection() { return this.direction; }

	@Override 
	public boolean equals(Object o) { 
		if(this == o) return true; 
		if(o == null || getClass() != o.getClass()) return false; 
		Location0 location = (Location0) o; 
		return this.x == location.x && this.y == location.y && this.direction == location.direction;
	}

	@Override 
	public int hashCode() { return Objects.hash(this.x, this.y, this.direction); } 
	
	@Override 
	public String toString() { 
		return "(x: " + this.x + ", y: " + this.y + ") => " + this.direction; 
	}

	public void move(int distance) {
		switch(this.direction) {
			case North -> this.y = this.y + distance;
			case East -> this.x = this.x + distance;
			case South -> this.y = this.y - distance;
			case West -> this.x = this.x - distance;
		}
	}
}
