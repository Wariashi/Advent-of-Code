package de.wariashi.aoc.day22;

import java.util.List;

import de.wariashi.aoc.common.AdventUtil;

public class Part1 {
	// instructions
	private static String instructions;
	private static int instructionPointer;

	// map
	private static int width;
	private static int height;
	private static char[][] map;

	// materials
	private static final char EMPTY = '.';
	private static final char VOID = ' ';
	private static final char WALL = '#';

	// player
	private static Facing facing;
	private static int playerX;
	private static int playerY;

	public static void main(String[] args) {
		init();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
		System.out.println("Starting at " + playerX + ":" + playerY + " facing " + facing);
		System.out.println("Following instructions: " + instructions);
		while (instructionPointer < instructions.length()) {
			followInstruction();
		}
		System.out.println("Arriving at " + playerX + ":" + playerY + " facing " + facing);

		var score = getScore();
		System.out.println("Final score: " + score);
	}

	private static void followInstruction() {
		if (instructions.charAt(instructionPointer) == 'L') {
			turnLeft();
			instructionPointer++;
		} else if (instructions.charAt(instructionPointer) == 'R') {
			turnRight();
			instructionPointer++;
		} else {
			var tilesToMove = 0;
			while (instructionPointer < instructions.length()
					&& Character.isDigit(instructions.charAt(instructionPointer))) {
				tilesToMove *= 10;
				tilesToMove += Integer.parseInt("" + instructions.charAt(instructionPointer));
				instructionPointer++;
			}
			move(tilesToMove);
		}
	}

	private static void init() {
		var input = AdventUtil.getLines("src/de/wariashi/aoc/day22/input");
		initMap(input);
		initPlayer();

		instructions = input.get(input.size() - 1);
		instructionPointer = 0;
	}

	private static int getScore() {
		return 1000 * (playerY + 1) + 4 * (playerX + 1) + facing.getScore();
	}

	private static void initMap(List<String> input) {
		// calculate size
		height = input.size() - 2;
		width = 0;
		for (int i = 0; i < height; i++) {
			var line = input.get(i);
			var lineLength = line.length();
			if (width < lineLength) {
				width = lineLength;
			}
		}

		// init map
		map = new char[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[x][y] = VOID;
			}
		}

		// fill map
		for (int y = 0; y < height; y++) {
			var line = input.get(y);
			for (int x = 0; x < line.length(); x++) {
				map[x][y] = line.charAt(x);
			}
		}
	}

	private static void initPlayer() {
		facing = Facing.RIGHT;
		for (int x = 0; x < width; x++) {
			if (map[x][0] == EMPTY) {
				playerX = x;
				break;
			}
		}
		playerY = 0;
	}

	private static void move(int tilesToMove) {
		switch (facing) {
		case DOWN:
			moveDown(tilesToMove);
			break;
		case LEFT:
			moveLeft(tilesToMove);
			break;
		case RIGHT:
			moveRight(tilesToMove);
			break;
		case UP:
			moveUp(tilesToMove);
			break;
		}
	}

	private static void moveDown(int tilesToMove) {
		var yToCheck = playerY + 1;

		// map wrapping
		if (height <= yToCheck || map[playerX][yToCheck] == VOID) {
			yToCheck = 0;
			while (map[playerX][yToCheck] == VOID) {
				yToCheck++;
			}
		}

		// get blocked by walls
		if (map[playerX][yToCheck] == WALL) {
			return;
		}

		playerY = yToCheck;
		if (1 < tilesToMove) {
			moveDown(tilesToMove - 1);
		}
	}

	private static void moveLeft(int tilesToMove) {
		var xToCheck = playerX - 1;

		// map wrapping
		if (xToCheck < 0 || map[xToCheck][playerY] == VOID) {
			xToCheck = width - 1;
			while (map[xToCheck][playerY] == VOID) {
				xToCheck--;
			}
		}

		// get blocked by walls
		if (map[xToCheck][playerY] == WALL) {
			return;
		}

		playerX = xToCheck;
		if (1 < tilesToMove) {
			moveLeft(tilesToMove - 1);
		}
	}

	private static void moveRight(int tilesToMove) {
		var xToCheck = playerX + 1;

		// map wrapping
		if (width <= xToCheck || map[xToCheck][playerY] == VOID) {
			xToCheck = 0;
			while (map[xToCheck][playerY] == VOID) {
				xToCheck++;
			}
		}

		// get blocked by walls
		if (map[xToCheck][playerY] == WALL) {
			return;
		}

		playerX = xToCheck;
		if (1 < tilesToMove) {
			moveRight(tilesToMove - 1);
		}
	}

	private static void moveUp(int tilesToMove) {
		var yToCheck = playerY - 1;

		// map wrapping
		if (yToCheck < 0 || map[playerX][yToCheck] == VOID) {
			yToCheck = height - 1;
			while (map[playerX][yToCheck] == VOID) {
				yToCheck--;
			}
		}

		// get blocked by walls
		if (map[playerX][yToCheck] == WALL) {
			return;
		}

		playerY = yToCheck;
		if (1 < tilesToMove) {
			moveUp(tilesToMove - 1);
		}
	}

	private static void turnLeft() {
		switch (facing) {
		case DOWN:
			facing = Facing.RIGHT;
			break;
		case LEFT:
			facing = Facing.DOWN;
			break;
		case RIGHT:
			facing = Facing.UP;
			break;
		case UP:
			facing = Facing.LEFT;
			break;
		}
	}

	private static void turnRight() {
		switch (facing) {
		case DOWN:
			facing = Facing.LEFT;
			break;
		case LEFT:
			facing = Facing.UP;
			break;
		case RIGHT:
			facing = Facing.DOWN;
			break;
		case UP:
			facing = Facing.RIGHT;
			break;
		}
	}
}
