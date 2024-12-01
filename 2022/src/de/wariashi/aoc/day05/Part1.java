package de.wariashi.aoc.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Part1 {
	@SuppressWarnings("unchecked")
	private static Stack<Character>[] crates = new Stack[10];// 0 is unused, but makes indexing easier

	public static void main(String[] args) {
		prefillCrates();

		File input = new File("src/de/wariashi/aoc/day05/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();

			while (currentLine != null) {
				if (!currentLine.startsWith("move")) {
					currentLine = inputReader.readLine();
					continue;
				}
				currentLine = currentLine.replace("move ", "");
				currentLine = currentLine.replace(" from ", ",");
				currentLine = currentLine.replace(" to ", ",");
				var parts = currentLine.split(",");
				int amount = Integer.valueOf(parts[0]);
				int from = Integer.valueOf(parts[1]);
				int to = Integer.valueOf(parts[2]);
				move(amount, from, to);
				currentLine = inputReader.readLine();
			}

			for (int i = 0; i < crates.length; i++) {
				System.out.print(crates[i].peek());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static void move(int amount, int from, int to) {
		for (int i = 0; i < amount; i++) {
			crates[to].add(crates[from].pop());
		}
	}

	private static void prefillCrates() {
		crates[0] = new Stack<>();
		crates[0].add(' ');

		crates[1] = new Stack<>();
		crates[1].add('R');
		crates[1].add('N');
		crates[1].add('P');
		crates[1].add('G');

		crates[2] = new Stack<>();
		crates[2].add('T');
		crates[2].add('J');
		crates[2].add('B');
		crates[2].add('L');
		crates[2].add('C');
		crates[2].add('S');
		crates[2].add('V');
		crates[2].add('H');

		crates[3] = new Stack<>();
		crates[3].add('T');
		crates[3].add('D');
		crates[3].add('B');
		crates[3].add('M');
		crates[3].add('N');
		crates[3].add('L');

		crates[4] = new Stack<>();
		crates[4].add('R');
		crates[4].add('V');
		crates[4].add('P');
		crates[4].add('S');
		crates[4].add('B');

		crates[5] = new Stack<>();
		crates[5].add('G');
		crates[5].add('C');
		crates[5].add('Q');
		crates[5].add('S');
		crates[5].add('W');
		crates[5].add('M');
		crates[5].add('V');
		crates[5].add('H');

		crates[6] = new Stack<>();
		crates[6].add('W');
		crates[6].add('Q');
		crates[6].add('S');
		crates[6].add('C');
		crates[6].add('D');
		crates[6].add('B');
		crates[6].add('J');

		crates[7] = new Stack<>();
		crates[7].add('F');
		crates[7].add('Q');
		crates[7].add('L');

		crates[8] = new Stack<>();
		crates[8].add('W');
		crates[8].add('M');
		crates[8].add('H');
		crates[8].add('T');
		crates[8].add('D');
		crates[8].add('L');
		crates[8].add('F');
		crates[8].add('V');

		crates[9] = new Stack<>();
		crates[9].add('L');
		crates[9].add('P');
		crates[9].add('B');
		crates[9].add('V');
		crates[9].add('M');
		crates[9].add('J');
		crates[9].add('F');
	}
}
