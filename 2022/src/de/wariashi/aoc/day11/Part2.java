package de.wariashi.aoc.day11;

import java.util.ArrayList;
import java.util.List;

public class Part2 {
	@SuppressWarnings("unchecked")
	private static List<Long>[] monkey = new List[8];

	private static int[] totalNumberOfInspections = new int[8];

	public static void main(String[] args) {
		init();

		for (int round = 0; round < 10000; round++) {
			monkey[0].replaceAll(item -> (item * 13));
			while (!monkey[0].isEmpty()) {
				var item = monkey[0].remove(0);
				if (item % 19 == 0) {
					monkey[6].add(item);
				} else {
					monkey[2].add(item);
				}
				totalNumberOfInspections[0]++;
			}

			monkey[1].replaceAll(item -> (item + 7));
			while (!monkey[1].isEmpty()) {
				var item = monkey[1].remove(0);
				if (item % 5 == 0) {
					monkey[0].add(item);
				} else {
					monkey[3].add(item);
				}
				totalNumberOfInspections[1]++;
			}

			monkey[2].replaceAll(item -> (item + 6));
			while (!monkey[2].isEmpty()) {
				var item = monkey[2].remove(0);
				if (item % 11 == 0) {
					monkey[5].add(item);
				} else {
					monkey[7].add(item);
				}
				totalNumberOfInspections[2]++;
			}

			monkey[3].replaceAll(item -> (item + 5));
			while (!monkey[3].isEmpty()) {
				var item = monkey[3].remove(0);
				if (item % 17 == 0) {
					monkey[6].add(item);
				} else {
					monkey[0].add(item);
				}
				totalNumberOfInspections[3]++;
			}

			monkey[4].replaceAll(item -> (item + 8));
			while (!monkey[4].isEmpty()) {
				var item = monkey[4].remove(0);
				if (item % 7 == 0) {
					monkey[1].add(item);
				} else {
					monkey[3].add(item);
				}
				totalNumberOfInspections[4]++;
			}

			monkey[5].replaceAll(item -> (item * 5));
			while (!monkey[5].isEmpty()) {
				var item = monkey[5].remove(0);
				if (item % 13 == 0) {
					monkey[7].add(item);
				} else {
					monkey[4].add(item);
				}
				totalNumberOfInspections[5]++;
			}

			monkey[6].replaceAll(item -> (item * item));
			while (!monkey[6].isEmpty()) {
				var item = monkey[6].remove(0);
				if (item % 3 == 0) {
					monkey[5].add(item);
				} else {
					monkey[2].add(item);
				}
				totalNumberOfInspections[6]++;
			}

			monkey[7].replaceAll(item -> (item + 2));
			while (!monkey[7].isEmpty()) {
				var item = monkey[7].remove(0);
				if (item % 2 == 0) {
					monkey[1].add(item);
				} else {
					monkey[4].add(item);
				}
				totalNumberOfInspections[7]++;
			}

			reduceValues();
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
		monkey[0].add(91L);
		monkey[0].add(66L);

		monkey[1] = new ArrayList<>();
		monkey[1].add(78L);
		monkey[1].add(97L);
		monkey[1].add(59L);

		monkey[2] = new ArrayList<>();
		monkey[2].add(57L);
		monkey[2].add(59L);
		monkey[2].add(97L);
		monkey[2].add(84L);
		monkey[2].add(72L);
		monkey[2].add(83L);
		monkey[2].add(56L);
		monkey[2].add(76L);

		monkey[3] = new ArrayList<>();
		monkey[3].add(81L);
		monkey[3].add(78L);
		monkey[3].add(70L);
		monkey[3].add(58L);
		monkey[3].add(84L);

		monkey[4] = new ArrayList<>();
		monkey[4].add(60L);

		monkey[5] = new ArrayList<>();
		monkey[5].add(57L);
		monkey[5].add(69L);
		monkey[5].add(63L);
		monkey[5].add(75L);
		monkey[5].add(62L);
		monkey[5].add(77L);
		monkey[5].add(72L);

		monkey[6] = new ArrayList<>();
		monkey[6].add(73L);
		monkey[6].add(66L);
		monkey[6].add(86L);
		monkey[6].add(79L);
		monkey[6].add(98L);
		monkey[6].add(87L);

		monkey[7] = new ArrayList<>();
		monkey[7].add(95L);
		monkey[7].add(89L);
		monkey[7].add(63L);
		monkey[7].add(67L);
	}

	private static void reduceValues() {
		var supermodulo = 19L * 5 * 11 * 17 * 7 * 13 * 3 * 2;
		for (int i = 0; i < monkey.length; i++) {
			monkey[i].replaceAll(item -> item % supermodulo);
		}
	}
}
