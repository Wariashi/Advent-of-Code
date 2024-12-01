package de.wariashi.aoc.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day03/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			var totalPriority = 0;
			while (currentLine != null) {
				totalPriority += getPriority(currentLine);
				currentLine = inputReader.readLine();
			}
			System.out.println(totalPriority);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static int getPriority(String content) {
		var firstCompartment = content.substring(0, content.length() / 2);
		var secondCompartment = content.substring(content.length() / 2);
		var duplicate = getDuplicate(firstCompartment, secondCompartment);
		int priority = duplicate - 38;
		if (priority > 52) {
			priority -= 58;
		}
		return priority;
	}

	private static char getDuplicate(String firstCompartment, String secondCompartment) {
		for (var i = 0; i < firstCompartment.length(); i++) {
			if (secondCompartment.contains("" + firstCompartment.charAt(i))) {
				return firstCompartment.charAt(i);
			}
		}
		return '-';
	}
}
