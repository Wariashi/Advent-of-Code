package de.wariashi.aoc.day25;

import de.wariashi.aoc.common.AdventUtil;

public class Part1 {

	public static void main(String[] args) {
		var input = AdventUtil.getLines("src/de/wariashi/aoc/day25/input");
		long sum = 0;
		for (var line : input) {
			sum += snafuToDecimal(line);
		}
		System.out.println("Sum: " + sum);
		var snafu = decimalToSnafu(sum);
		System.out.println("SNAFU: " + snafu);
	}

	private static String decimalToSnafu(long decimal) {
		var result = new StringBuilder();
		while (1 <= decimal) {
			var rest = decimal % 5;
			switch ((int) rest) {
				case 0:
					result.insert(0, "0");
					break;
				case 1:
					result.insert(0, "1");
					decimal -= 1;
					break;
				case 2:
					result.insert(0, "2");
					decimal -= 2;
					break;
				case 3:
					result.insert(0, "=");
					decimal += 2;
					break;
				case 4:
					result.insert(0, "-");
					decimal += 1;
					break;
				default:
					break;
			}
			decimal /= 5;
		}
		return result.toString();
	}

	private static long snafuToDecimal(String snafu) {
		var decimal = 0L;
		var factor = 1L;
		for (int i = 1; i <= snafu.length(); i++) {
			var character = snafu.charAt(snafu.length() - i);
			switch (character) {
				case '=':
					decimal -= 2 * factor;
					break;
				case '-':
					decimal -= factor;
					break;
				case '0':
					break;
				case '1':
					decimal += factor;
					break;
				case '2':
					decimal += 2 * factor;
					break;
				default:
					break;
			}
			factor *= 5;
		}
		return decimal;
	}
}
