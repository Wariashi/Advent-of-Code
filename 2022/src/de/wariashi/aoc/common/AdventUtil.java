package de.wariashi.aoc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventUtil {
	private AdventUtil() {
	}

	public static List<String> getLines(String file) {
		var lines = new ArrayList<String>();
		File input = new File(file);
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			while (currentLine != null) {
				lines.add(currentLine);
				currentLine = inputReader.readLine();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return lines;
	}
}
