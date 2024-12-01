package de.wariashi.aoc.day21.part2;

public class UnknownOperation implements Operation {

	private final double dummyValue;

	public UnknownOperation(double dummyValue) {
		this.dummyValue = dummyValue;
	}

	@Override
	public double getResult() {
		return dummyValue;
	}

	@Override
	public String toString() {
		return "x";
	}

}
