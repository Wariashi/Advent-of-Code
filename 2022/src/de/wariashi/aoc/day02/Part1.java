package de.wariashi.aoc.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
	private static final char ELF_ROCK = 'A';
	private static final char ELF_PAPER = 'B';
	private static final char ELF_SCISSORS = 'C';

	private static final char ME_ROCK = 'X';
	private static final char ME_PAPER = 'Y';
	private static final char ME_SCISSORS = 'Z';

	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day02/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {
			var currentLine = inputReader.readLine();
			var score = 0;
			while (currentLine != null) {
				var elfSign = currentLine.charAt(0);
				var mySign = currentLine.charAt(2);
				score += getScore(mySign, elfSign);
				currentLine = inputReader.readLine();
			}
			System.out.println("My score is: " + score);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static int getScore(char mySign, char elfSign) {
		int score = 0;

		// add points for selecting a shape
		if (mySign == ME_ROCK) {
			// plus 1 for picking rock
			score += 1;
		} else if (mySign == ME_PAPER) {
			// plus 2 for picking paper
			score += 2;
		} else if (mySign == ME_SCISSORS) {
			// plus 3 for picking scissors
			score += 3;
		}

		// add points for the outcome
		switch (mySign) {
		case ME_ROCK:
			switch (elfSign) {
			case ELF_ROCK:
				score += 3;// draw
				break;
			case ELF_PAPER:
				break;
			case ELF_SCISSORS:
				score += 6;// win
				break;
			default:
				break;
			}
			break;
		case ME_PAPER:
			switch (elfSign) {
			case ELF_ROCK:
				score += 6;// win
				break;
			case ELF_PAPER:
				score += 3;// draw
				break;
			case ELF_SCISSORS:
				break;
			default:
				break;
			}
			break;
		case ME_SCISSORS:
			switch (elfSign) {
			case ELF_ROCK:
				break;
			case ELF_PAPER:
				score += 6;// win
				break;
			case ELF_SCISSORS:
				score += 3;// draw
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
