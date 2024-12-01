package de.wariashi.aoc.day13;

import java.util.ArrayDeque;
import java.util.Deque;

public class Pair {
	private PacketData packet1;
	private PacketData packet2;

	public Pair(String packet1, String packet2) {
		this.packet1 = parse(packet1);
		this.packet2 = parse(packet2);
	}

	public boolean isInRightOrder() {
		System.out.println();
		System.out.println("Compare " + packet1 + " vs " + packet2);
		return isInRightOrder(packet1, packet2) == Result.IN_ORDER;
	}

	private Result isInRightOrder(PacketData left, PacketData right) {
		if (left instanceof PacketValue l && right instanceof PacketValue r) {
			if (l.getValue() < r.getValue()) {
				System.out.println("Left side is smaller, so inputs are in the right order");
				return Result.IN_ORDER;
			} else if (r.getValue() < l.getValue()) {
				System.out.println("Right side is smaller, so inputs are not in the right order");
				return Result.NOT_IN_ORDER;
			}
		} else if (left instanceof PacketList l && right instanceof PacketList r) {
			var size = Math.max(l.size(), r.size());
			for (int i = 0; i < size; i++) {
				if (l.size() <= i) {
					System.out.println("Left list running out of elements");
					return Result.IN_ORDER;
				} else if (r.size() <= i) {
					System.out.println("Left list running out of elements");
					return Result.NOT_IN_ORDER;
				}

				System.out.println("Compare " + l.get(i) + " vs " + r.get(i));
				if (l.get(i).equals(r.get(i))) {
					continue;
				}
				var subresult = isInRightOrder(l.get(i), r.get(i));
				if (subresult != Result.UNKNOWN) {
					return subresult;
				}
			}
		} else if (left instanceof PacketList && right instanceof PacketValue) {
			var list = new PacketList();
			list.add(right);
			System.out.println("Mixed types; convert right to " + list + " and retry comparison");
			return isInRightOrder(left, list);
		} else if (left instanceof PacketValue && right instanceof PacketList) {
			var list = new PacketList();
			list.add(left);
			System.out.println("Mixed types; convert left to " + list + " and retry comparison");
			return isInRightOrder(list, right);
		}

		return Result.UNKNOWN;
	}

	private PacketData parse(String data) {
		data = data.substring(1, data.length() - 1);

		Deque<PacketList> stack = new ArrayDeque<>();
		stack.push(new PacketList());

		while (!data.isEmpty()) {
			if (data.startsWith("[")) {
				var list = new PacketList();
				stack.peek().add(list);
				stack.push(list);
				data = data.substring(1);
			} else if (data.startsWith("]")) {
				stack.pop();
				data = data.substring(1);
			} else if (data.startsWith(",")) {
				data = data.substring(1);
			} else {
				var endIndex = data.length();
				var firstBracket = data.indexOf(']');
				if (firstBracket != -1 && firstBracket < endIndex) {
					endIndex = firstBracket;
				}
				var firstColon = data.indexOf(',');
				if (firstColon != -1 && firstColon < endIndex) {
					endIndex = firstColon;
				}
				var value = Integer.parseInt(data.substring(0, endIndex));
				stack.peek().add(new PacketValue(value));
				data = data.substring(endIndex);
			}
		}

		return stack.pop();
	}
}
