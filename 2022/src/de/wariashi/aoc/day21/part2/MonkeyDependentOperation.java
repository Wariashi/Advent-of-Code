package de.wariashi.aoc.day21.part2;

import java.util.Map;

public abstract class MonkeyDependentOperation implements Operation {
	protected final String firstName;
	protected final String secondName;
	protected final Map<String, Monkey> monkeys;

	protected MonkeyDependentOperation(String firstName, String secondName, Map<String, Monkey> monkeys) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.monkeys = monkeys;
	}

	public Monkey getFirstMonkey() {
		return monkeys.get(firstName);
	}

	public Monkey getSecondMonkey() {
		return monkeys.get(secondName);
	}
}
