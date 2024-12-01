package de.wariashi.aoc.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

	private static int totalSpace = 70000000;
	private static int spaceNeeded = 30000000;

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

			System.out.println("Root has a size of: " + root.getSize());
			var spaceAvailable = totalSpace - root.getSize();
			System.out.println("Space available: " + spaceAvailable);
			var neededToDelete = spaceNeeded - spaceAvailable;
			System.out.println("Need to delete: " + neededToDelete);

			var smallest = getSmallestDirectoryAbove(root, neededToDelete);

			System.out.println("Smallest directory above that is '" + smallest.toString() + "' with a size of "
					+ smallest.getSize());

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static Directory getSmallestDirectoryAbove(Directory root, int minSize) {
		var smallest = root;

		for (var folder : root.getSubfolders()) {
			var smallestInSubfolder = getSmallestDirectoryAbove(folder, minSize);
			if (minSize <= smallestInSubfolder.getSize() && smallestInSubfolder.getSize() < smallest.getSize()) {
				smallest = smallestInSubfolder;
			}
		}

		return smallest;
	}
}
