package de.wariashi.aoc.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
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
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static boolean isMarker(int index, String content) {
		System.out.println(index + ": " + (char) content.charAt(index));

		var substring = content.subSequence(index, index + 4);
		return substring.charAt(0) != substring.charAt(1)//
				&& substring.charAt(0) != substring.charAt(2)//
				&& substring.charAt(0) != substring.charAt(3)//
				&& substring.charAt(1) != substring.charAt(2)//
				&& substring.charAt(1) != substring.charAt(3)//
				&& substring.charAt(2) != substring.charAt(3);

	}
}
