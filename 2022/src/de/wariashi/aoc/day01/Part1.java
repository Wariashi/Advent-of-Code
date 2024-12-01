package de.wariashi.aoc.day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day01/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			var currentPot = 0;
			var currentMax = 0;
			while (currentLine != null) {
				if ("".equals(currentLine)) {
					currentMax = Math.max(currentMax, currentPot);
					currentPot = 0;
				} else {
					int calories = Integer.valueOf(currentLine);
					currentPot += calories;
				}
				currentLine = inputReader.readLine();
			}
			System.out.println(currentMax);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
