package de.wariashi.aoc.day11;

import java.util.ArrayList;
import java.util.List;

public class Part1 {
	@SuppressWarnings("unchecked")
	private static List<Integer>[] monkey = new List[8];

	private static int[] totalNumberOfInspections = new int[8];

	public static void main(String[] args) {
		init();

		for (int round = 0; round < 20; round++) {
			monkey[0].replaceAll(item -> (item * 13 / 3));
			while (!monkey[0].isEmpty()) {
				var item = monkey[0].remove(0);
				if (item % 19 == 0) {
					monkey[6].add(item);
				} else {
					monkey[2].add(item);
				}
				totalNumberOfInspections[0]++;
			}

			monkey[1].replaceAll(item -> (item + 7) / 3);
			while (!monkey[1].isEmpty()) {
				var item = monkey[1].remove(0);
				if (item % 5 == 0) {
					monkey[0].add(item);
				} else {
					monkey[3].add(item);
				}
				totalNumberOfInspections[1]++;
			}

			monkey[2].replaceAll(item -> (item + 6) / 3);
			while (!monkey[2].isEmpty()) {
				var item = monkey[2].remove(0);
				if (item % 11 == 0) {
					monkey[5].add(item);
				} else {
					monkey[7].add(item);
				}
				totalNumberOfInspections[2]++;
			}

			monkey[3].replaceAll(item -> (item + 5) / 3);
			while (!monkey[3].isEmpty()) {
				var item = monkey[3].remove(0);
				if (item % 17 == 0) {
					monkey[6].add(item);
				} else {
					monkey[0].add(item);
				}
				totalNumberOfInspections[3]++;
			}

			monkey[4].replaceAll(item -> (item + 8) / 3);
			while (!monkey[4].isEmpty()) {
				var item = monkey[4].remove(0);
				if (item % 7 == 0) {
					monkey[1].add(item);
				} else {
					monkey[3].add(item);
				}
				totalNumberOfInspections[4]++;
			}

			monkey[5].replaceAll(item -> (item * 5) / 3);
			while (!monkey[5].isEmpty()) {
				var item = monkey[5].remove(0);
				if (item % 13 == 0) {
					monkey[7].add(item);
				} else {
					monkey[4].add(item);
				}
				totalNumberOfInspections[5]++;
			}

			monkey[6].replaceAll(item -> (item * item) / 3);
			while (!monkey[6].isEmpty()) {
				var item = monkey[6].remove(0);
				if (item % 3 == 0) {
					monkey[5].add(item);
				} else {
					monkey[2].add(item);
				}
				totalNumberOfInspections[6]++;
			}

			monkey[7].replaceAll(item -> (item + 2) / 3);
			while (!monkey[7].isEmpty()) {
				var item = monkey[7].remove(0);
				if (item % 2 == 0) {
					monkey[1].add(item);
				} else {
					monkey[4].add(item);
				}
				totalNumberOfInspections[7]++;
			}
		}

		System.out.println(totalNumberOfInspections[0]);
		System.out.println(totalNumberOfInspections[1]);
		System.out.println(totalNumberOfInspections[2]);
		System.out.println(totalNumberOfInspections[3]);
		System.out.println(totalNumberOfInspections[4]);
		System.out.println(totalNumberOfInspections[5]);
		System.out.println(totalNumberOfInspections[6]);
		System.out.println(totalNumberOfInspections[7]);
	}

	private static void init() {
		monkey[0] = new ArrayList<>();
		monkey[0].add(91);
		monkey[0].add(66);

		monkey[1] = new ArrayList<>();
		monkey[1].add(78);
		monkey[1].add(97);
		monkey[1].add(59);

		monkey[2] = new ArrayList<>();
		monkey[2].add(57);
		monkey[2].add(59);
		monkey[2].add(97);
		monkey[2].add(84);
		monkey[2].add(72);
		monkey[2].add(83);
		monkey[2].add(56);
		monkey[2].add(76);

		monkey[3] = new ArrayList<>();
		monkey[3].add(81);
		monkey[3].add(78);
		monkey[3].add(70);
		monkey[3].add(58);
		monkey[3].add(84);

		monkey[4] = new ArrayList<>();
		monkey[4].add(60);

		monkey[5] = new ArrayList<>();
		monkey[5].add(57);
		monkey[5].add(69);
		monkey[5].add(63);
		monkey[5].add(75);
		monkey[5].add(62);
		monkey[5].add(77);
		monkey[5].add(72);

		monkey[6] = new ArrayList<>();
		monkey[6].add(73);
		monkey[6].add(66);
		monkey[6].add(86);
		monkey[6].add(79);
		monkey[6].add(98);
		monkey[6].add(87);

		monkey[7] = new ArrayList<>();
		monkey[7].add(95);
		monkey[7].add(89);
		monkey[7].add(63);
		monkey[7].add(67);
	}
}
