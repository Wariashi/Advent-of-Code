package de.wariashi.aoc.day18;

import de.wariashi.aoc.common.AdventUtil;

public class Part2 {
	private static int sizeX;
	private static int sizeY;
	private static int sizeZ;

	private static boolean[][][] droplet;
	private static boolean[][][] water;

	public static void main(String[] args) {
		initDroplet();
		initWater();

		flood(0, 0, 0);
		removeAirPockets();

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

	private static void flood(int x, int y, int z) {
		if (x < 0 || sizeX <= x || y < 0 || sizeY <= y || z < 0 || sizeZ <= z) {
			// out of bounds
			return;
		}
		if (water[x][y][z]) {
			// been here, done that
			return;
		}
		if (droplet[x][y][z]) {
			// occupied by lava
			return;
		}

		water[x][y][z] = true;
		flood(x - 1, y, z);
		flood(x + 1, y, z);
		flood(x, y - 1, z);
		flood(x, y + 1, z);
		flood(x, y, z - 1);
		flood(x, y, z + 1);
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
		sizeX = maxX - minX + 3;
		sizeY = maxY - minY + 3;
		sizeZ = maxZ - minZ + 3;
		droplet = new boolean[sizeX][sizeY][sizeZ];

		// fill the droplet
		for (var line : input) {
			var parts = line.split(",");
			var x = Integer.parseInt(parts[0]) - minX + 1;
			var y = Integer.parseInt(parts[1]) - minY + 1;
			var z = Integer.parseInt(parts[2]) - minZ + 1;
			droplet[x][y][z] = true;
		}
	}

	private static void initWater() {
		water = new boolean[sizeX][sizeY][sizeZ];
	}

	private static void removeAirPockets() {
		for (int z = 0; z < droplet[0][0].length; z++) {
			for (int y = 0; y < droplet[0].length; y++) {
				for (int x = 0; x < droplet.length; x++) {
					if (!water[x][y][z]) {
						droplet[x][y][z] = true;
					}
				}
			}
		}
	}
}
