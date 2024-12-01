package de.wariashi.aoc.day21.part1;

import java.util.HashMap;
import java.util.Map;

import de.wariashi.aoc.common.AdventUtil;

public class Part1 {
	private static Map<String, Monkey> monkeys;

	public static void main(String[] args) {
		init();
		var result = monkeys.get("root").getResult();
		System.out.println(result);
	}

	private static void init() {
		monkeys = new HashMap<>();

		var input = AdventUtil.getLines("src/de/wariashi/aoc/day21/input");
		for (var line : input) {
			var parts = line.split(": ");
			var name = parts[0];
			var operation = parts[1];
			monkeys.put(name, new Monkey(operation, monkeys));
		}
	}
}
