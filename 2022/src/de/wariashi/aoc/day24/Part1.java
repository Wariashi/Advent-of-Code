package de.wariashi.aoc.day24;

import de.wariashi.aoc.common.AdventUtil;

public class Part1 {
	// states
	private static final int CLEAR = 0;
	private static final int WALL = 1;
	private static final int ELF = 2;
	private static final int DOWN = 4;
	private static final int LEFT = 8;
	private static final int RIGHT = 16;
	private static final int UP = 32;

	// map
	private static int width;
	private static int height;
	private static int[][] map;
	private static int[][] previousState;

	private static int stepCounter = 0;

	public static void main(String[] args) {
		init();
		printMap();
		while (!reachedGoal()) {
			step();
		}
		printMap();
	}

	private static void backupMap() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				previousState[x][y] = map[x][y];
			}
		}
	}

	private static void clearMap() {
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				map[x][y] = CLEAR;
			}
		}
	}

	private static void init() {
		var input = AdventUtil.getLines("src/de/wariashi/aoc/day24/input");
		height = input.size();
		width = input.get(0).length();
		map = new int[width][height];
		previousState = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				var tile = input.get(y).charAt(x);
				if (tile == '.') {
					map[x][y] = CLEAR;
				} else if (tile == 'v') {
					map[x][y] = DOWN;
				} else if (tile == '<') {
					map[x][y] = LEFT;
				} else if (tile == '>') {
					map[x][y] = RIGHT;
				} else if (tile == '^') {
					map[x][y] = UP;
				} else if (tile == '#') {
					map[x][y] = WALL;
				}
			}
		}
		for (int x = 0; x < width; x++) {
			if (map[x][0] == CLEAR) {
				map[x][0] = ELF;
			}
		}
	}

	private static void printMap() {
		System.out.println("Minute: " + stepCounter);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[x][y] == CLEAR) {
					System.out.print('.');
				} else if (map[x][y] == DOWN) {
					System.out.print('v');
				} else if (map[x][y] == ELF) {
					System.out.print('E');
				} else if (map[x][y] == LEFT) {
					System.out.print('<');
				} else if (map[x][y] == RIGHT) {
					System.out.print('>');
				} else if (map[x][y] == UP) {
					System.out.print('^');
				} else if (map[x][y] == WALL) {
					System.out.print('#');
				} else {
					// multiple blizzards
					var numberOfBlizzards = 0;
					if ((map[x][y] & DOWN) == DOWN) {
						numberOfBlizzards++;
					}
					if ((map[x][y] & LEFT) == LEFT) {
						numberOfBlizzards++;
					}
					if ((map[x][y] & RIGHT) == RIGHT) {
						numberOfBlizzards++;
					}
					if ((map[x][y] & UP) == UP) {
						numberOfBlizzards++;
					}
					System.out.print(numberOfBlizzards);
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void step() {
		backupMap();
		clearMap();
		updateBlizzards();
		updateElves();
		stepCounter++;
	}

	private static boolean reachedGoal() {
		for (int x = 0; x < width; x++) {
			if (map[x][height - 1] == ELF) {
				return true;
			}
		}
		return false;
	}

	private static void updateBlizzards() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if ((previousState[x][y] & DOWN) == DOWN) {
					var newY = y + 1;
					if (height - 2 < newY) {
						newY = 1;
					}
					map[x][newY] = map[x][newY] | DOWN;
				}
				if ((previousState[x][y] & LEFT) == LEFT) {
					var newX = x - 1;
					if (newX < 1) {
						newX = width - 2;
					}
					map[newX][y] = map[newX][y] | LEFT;
				}
				if ((previousState[x][y] & RIGHT) == RIGHT) {
					var newX = x + 1;
					if (width - 2 < newX) {
						newX = 1;
					}
					map[newX][y] = map[newX][y] | RIGHT;
				}
				if ((previousState[x][y] & UP) == UP) {
					var newY = y - 1;
					if (newY < 1) {
						newY = height - 2;
					}
					map[x][newY] = map[x][newY] | UP;
				}
			}
		}
	}

	private static void updateElves() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[x][y] != CLEAR) {
					continue;
				}

				// wait
				if (previousState[x][y] == ELF) {
					map[x][y] = ELF;
				}

				// go down
				if (0 < y && previousState[x][y - 1] == ELF) {
					map[x][y] = ELF;
				}

				// go left
				if (x + 1 < width && previousState[x + 1][y] == ELF) {
					map[x][y] = ELF;
				}

				// go right
				if (0 < x && previousState[x - 1][y] == ELF) {
					map[x][y] = ELF;
				}

				// go up
				if (y + 1 < height && previousState[x][y + 1] == ELF) {
					map[x][y] = ELF;
				}
			}
		}
	}
}
