package de.wariashi.aoc.day21.part2;

import java.util.HashMap;
import java.util.Map;

import de.wariashi.aoc.common.AdventUtil;

public class Part2 {
	private static Map<String, Monkey> monkeys;

	public static void main(String[] args) {
		init();
		var root = monkeys.get("root");
		if (root.getOperation() instanceof MonkeyDependentOperation operation) {
			var monkey1 = operation.getFirstMonkey();
			var monkey2 = operation.getSecondMonkey();
			if (monkey1.isDependentOnHuman()) {
				System.out.println(monkey2.getOperation() + "=" + monkey1.getOperation());
				var expectedResult = monkey2.getResult();
				monkey1.makeResult(expectedResult);
			} else {
				System.out.println(monkey1.getOperation() + "=" + monkey2.getOperation());
				var expectedResult = monkey1.getResult();
				monkey2.makeResult(expectedResult);
			}
		}
	}

	private static void init() {
		monkeys = new HashMap<>();

		var input = AdventUtil.getLines("src/de/wariashi/aoc/day21/input");
		for (var line : input) {
			var parts = line.split(": ");
			var name = parts[0];
			var operation = parts[1];
			monkeys.put(name, new Monkey(name, operation, monkeys));
		}
	}
}
