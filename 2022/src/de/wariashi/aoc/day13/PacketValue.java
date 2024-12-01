package de.wariashi.aoc.day13;

public class PacketValue implements PacketData {
	private final int value;

	public PacketValue(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof PacketValue)) {
			return false;
		}
		return this.value == ((PacketValue) other).value;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return String.valueOf(value);
	}
}
