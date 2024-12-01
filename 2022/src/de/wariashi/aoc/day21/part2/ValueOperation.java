package de.wariashi.aoc.day21.part2;

public class ValueOperation implements Operation {

	private final double value;

	public ValueOperation(double value) {
		this.value = value;
	}

	@Override
	public double getResult() {
		return value;
	}

	@Override
	public String toString() {
		return Integer.toString((int) value);
	}
}
