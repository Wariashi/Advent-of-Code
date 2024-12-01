package de.wariashi.aoc.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
	private static final int[][] heights = new int[99][99];
	private static final boolean[][] visibility = new boolean[99][99];

	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day08/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			for (int y = 0; y < 99; y++) {
				var currentLine = inputReader.readLine();
				for (int x = 0; x < 99; x++) {
					heights[x][y] = Integer.valueOf("" + currentLine.charAt(x));
				}
			}

			calculateVisibility();
			printVisibility();

			int sumOfVisibleTrees = 0;
			for (int y = 0; y < 99; y++) {
				for (int x = 0; x < 99; x++) {
					if (visibility[x][y]) {
						sumOfVisibleTrees++;
					}
				}
			}
			System.out.println("Sum of visible trees: " + sumOfVisibleTrees);

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static void calculateVisibility() {
		calculateVisibilityFromEast();
		calculateVisibilityFromNorth();
		calculateVisibilityFromSouth();
		calculateVisibilityFromWest();
	}

	private static void calculateVisibilityFromEast() {
		for (int y = 0; y < 99; y++) {
			var maxHeight = -1;
			for (int x = 98; x >= 0; x--) {
				if (maxHeight < heights[x][y]) {
					visibility[x][y] = true;
					maxHeight = heights[x][y];
				}
			}
		}
	}

	private static void calculateVisibilityFromNorth() {
		for (int x = 0; x < 99; x++) {
			var maxHeight = -1;
			for (int y = 0; y < 99; y++) {
				if (maxHeight < heights[x][y]) {
					visibility[x][y] = true;
					maxHeight = heights[x][y];
				}
			}
		}
	}

	private static void calculateVisibilityFromSouth() {
		for (int x = 0; x < 99; x++) {
			var maxHeight = -1;
			for (int y = 98; y >= 0; y--) {
				if (maxHeight < heights[x][y]) {
					visibility[x][y] = true;
					maxHeight = heights[x][y];
				}
			}
		}
	}

	private static void calculateVisibilityFromWest() {
		for (int y = 0; y < 99; y++) {
			var maxHeight = -1;
			for (int x = 0; x < 99; x++) {
				if (maxHeight < heights[x][y]) {
					visibility[x][y] = true;
					maxHeight = heights[x][y];
				}
			}
		}
	}

	private static void printVisibility() {
		for (int y = 0; y < 99; y++) {
			for (int x = 0; x < 99; x++) {
				System.out.print(visibility[x][y] ? '#' : '?');
			}
			System.out.println();
		}
	}
}
