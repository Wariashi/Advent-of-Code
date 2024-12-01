package de.wariashi.aoc.day21.part2;

import java.util.Map;

public class Monkey {
	private static final String HUMAN = "humn";

	private String name;
	private Operation operation;

	public Monkey(String name, String operation, Map<String, Monkey> monkeys) {
		this.name = name;
		if (name.equals(HUMAN)) {
			this.operation = new UnknownOperation(Double.parseDouble(operation));
		} else if (operation.contains("+")) {
			var parts = operation.split(" \\+ ");
			this.operation = new AddingOperation(parts[0], parts[1], monkeys);
		} else if (operation.contains("-")) {
			var parts = operation.split(" - ");
			this.operation = new SubtractingOperation(parts[0], parts[1], monkeys);
		} else if (operation.contains("*")) {
			var parts = operation.split(" \\* ");
			this.operation = new MultiplyingOperation(parts[0], parts[1], monkeys);
		} else if (operation.contains("/")) {
			var parts = operation.split(" / ");
			this.operation = new DividingOperation(parts[0], parts[1], monkeys);
		} else {
			this.operation = new ValueOperation(Double.parseDouble(operation));
		}
	}

	public String getName() {
		return name;
	}

	public Operation getOperation() {
		return operation;
	}

	public double getResult() {
		return operation.getResult();
	}

	public boolean isDependentOnHuman() {
		if (HUMAN.equals(name)) {
			return true;
		} else if (operation instanceof MonkeyDependentOperation mdo) {
			var monkey1 = mdo.getFirstMonkey();
			var monkey2 = mdo.getSecondMonkey();
			return monkey1.isDependentOnHuman() || monkey2.isDependentOnHuman();
		}
		return false;
	}

	public void makeResult(double expectedResult) {
		System.out.println(expectedResult + "=" + operation);

		if (HUMAN.equals(name)) {
			System.out.println("I need to be " + expectedResult + " instead of " + operation.getResult());
			System.out.println("Or as Long " + (long) expectedResult);
			/*
			 * except it isn't, but https://quickmath.com/ does the trick by solving the
			 * equation printed in Part2.java
			 */

			System.exit(0);
		}
		if (operation instanceof AddingOperation addOp) {
			var monkey1 = addOp.getFirstMonkey();
			var monkey2 = addOp.getSecondMonkey();
			if (monkey1.isDependentOnHuman()) {
				var result = expectedResult - monkey2.getResult();
				monkey1.makeResult(result);
			} else {
				var result = expectedResult - monkey1.getResult();
				monkey2.makeResult(result);
			}
		} else if (operation instanceof DividingOperation divOp) {
			var monkey1 = divOp.getFirstMonkey();
			var monkey2 = divOp.getSecondMonkey();
			if (monkey1.isDependentOnHuman()) {
				var result = expectedResult * monkey2.getResult();
				monkey1.makeResult(result);
			} else {
				var result = monkey1.getResult() / expectedResult;
				monkey2.makeResult(result);
			}
		} else if (operation instanceof MultiplyingOperation multOp) {
			var monkey1 = multOp.getFirstMonkey();
			var monkey2 = multOp.getSecondMonkey();
			if (monkey1.isDependentOnHuman()) {
				var result = expectedResult / monkey2.getResult();
				monkey1.makeResult(result);
			} else {
				var result = expectedResult / monkey1.getResult();
				monkey2.makeResult(result);
			}
		} else if (operation instanceof SubtractingOperation subOp) {
			var monkey1 = subOp.getFirstMonkey();
			var monkey2 = subOp.getSecondMonkey();
			if (monkey1.isDependentOnHuman()) {
				var result = expectedResult + monkey2.getResult();
				monkey1.makeResult(result);
			} else {
				var result = expectedResult - monkey1.getResult();
				monkey2.makeResult(result);
			}
		}
	}
}
