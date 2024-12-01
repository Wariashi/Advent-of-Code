package de.wariashi.aoc.day17;

public enum Shape {
	// ####
	MINUS(new boolean[][] { { true }, { true }, { true }, { true } }),

	// .#.
	// ###
	// .#.
	PLUS(new boolean[][] { { false, true, false }, { true, true, true }, { false, true, false } }),

	// ..#
	// ..#
	// ###
	J(new boolean[][] { { false, false, true }, { false, false, true }, { true, true, true } }),

	// #
	// #
	// #
	// #
	I(new boolean[][] { { true, true, true, true } }),

	// ##
	// ##
	SQUARE(new boolean[][] { { true, true }, { true, true } });

	private final boolean[][] map;

	Shape(boolean[][] map) {
		this.map = map;
	}

	public int getHeight() {
		return map[0].length;
	}

	public boolean isRock(int x, int y) {
		return map[x][y];
	}

	public int getWidth() {
		return map.length;
	}
}
