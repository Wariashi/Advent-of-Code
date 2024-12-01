package de.wariashi.aoc.day12;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
	private static final int HEIGHT = 41;
	private static final int WIDTH = 93;

	// map
	private static int[][] elevation;
	private static Point end;
	private static Point start;

	// pathfinding
	private static Pathfinding pathfinding;

	public static void main(String[] args) {
		initMap();
		pathfinding = new Pathfinding(elevation, end);

		System.out.println(pathfinding.getDistanceAt(start));
	}

	private static void initMap() {
		elevation = new int[WIDTH][HEIGHT];
		File input = new File("src/de/wariashi/aoc/day12/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			for (int y = 0; y < HEIGHT; y++) {
				var currentLine = inputReader.readLine();
				for (int x = 0; x < WIDTH; x++) {
					int value = currentLine.charAt(x);
					if (value == 'S') {
						start = new Point(x, y);
						value = 'a';
					} else if (value == 'E') {
						end = new Point(x, y);
						value = 'z';
					}

					elevation[x][y] = value - 'a';
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
