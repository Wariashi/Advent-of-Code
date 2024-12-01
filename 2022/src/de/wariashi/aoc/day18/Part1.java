package de.wariashi.aoc.day18;

import de.wariashi.aoc.common.AdventUtil;

public class Part1 {
	private static boolean[][][] droplet;

	public static void main(String[] args) {
		initDroplet();
		System.out.println(calculateSurfaceArea());
	}

	private static int calculateSurfaceArea() {
		var surfaceArea = 0;

		for (int z = 0; z < droplet[0][0].length; z++) {
			for (int y = 0; y < droplet[0].length; y++) {
				for (int x = 0; x < droplet.length; x++) {
					if (droplet[x][y][z]) {
						var neighbors = getNeighbors(x, y, z);
						var exposedSquares = 6 - neighbors;
						surfaceArea += exposedSquares;
					}
				}
			}
		}

		return surfaceArea;
	}

	private static int getNeighbors(int x, int y, int z) {
		var neighbors = 0;

		// x
		if (0 < x && droplet[x - 1][y][z]) {
			neighbors++;
		}
		if (x < droplet.length - 1 && droplet[x + 1][y][z]) {
			neighbors++;
		}

		// y
		if (0 < y && droplet[x][y - 1][z]) {
			neighbors++;
		}
		if (y < droplet[0].length - 1 && droplet[x][y + 1][z]) {
			neighbors++;
		}

		// z
		if (0 < z && droplet[x][y][z - 1]) {
			neighbors++;
		}
		if (z < droplet[0][0].length - 1 && droplet[x][y][z + 1]) {
			neighbors++;
		}

		return neighbors;
	}

	private static void initDroplet() {
		var input = AdventUtil.getLines("src/de/wariashi/aoc/day18/input");

		// get the size of the droplet
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int minZ = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		int maxZ = Integer.MIN_VALUE;
		for (var line : input) {
			var parts = line.split(",");

			var x = Integer.parseInt(parts[0]);
			if (x < minX) {
				minX = x;
			}
			if (maxX < x) {
				maxX = x;
			}

			var y = Integer.parseInt(parts[1]);
			if (y < minY) {
				minY = y;
			}
			if (maxY < y) {
				maxY = y;
			}

			var z = Integer.parseInt(parts[2]);
			if (z < minZ) {
				minZ = z;
			}
			if (maxZ < z) {
				maxZ = z;
			}
		}
		var sizeX = maxX - minX + 1;
		var sizeY = maxY - minY + 1;
		var sizeZ = maxZ - minZ + 1;
		droplet = new boolean[sizeX][sizeY][sizeZ];

		// fill the droplet
		for (var line : input) {
			var parts = line.split(",");
			var x = Integer.parseInt(parts[0]) - minX;
			var y = Integer.parseInt(parts[1]) - minY;
			var z = Integer.parseInt(parts[2]) - minZ;
			droplet[x][y][z] = true;
		}
	}
}
