package de.wariashi.aoc.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
	private static final int[][] heights = new int[99][99];
	private static final int[][] scenicScores = new int[99][99];

	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day08/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			for (int y = 0; y < 99; y++) {
				var currentLine = inputReader.readLine();
				for (int x = 0; x < 99; x++) {
					heights[x][y] = Integer.valueOf("" + currentLine.charAt(x));
				}
			}

			// calculate scenic scores
			for (int y = 0; y < 99; y++) {
				for (int x = 0; x < 99; x++) {
					scenicScores[x][y] = getScore(x, y);
				}
			}

			// get highest score
			int highestScore = 0;
			for (int y = 0; y < 99; y++) {
				for (int x = 0; x < 99; x++) {
					if (scenicScores[x][y] > highestScore) {
						highestScore = scenicScores[x][y];
					}
				}
			}
			System.out.println(highestScore);

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static int getScore(int x, int y) {
		return getEastScore(x, y) * getNorthScore(x, y) * getSouthScore(x, y) * getWestScore(x, y);
	}

	private static int getEastScore(int x, int y) {
		var height = heights[x][y];

		var score = 0;
		while (x + score < 98) {
			score++;
			if (height <= heights[x + score][y]) {
				break;
			}
		}

		return score;
	}

	private static int getNorthScore(int x, int y) {
		var height = heights[x][y];

		var score = 0;
		while (0 < y - score) {
			score++;
			if (height <= heights[x][y - score]) {
				break;
			}
		}

		return score;
	}

	private static int getSouthScore(int x, int y) {
		var height = heights[x][y];

		var score = 0;
		while (y + score < 98) {
			score++;
			if (height <= heights[x][y + score]) {
				break;
			}
		}

		return score;
	}

	private static int getWestScore(int x, int y) {
		var height = heights[x][y];

		var score = 0;
		while (0 < x - score) {
			score++;
			if (height <= heights[x - score][y]) {
				break;
			}
		}

		return score;
	}
}
