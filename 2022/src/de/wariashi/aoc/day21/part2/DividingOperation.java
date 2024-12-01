package de.wariashi.aoc.day21.part2;

import java.util.Map;

public class DividingOperation extends MonkeyDependentOperation {

	public DividingOperation(String firstName, String secondName, Map<String, Monkey> monkeys) {
		super(firstName, secondName, monkeys);
	}

	@Override
	public double getResult() {
		return getFirstMonkey().getResult() / getSecondMonkey().getResult();
	}

	@Override
	public String toString() {
		return "(" + getFirstMonkey().getOperation() + "/" + getSecondMonkey().getOperation() + ")";
	}
}
