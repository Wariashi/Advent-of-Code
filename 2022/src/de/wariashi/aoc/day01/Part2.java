package de.wariashi.aoc.day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Part2 {
	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day01/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {

			var pots = new ArrayList<Integer>();
			var currentPot = 0;

			var currentLine = inputReader.readLine();
			while (currentLine != null) {
				if ("".equals(currentLine)) {
					pots.add(currentPot);
					currentPot = 0;
				} else {
					int calories = Integer.valueOf(currentLine);
					currentPot += calories;
				}
				currentLine = inputReader.readLine();
			}
			Collections.sort(pots);
			Collections.reverse(pots);
			var result = pots.get(0) + pots.get(1) + pots.get(2);
			System.out.println(result);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
