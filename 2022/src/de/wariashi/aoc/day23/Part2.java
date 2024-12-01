package de.wariashi.aoc.day23;

import de.wariashi.aoc.common.AdventUtil;

public class Part2 {
	// map
	private static int width;
	private static int height;
	private static char[][] map;

	// states
	private static final char EAST = '>';
	private static final char ELF = '#';
	private static final char GROUND = '.';
	private static final char NORTH = '^';
	private static final char SOUTH = 'v';
	private static final char WEST = '<';

	public static void main(String[] args) {
		init();
		printMap();

		var round = 0;
		decide(round);
		while (elvesMoving()) {
			round++;
			move();
			decide(round);
		}
		printMap();
		System.out.println("Finished after round " + round);
	}

	private static void decide(int round) {
		switch (round % 4) {
			case 0:
				decide(Direction.NORTH);
				decide(Direction.SOUTH);
				decide(Direction.WEST);
				decide(Direction.EAST);
				break;
			case 1:
				decide(Direction.SOUTH);
				decide(Direction.WEST);
				decide(Direction.EAST);
				decide(Direction.NORTH);
				break;
			case 2:
				decide(Direction.WEST);
				decide(Direction.EAST);
				decide(Direction.NORTH);
				decide(Direction.SOUTH);
				break;
			case 3:
				decide(Direction.EAST);
				decide(Direction.NORTH);
				decide(Direction.SOUTH);
				decide(Direction.WEST);
				break;
			default:
				break;
		}
	}

	private static void decide(Direction direction) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (!needsToMove(x, y)) {
					continue;
				}

				switch (direction) {
					case EAST:
						if (map[x + 1][y - 1] == GROUND && map[x + 1][y] == GROUND && map[x + 1][y + 1] == GROUND) {
							map[x][y] = EAST;
						}
						break;
					case NORTH:
						if (map[x - 1][y - 1] == GROUND && map[x][y - 1] == GROUND && map[x + 1][y - 1] == GROUND) {
							map[x][y] = NORTH;
						}
						break;
					case SOUTH:
						if (map[x - 1][y + 1] == GROUND && map[x][y + 1] == GROUND && map[x + 1][y + 1] == GROUND) {
							map[x][y] = SOUTH;
						}
						break;
					case WEST:
						if (map[x - 1][y - 1] == GROUND && map[x - 1][y] == GROUND && map[x - 1][y + 1] == GROUND) {
							map[x][y] = WEST;
						}
						break;
				}
			}
		}
	}

	private static boolean elvesMoving() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[x][y] == EAST || map[x][y] == NORTH || map[x][y] == SOUTH || map[x][y] == WEST) {
					return true;
				}
			}
		}
		return false;
	}

	private static void init() {
		var input = AdventUtil.getLines("src/de/wariashi/aoc/day23/input");
		height = 3 * input.size();
		width = 3 * input.get(0).length();
		map = new char[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[x][y] = GROUND;
			}
		}
		for (int y = 0; y < height / 3; y++) {
			for (int x = 0; x < width / 3; x++) {
				map[x + width / 3][y + height / 3] = input.get(y).charAt(x);
			}
		}
	}

	private static void move() {
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				if (map[x][y] == GROUND) {
					var numberOfContestants = 0;
					if (map[x + 1][y] == WEST) {
						numberOfContestants++;
					}
					if (map[x - 1][y] == EAST) {
						numberOfContestants++;
					}
					if (map[x][y + 1] == NORTH) {
						numberOfContestants++;
					}
					if (map[x][y - 1] == SOUTH) {
						numberOfContestants++;
					}
					if (numberOfContestants == 1) {
						map[x][y] = ELF;
						if (map[x + 1][y] == WEST) {
							map[x + 1][y] = GROUND;
						} else if (map[x - 1][y] == EAST) {
							map[x - 1][y] = GROUND;
						} else if (map[x][y + 1] == NORTH) {
							map[x][y + 1] = GROUND;
						} else if (map[x][y - 1] == SOUTH) {
							map[x][y - 1] = GROUND;
						}
					}
				}
			}
		}

		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				if (map[x][y] == EAST || map[x][y] == NORTH || map[x][y] == SOUTH || map[x][y] == WEST) {
					map[x][y] = ELF;
				}
			}
		}
	}

	private static boolean needsToMove(int x, int y) {
		if (map[x][y] != ELF) {
			return false;
		}

		for (int relativeY = -1; relativeY < 2; relativeY++) {
			for (int relativeX = -1; relativeX < 2; relativeX++) {
				if ((relativeX != 0 || relativeY != 0) && map[x + relativeX][y + relativeY] != GROUND) {
					return true;
				}
			}
		}

		return false;
	}

	private static void printMap() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
	}
}
