package de.wariashi.aoc.day20;

import java.util.ArrayList;
import java.util.List;

import de.wariashi.aoc.common.AdventUtil;

public class Part1 {
	private static List<Number1> file;

	public static void main(String[] args) {
		init();
		for (int i = 0; i < file.size(); i++) {
			move(i);
		}
		var indexOfZero = getIndexOfZero();
		var valueAt1k = file.get((indexOfZero + 1000) % file.size()).value();
		var valueAt2k = file.get((indexOfZero + 2000) % file.size()).value();
		var valueAt3k = file.get((indexOfZero + 3000) % file.size()).value();
		var result = valueAt1k + valueAt2k + valueAt3k;
		System.out.println("Result: " + result);
	}

	private static int getIndexOf(int initialPosition) {
		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).initialPosition() == initialPosition) {
				return i;
			}
		}
		return -1;
	}

	private static int getIndexOfZero() {
		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).value() == 0) {
				return i;
			}
		}
		return -1;
	}

	private static void init() {
		file = new ArrayList<>();

		var input = AdventUtil.getLines("src/de/wariashi/aoc/day20/input");
		for (int i = 0; i < input.size(); i++) {
			var line = input.get(i);
			var value = Integer.parseInt(line);
			file.add(new Number1(i, value));
		}
	}

	private static void move(int initialPosition) {
		var index = getIndexOf(initialPosition);
		var number = file.get(index);
		var normalized = normalize(number.value());
		file.remove(index);
		if (index + normalized < file.size()) {
			// without wrapping
			file.add(index + normalized, number);
		} else {
			// with wrapping
			file.add(index + normalized - file.size(), number);
		}
	}

	private static int normalize(int value) {
		var otherNumbers = file.size() - 1;
		value = value % (otherNumbers);
		if (value < 0) {
			value += otherNumbers;
		}
		return value;
	}
}
