package de.wariashi.aoc.day09;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
	private static boolean[][] visited;

	private static Point[] rope = new Point[10];

	private static Point head;
	private static Point tail;

	private static final List<Motion> motions = new ArrayList<>();

	public static void main(String[] args) {
		File input = new File("src/de/wariashi/aoc/day09/input");
		try (var fileReader = new FileReader(input); var inputReader = new BufferedReader(fileReader)) {

			var currentLine = inputReader.readLine();
			while (currentLine != null) {
				var parts = currentLine.split(" ");
				var direction = parts[0].charAt(0);
				var count = Integer.valueOf(parts[1]);
				motions.add(new Motion(count, direction));
				currentLine = inputReader.readLine();
			}

			initialize();
			move();
			printMap();

			int count = 0;
			for (int y = 0; y < visited[0].length; y++) {
				for (int x = 0; x < visited.length; x++) {
					if (visited[x][y]) {
						count++;
					}
				}
			}
			System.out.println(count);

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private static void initialize() {
		int x = 0;
		int y = 0;

		int minX = 0;
		int minY = 0;
		int maxX = 0;
		int maxY = 0;

		for (var motion : motions) {
			switch (motion.direction()) {
			case 'D':
				y += motion.count();
				maxY = Math.max(maxY, y);
				break;
			case 'L':
				x -= motion.count();
				minX = Math.min(x, minX);
				break;
			case 'R':
				x += motion.count();
				maxX = Math.max(maxX, x);
				break;
			case 'U':
				y -= motion.count();
				minY = Math.min(y, minY);
				break;
			default:
				System.out.println("Unknown direction: " + motion.direction());
				break;
			}
		}

		int width = maxX - minX + 1;
		int height = maxY - minY + 1;

		visited = new boolean[width][height];

		for (int i = 0; i < 10; i++) {
			rope[i] = new Point(-minX, -minY);
		}
		head = rope[0];
		tail = rope[9];

		visited[tail.x][tail.y] = true;
	}

	private static void move() {
		for (var motion : motions) {
			for (int i = 0; i < motion.count(); i++) {
				moveHead(motion.direction());
			}
		}
	}

	private static void moveHead(char direction) {
		switch (direction) {
		case 'D':
			head.y++;
			break;
		case 'L':
			head.x--;
			break;
		case 'R':
			head.x++;
			break;
		case 'U':
			head.y--;
			break;
		default:
			System.out.println("Unknown direction: " + direction);
			break;
		}
		for (int i = 0; i < 9; i++) {
			moveTail(rope[i], rope[i + 1]);
		}
		visited[tail.x][tail.y] = true;
	}

	private static void moveTail(Point head, Point tail) {
		if (Math.abs(head.x - tail.x) < 2 && Math.abs(head.y - tail.y) < 2) {
			// still connected, nothing to do
			return;
		}

		if (head.x == tail.x) {
			// vertical movement
			if (tail.y < head.y) {
				// down
				tail.y++;
			} else if (head.y < tail.y) {
				// up
				tail.y--;
			}
		} else if (head.y == tail.y) {
			// horizontal movement
			if (head.x < tail.x) {
				// left
				tail.x--;
			} else if (tail.x < head.x) {
				// right
				tail.x++;
			}
		} else {
			// diagonal movement
			if (tail.x < head.x && head.y < tail.y) {
				// north east
				tail.x++;
				tail.y--;
			} else if (head.x < tail.x && head.y < tail.y) {
				// north west
				tail.x--;
				tail.y--;
			} else if (tail.x < head.x && tail.y < head.y) {
				// south east
				tail.x++;
				tail.y++;
			} else if (head.x < tail.x && tail.y < head.y) {
				// south west
				tail.x--;
				tail.y++;
			}
		}
	}

	private static void printMap() {
		for (int y = 0; y < visited[0].length; y++) {
			for (int x = 0; x < visited.length; x++) {
				System.out.print(visited[x][y] ? "*" : ".");
			}
			System.out.println();
		}
	}
}
