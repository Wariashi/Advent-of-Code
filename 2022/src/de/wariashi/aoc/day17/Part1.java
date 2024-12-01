package de.wariashi.aoc.day17;

import de.wariashi.aoc.common.AdventUtil;

public class Part1 {
	private static final int NUMBER_OF_ROCKS = 2022;

	private static char[][] chamber;
	private static int chamberHeight;
	private static final int CHAMBER_WIDTH = 7;

	private static final char AIR = '.';
	private static final char ROCK = '#';
	private static int highestY;

	private static String instructions;
	private static int instructionPointer = 0;

	public static void main(String[] args) {
		init();
		for (int i = 0; i < NUMBER_OF_ROCKS; i++) {
			var shape = getShapeForCycle(i);
			dropRock(shape);
		}
		printChamber();

		System.out.println();

		System.out.println("Tower height: " + (chamberHeight - highestY));
	}

	private static void applyRockToChamber(Shape shape, int x, int y) {
		for (int shapeY = 0; shapeY < shape.getHeight(); shapeY++) {
			for (int shapeX = 0; shapeX < shape.getWidth(); shapeX++) {
				if (shape.isRock(shapeX, shapeY)) {
					chamber[x + shapeX][y + shapeY] = ROCK;
				}
			}
		}
		if (y < highestY) {
			highestY = y;
		}
	}

	private static boolean canPushLeft(Shape shape, int x, int y) {
		// check for chamber collision
		if (x == 0) {
			return false;
		}

		// check for rock collision
		for (int shapeY = 0; shapeY < shape.getHeight(); shapeY++) {
			for (int shapeX = 0; shapeX < shape.getWidth(); shapeX++) {
				if (shape.isRock(shapeX, shapeY) && chamber[x + shapeX - 1][y + shapeY] == ROCK) {
					return false;
				}
			}
		}

		return true;
	}

	private static boolean canPushRight(Shape shape, int x, int y) {
		// check for chamber collision
		if (CHAMBER_WIDTH <= x + shape.getWidth()) {
			return false;
		}

		// check for rock collision
		for (int shapeY = 0; shapeY < shape.getHeight(); shapeY++) {
			for (int shapeX = 0; shapeX < shape.getWidth(); shapeX++) {
				if (shape.isRock(shapeX, shapeY) && chamber[x + shapeX + 1][y + shapeY] == ROCK) {
					return false;
				}
			}
		}

		return true;
	}

	private static void dropRock(Shape shape) {
		var x = 2;
		var y = highestY - 3 - shape.getHeight();

		while (true) {
			var instruction = instructions.charAt(instructionPointer);
			instructionPointer = (instructionPointer + 1) % instructions.length();
			if (instruction == '<' && canPushLeft(shape, x, y)) {
				x--;
			} else if (instruction == '>' && canPushRight(shape, x, y)) {
				x++;
			}

			if (!hasReachedBottom(shape, x, y)) {
				y++;
			} else {
				break;
			}
		}

		applyRockToChamber(shape, x, y);
	}

	private static void init() {
		var input = AdventUtil.getLines("src/de/wariashi/aoc/day17/input");
		instructions = input.get(0);

		chamberHeight = NUMBER_OF_ROCKS * 4 + 3;
		highestY = chamberHeight;
		chamber = new char[7][chamberHeight];
		for (int y = 0; y < chamberHeight; y++) {
			for (int x = 0; x < CHAMBER_WIDTH; x++) {
				chamber[x][y] = AIR;
			}
		}
	}

	private static Shape getShapeForCycle(int cycle) {
		switch (cycle % 5) {
		case 0:
			return Shape.MINUS;
		case 1:
			return Shape.PLUS;
		case 2:
			return Shape.J;
		case 3:
			return Shape.I;
		case 4:
			return Shape.SQUARE;
		default:
			return null;
		}
	}

	private static boolean hasReachedBottom(Shape shape, int x, int y) {
		// check for bottom of chamber
		if (chamberHeight <= y + shape.getHeight()) {
			return true;
		}

		// check for other rocks
		for (int shapeY = 0; shapeY < shape.getHeight(); shapeY++) {
			for (int shapeX = 0; shapeX < shape.getWidth(); shapeX++) {
				if (shape.isRock(shapeX, shapeY) && chamber[x + shapeX][y + shapeY + 1] == ROCK) {
					return true;

				}
			}
		}

		return false;
	}

	private static void printChamber() {
		// x coordinates
		System.out.print(' ');
		for (int x = 0; x < CHAMBER_WIDTH; x++) {
			System.out.print(x);
		}
		System.out.println();

		// chamber
		for (int y = 0; y < chamberHeight; y++) {
			System.out.print("|");
			for (int x = 0; x < CHAMBER_WIDTH; x++) {
				System.out.print(chamber[x][y]);
			}
			System.out.println("| " + y);
		}

		// bottom line
		System.out.print("+");
		for (int x = 0; x < CHAMBER_WIDTH; x++) {
			System.out.print("-");
		}
		System.out.println("+ " + chamberHeight);

		// x coordinates
		System.out.print(' ');
		for (int x = 0; x < CHAMBER_WIDTH; x++) {
			System.out.print(x);
		}
		System.out.println();
	}
}
