package de.wariashi.aoc.day14;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
	// map
	private static int width;
	private static int height;
	private static char[][] cave;
	private static List<List<Point>> paths;

	// materials
	private static final char AIR = ' ';
	private static final char ROCK = '#';
	private static final char SAND = '.';
	private static final char SOURCE = '+';

	public static void main(String[] args) {
		initPaths();
		initCave();
		for (int i = 0; i < 29075; i++) {
			dropSand();
		}
		// correct result is 29076, because it can still 'drop'
		printCave();
	}

	private static void addRocks() {
		for (var path : paths) {
			var x = path.get(0).x;
			var y = path.get(0).y;
			for (var coordinate : path) {
				cave[x][y] = ROCK;
				while (x != coordinate.x || y != coordinate.y) {
					if (x < coordinate.x) {
						x++;
					} else if (coordinate.x < x) {
						x--;
					}
					if (y < coordinate.y) {
						y++;
					} else if (coordinate.y < y) {
						y--;
					}
					cave[x][y] = ROCK;
				}
			}
		}
	}

	private static void dropSand() {
		var x = 500;
		var y = 0;

		var falling = true;
		while (falling) {
			falling = false;
			if (y < height - 1 && cave[x][y + 1] == AIR) {
				y++;
				falling = true;
			} else if (0 < x && y < height - 1 && cave[x - 1][y + 1] == AIR) {
				x--;
				y++;
				falling = true;
			} else if (x < width - 1 && y < height - 1 && cave[x + 1][y + 1] == AIR) {
				x++;
				y++;
				falling = true;
			}
		}

		cave[x][y] = SAND;
	}

	private static void initCave() {
		var maxY = Integer.MIN_VALUE;

		for (var path : paths) {
			for (var coordinate : path) {
				if (maxY < coordinate.y) {
					maxY = coordinate.y;
				}
			}
		}

		width = 1000;
		height = maxY + 2;

		cave = new char[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				cave[x][y] = AIR;
			}
		}

		cave[500][0] = SOURCE;

		addRocks();
	}

	private static void initPaths() {
		paths = new ArrayList<>();
		File input = new File("src/de/wariashi/aoc/day14/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			while (currentLine != null) {
				var path = new ArrayList<Point>();
				paths.add(path);
				var parts = currentLine.split(" -> ");
				for (var coordinate : parts) {
					var coordinates = coordinate.split(",");
					var x = Integer.valueOf(coordinates[0]);
					var y = Integer.valueOf(coordinates[1]);
					path.add(new Point(x, y));
				}
				currentLine = inputReader.readLine();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static void printCave() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(cave[x][y]);
			}
			System.out.println();
		}
	}
}
