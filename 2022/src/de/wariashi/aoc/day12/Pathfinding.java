package de.wariashi.aoc.day12;

import java.awt.Point;

public class Pathfinding {
	private final int height;
	private final int width;
	private final int[][] elevation;

	private int[][] distance;

	public Pathfinding(int[][] elevation, Point target) {
		height = elevation[0].length;
		width = elevation.length;
		this.elevation = elevation;

		distance = new int[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				distance[x][y] = Integer.MAX_VALUE;
			}
		}
		distance[target.x][target.y] = 0;
		calculateDistances(target.x, target.y);

		debug();
	}

	public int getDistanceAt(Point start) {
		return distance[start.x][start.y];
	}

	private void calculateDistances(int x, int y) {
		// east
		if (x < width - 2) {
			var slope = elevation[x][y] - elevation[x + 1][y];
			if (slope <= 1 && distance[x][y] < distance[x + 1][y]) {
				var before = distance[x + 1][y];
				distance[x + 1][y] = distance[x][y] + 1;
				var after = distance[x + 1][y];
				if (before != after) {
					calculateDistances(x + 1, y);
				}
			}
		}

		// north
		if (0 < y) {
			var slope = elevation[x][y] - elevation[x][y - 1];
			if (slope <= 1 && distance[x][y] < distance[x][y - 1]) {
				var before = distance[x][y - 1];
				distance[x][y - 1] = distance[x][y] + 1;
				var after = distance[x][y - 1];
				if (before != after) {
					calculateDistances(x, y - 1);
				}
			}
		}

		// south
		if (y < height - 2) {
			var slope = elevation[x][y] - elevation[x][y + 1];
			if (slope <= 1 && distance[x][y] < distance[x][y + 1]) {
				var before = distance[x][y + 1];
				distance[x][y + 1] = distance[x][y] + 1;
				var after = distance[x][y + 1];
				if (before != after) {
					calculateDistances(x, y + 1);
				}
			}
		}

		// west
		if (0 < x) {
			var slope = elevation[x][y] - elevation[x - 1][y];
			if (slope <= 1 && distance[x][y] < distance[x - 1][y]) {
				var before = distance[x - 1][y];
				distance[x - 1][y] = distance[x][y] + 1;
				var after = distance[x - 1][y];
				if (before != after) {
					calculateDistances(x - 1, y);
				}
			}
		}
	}

	private void debug() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				System.out.print((char) (distance[x][y] + 48));
			}
			System.out.println();
		}
	}
}
