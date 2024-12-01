package de.wariashi.aoc.day21.part1;

import java.util.Map;

public class Monkey {
	private final Map<String, Monkey> monkeys;
	private final String operation;

	public Monkey(String operation, Map<String, Monkey> monkeys) {
		this.operation = operation;
		this.monkeys = monkeys;
	}

	public double getResult() {
		if (operation.contains("+")) {
			var parts = operation.split(" \\+ ");
			var firstResult = monkeys.get(parts[0]).getResult();
			var secondResult = monkeys.get(parts[1]).getResult();
			return firstResult + secondResult;
		} else if (operation.contains("-")) {
			var parts = operation.split(" - ");
			var firstResult = monkeys.get(parts[0]).getResult();
			var secondResult = monkeys.get(parts[1]).getResult();
			return firstResult - secondResult;
		} else if (operation.contains("*")) {
			var parts = operation.split(" \\* ");
			var firstResult = monkeys.get(parts[0]).getResult();
			var secondResult = monkeys.get(parts[1]).getResult();
			return firstResult * secondResult;
		} else if (operation.contains("/")) {
			var parts = operation.split(" / ");
			var firstResult = monkeys.get(parts[0]).getResult();
			var secondResult = monkeys.get(parts[1]).getResult();
			return firstResult / secondResult;
		} else {
			return Double.parseDouble(operation);
		}
	}
}
