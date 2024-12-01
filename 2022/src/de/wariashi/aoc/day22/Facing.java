package de.wariashi.aoc.day22;

public enum Facing {
	RIGHT, DOWN, LEFT, UP;

	public int getScore() {
		return ordinal();
	}
}
