package de.wariashi.aoc.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day06/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var content = inputReader.readLine();
			var i = 0;
			while (!isMarker(i, content)) {
				i++;
			}

			System.out.println(i + ": " + (char) content.charAt(i));
			System.out.println(i + 1 + ": " + (char) content.charAt(i + 1));
			System.out.println(i + 2 + ": " + (char) content.charAt(i + 2));
			System.out.println(i + 3 + ": " + (char) content.charAt(i + 3));
			System.out.println(i + 4 + ": " + (char) content.charAt(i + 4));
			System.out.println(i + 5 + ": " + (char) content.charAt(i + 5));
			System.out.println(i + 6 + ": " + (char) content.charAt(i + 6));
			System.out.println(i + 7 + ": " + (char) content.charAt(i + 7));
			System.out.println(i + 8 + ": " + (char) content.charAt(i + 8));
			System.out.println(i + 9 + ": " + (char) content.charAt(i + 9));
			System.out.println(i + 10 + ": " + (char) content.charAt(i + 10));
			System.out.println(i + 11 + ": " + (char) content.charAt(i + 11));
			System.out.println(i + 12 + ": " + (char) content.charAt(i + 12));
			System.out.println(i + 13 + ": " + (char) content.charAt(i + 13));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static boolean isMarker(int index, String content) {
		var substring = content.subSequence(index, index + 20);
		System.out.println(substring);

		for (int i = 0; i < 14; i++) {
			for (int j = i + 1; j < 14; j++) {
				if (substring.charAt(i) == substring.charAt(j)) {
					return false;
				}
			}
		}
		return true;

	}
}
