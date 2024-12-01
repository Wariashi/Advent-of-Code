package de.wariashi.aoc.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
	private static final char ROCK = 'A';
	private static final char PAPER = 'B';
	private static final char SCISSORS = 'C';

	private static final char LOSS = 'X';
	private static final char DRAW = 'Y';
	private static final char WIN = 'Z';

	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day02/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			var score = 0;
			while (currentLine != null) {
				var elfSign = currentLine.charAt(0);
				var expectedResult = currentLine.charAt(2);
				score += getScore(elfSign, expectedResult);
				currentLine = inputReader.readLine();
			}
			System.out.println("My score is: " + score);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static int getScore(char elfSign, char expectedResult) {
		int score = 0;

		// add points for the outcome
		if (expectedResult == DRAW) {
			score += 3;
		} else if (expectedResult == WIN) {
			score += 6;
		}

		// add points for selecting a shape
		switch (elfSign) {
		case ROCK:
			switch (expectedResult) {
			case LOSS:
				score += 3;// for selecting scissors
				break;
			case DRAW:
				score += 1;// for selecting rock
				break;
			case WIN:
				score += 2;// for selecting paper
				break;
			default:
				break;
			}
			break;
		case PAPER:
			switch (expectedResult) {
			case LOSS:
				score += 1;// for selecting rock
				break;
			case DRAW:
				score += 2;// for selecting paper
				break;
			case WIN:
				score += 3;// for selecting scissors
				break;
			default:
				break;
			}
			break;
		case SCISSORS:
			switch (expectedResult) {
			case LOSS:
				score += 2;// for selecting paper
				break;
			case DRAW:
				score += 3;// for selecting scissors
				break;
			case WIN:
				score += 1;// for selecting rock
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}

		return score;
	}
}
