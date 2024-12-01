package de.wariashi.aoc.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

	private static int totalSum = 0;

	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day07/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();

			var root = new Directory("/", null);
			Directory currentDirectory = null;

			while (currentLine != null) {
				if (currentLine.equals("$ cd /")) {
					currentDirectory = root;
				} else if (currentLine.equals("$ ls")) {
					currentLine = inputReader.readLine();
					while (currentLine != null && !currentLine.startsWith("$")) {
						if (currentLine.startsWith("dir")) {
							var directory = new Directory(currentLine.substring(4), currentDirectory);
							currentDirectory.addDirectory(directory);
						} else {
							String parts[] = currentLine.split(" ");
							var size = Integer.valueOf(parts[0]);
							var data = new Data(size);
							currentDirectory.addData(data);
						}
						currentLine = inputReader.readLine();
					}
					continue;
				} else if (currentLine.equals("$ cd ..")) {
					currentDirectory = currentDirectory.getParent();
				} else if (currentLine.startsWith("$ cd ")) {
					var subdirectory = currentLine.substring(5);
					currentDirectory = currentDirectory.getChild(subdirectory);
				}
				currentLine = inputReader.readLine();
			}

			calculateSumOfSmallFiles(root);
			System.out.println(totalSum);

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static int calculateSumOfSmallFiles(Directory root) {
		int sum = 0;

		// add sum of files
		for (var file : root.getFiles()) {
			sum += file.getSize();
		}

		// add subfolders
		for (var subfolder : root.getSubfolders()) {
			var size = calculateSumOfSmallFiles(subfolder);
			sum += size;
		}

		if (sum < 100000) {
			totalSum += sum;
		}

		return sum;
	}
}
