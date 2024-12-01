package de.wariashi.aoc.day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
	private static int cycle = 0;
	private static List<String> instructions = new ArrayList<>();
	private static int x = 1;

	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day10/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			while (currentLine != null) {
				if (currentLine.startsWith("addx")) {
					// takes two cycles
					instructions.add("noop");
				}
				instructions.add(currentLine);
				currentLine = inputReader.readLine();
			}
			runInstructions();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static void runInstructions() {
		for (var instruction : instructions) {
			cycle++;
			if (instruction.startsWith("addx")) {
				x += Integer.valueOf(instruction.substring(5));
			}
			if (cycle % 40 == x) {
				System.out.print("#");
			} else if (cycle % 40 == x - 1) {
				System.out.print("#");
			} else if (cycle % 40 == x + 1) {
				System.out.print("#");
			} else {
				System.out.print(".");
			}
			if (cycle % 40 == 0) {
				System.out.println();
			}
		}

		// result: FJUBULRZ
	}
}
