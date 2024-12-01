package de.wariashi.aoc.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day03/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			var totalPriority = 0;
			while (currentLine != null) {
				var part1 = currentLine;
				var part2 = inputReader.readLine();
				var part3 = inputReader.readLine();
				var badge = getBadge(part1, part2, part3);
				totalPriority += getPriority(badge);
				currentLine = inputReader.readLine();
			}
			System.out.println(totalPriority);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static int getPriority(char badge) {
		int priority = badge - 38;
		if (priority > 52) {
			priority -= 58;
		}
		return priority;
	}

	private static char getBadge(String part1, String part2, String part3) {
		for (var i = 0; i < part1.length(); i++) {
			var badge = "" + part1.charAt(i);
			if (part2.contains(badge) && part3.contains(badge)) {
				return part1.charAt(i);
			}
		}
		return '-';
	}
}
