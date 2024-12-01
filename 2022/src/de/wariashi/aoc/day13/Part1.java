package de.wariashi.aoc.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

	private static List<Pair> pairs = new ArrayList<>();

	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day13/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			while (currentLine != null) {
				var packet1 = currentLine;
				var packet2 = inputReader.readLine();

				pairs.add(new Pair(packet1, packet2));

				inputReader.readLine();// seperator
				currentLine = inputReader.readLine();
			}

			var sum = 0;
			for (int i = 0; i < pairs.size(); i++) {
				if (pairs.get(i).isInRightOrder()) {
					System.out.println((i + 1) + " is in right order");
					sum = sum + i + 1;
				}
			}
			System.out.println("which makes " + sum + " in total");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
