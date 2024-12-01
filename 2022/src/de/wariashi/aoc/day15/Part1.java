package de.wariashi.aoc.day15;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.wariashi.aoc.common.AdventUtil;

public class Part1 {
	private static Set<Point> beacons = new HashSet<>();
	private static Map<Point, Integer> sensors = new HashMap<>();

	private static int minX;
	private static int maxX;

	public static void main(String[] args) {
		init(AdventUtil.getLines("src/de/wariashi/aoc/day15/input"));
		System.out.println(getBeaconFreeCoverageInLine(2000000));
	}

	private static int getBeaconFreeCoverageInLine(int line) {
		var result = 0;

		// add coverage
		for (int x = minX; x <= maxX; x++) {
			for (var sensor : sensors.entrySet()) {
				int distance = Math.abs(x - sensor.getKey().x) + Math.abs(line - sensor.getKey().y);
				if (distance <= sensor.getValue()) {
					result++;
					break;
				}
			}
		}

		// remove known beacons
		for (var beacon : beacons) {
			if (beacon.y == line) {
				result--;
			}
		}

		return result;
	}

	private static void init(List<String> rawInput) {
		for (var line : rawInput) {
			line = line.substring(12);
			var parts = line.split(", y=|: closest beacon is at x=");
			var sensorX = Integer.valueOf(parts[0]);
			var sensorY = Integer.valueOf(parts[1]);
			var beaconX = Integer.valueOf(parts[2]);
			var beaconY = Integer.valueOf(parts[3]);

			beacons.add(new Point(beaconX, beaconY));

			int distance = Math.abs(beaconX - sensorX) + Math.abs(beaconY - sensorY);
			sensors.put(new Point(sensorX, sensorY), distance);
		}

		minX = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		for (var sensor : sensors.entrySet()) {
			var sensorMinX = sensor.getKey().x - sensor.getValue();
			if (sensorMinX < minX) {
				minX = sensorMinX;
			}
			var sensorMaxX = sensor.getKey().x + sensor.getValue();
			if (maxX < sensorMaxX) {
				maxX = sensorMaxX;
			}
		}
	}
}
