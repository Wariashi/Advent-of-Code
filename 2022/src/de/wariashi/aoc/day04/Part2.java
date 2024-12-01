package de.wariashi.aoc.day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day04/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();

			var overlaps = 0;
			while (currentLine != null) {
				var assignments = currentLine.split(",");
				var assignment1 = assignments[0];
				var assignment2 = assignments[1];
				if (contains(assignment1, assignment2)) {
					overlaps++;
				}
				currentLine = inputReader.readLine();
			}
			System.out.println(overlaps);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static boolean contains(String assignment1, String assignment2) {
		var parts1 = assignment1.split("-");
		var parts2 = assignment2.split("-");

		var min1 = Integer.valueOf(parts1[0]);
		var max1 = Integer.valueOf(parts1[1]);
		var min2 = Integer.valueOf(parts2[0]);
		var max2 = Integer.valueOf(parts2[1]);

		if (max1 < min2) {
			return false;
		}

		if (max2 < min1) {
			return false;
		}

		return true;
	}
}
