package de.wariashi.aoc.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

	private static List<List<Integer>> packets = new ArrayList<>();

	public static void main(String[] args) {
		init();

		var firstDivider = new ArrayList<Integer>();
		firstDivider.add(2);
		packets.add(firstDivider);

		var secondDivider = new ArrayList<Integer>();
		secondDivider.add(6);
		packets.add(secondDivider);

		packets.sort((List<Integer> list1, List<Integer> list2) -> {
			var size = Math.max(list1.size(), list2.size());
			for (int i = 0; i < size; i++) {
				if (list1.size() <= i) {
					return -1;
				} else if (list2.size() <= i) {
					return 1;
				} else if (!list1.get(i).equals(list2.get(i))) {
					return list1.get(i).compareTo(list2.get(i));
				}
			}
			return 0;
		});

		for (int i = 0; i < packets.size(); i++) {
			var packet = packets.get(i);
			System.out.print(i + 1 + ": ");
			for (var item : packet) {
				System.out.print(item + ",");
			}
			System.out.println();
		}

		// 133: 2,
		// 195: 6,
	}

	private static void init() {
		File input = new File("src/de/wariashi/aoc/day13/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			while (currentLine != null) {
				if (!currentLine.isEmpty()) {
					currentLine = currentLine.replace("[]", "-1");
					currentLine = currentLine.replace("[", "");
					currentLine = currentLine.replace("]", "");
					var parts = currentLine.split(",");
					List<Integer> packet = new ArrayList<>();
					for (var part : parts) {
						if (!part.isEmpty()) {
							packet.add(Integer.valueOf(part));
						}
					}
					packets.add(packet);
				}
				currentLine = inputReader.readLine();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
