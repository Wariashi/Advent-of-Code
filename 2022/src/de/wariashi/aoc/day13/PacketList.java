package de.wariashi.aoc.day13;

import java.util.ArrayList;
import java.util.List;

public class PacketList implements PacketData {

	private List<PacketData> data = new ArrayList<>();

	public void add(PacketData data) {
		this.data.add(data);
	}

	public PacketData get(int index) {
		return data.get(index);
	}

	public int size() {
		return data.size();
	}

	@Override
	public String toString() {
		var result = new StringBuilder();
		result.append("[");
		for (int i = 0; i < data.size(); i++) {
			if (i != 0) {
				result.append(',');
			}
			result.append(data.get(i));
		}
		result.append("]");
		return result.toString();
	}
}
