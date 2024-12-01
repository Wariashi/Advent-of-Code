package de.wariashi.aoc.day12;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
	private static final int HEIGHT = 41;
	private static final int WIDTH = 93;

	// map
	private static int[][] elevation;
	private static Point end;

	// pathfinding
	private static Pathfinding pathfinding;

	public static void main(String[] args) {
		initMap();
		pathfinding = new Pathfinding(elevation, end);

		int shortestDistance = Integer.MAX_VALUE;
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				var distance = pathfinding.getDistanceAt(new Point(x, y));
				if (elevation[x][y] == 0 && distance < shortestDistance) {
					shortestDistance = distance;
				}

			}
		}
		System.out.println(shortestDistance);
	}

	private static void initMap() {
		elevation = new int[WIDTH][HEIGHT];
		File input = new File("src/de/wariashi/aoc/day12/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			for (int y = 0; y < HEIGHT; y++) {
				var currentLine = inputReader.readLine();
				for (int x = 0; x < WIDTH; x++) {
					int value = currentLine.charAt(x);
					if (value == 'E') {
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
