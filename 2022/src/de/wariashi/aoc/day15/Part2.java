package de.wariashi.aoc.day15;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.wariashi.aoc.common.AdventUtil;

public class Part2 {
	private static final int MIN_X = 0;
	private static final int MIN_Y = 0;
	private static final int MAX_X = 4_000_000;
	private static final int MAX_Y = 4_000_000;

	private static Map<Point, Integer> sensors = new HashMap<>();

	public static void main(String[] args) {
		initSensors(AdventUtil.getLines("src/de/wariashi/aoc/day15/input"));
		var candidates = getDistressCandidates();
		System.out.println("Found " + candidates.size() + " candidates in total");
		var candidate = candidates.iterator().next();
		System.out.println("Candidate:" + candidate);

		long tuningFrequency = (candidate.x * 4000000L) + candidate.y;

		System.out.println("Tuning frequency: " + tuningFrequency);
	}

	private static Set<Point> getDistressCandidates() {
		var candidates = new HashSet<Point>();

		for (var sensor : sensors.entrySet()) {
			var sensorX = sensor.getKey().x;
			var sensorY = sensor.getKey().y;
			var range = sensor.getValue() + 1;

			for (int i = 0; i < range; i++) {
				var x = 0;
				var y = 0;

				// top to right
				x = sensorX + i;
				y = sensorY - range + i;
				if (MIN_X <= x && MIN_Y <= y && x <= MAX_X && y <= MAX_Y && !isNearSensor(x, y)) {
					candidates.add(new Point(x, y));
				}

				// right to bottom
				x = sensorX + range - i;
				y = sensorY + i;
				if (MIN_X <= x && MIN_Y <= y && x <= MAX_X && y <= MAX_Y && !isNearSensor(x, y)) {
					candidates.add(new Point(x, y));
				}

				// bottom to left
				x = sensorX - i;
				y = sensorY + range - i;
				if (MIN_X <= x && MIN_Y <= y && x <= MAX_X && y <= MAX_Y && !isNearSensor(x, y)) {
					candidates.add(new Point(x, y));
				}

				// left to top
				x = sensorX - range + i;
				y = sensorY - i;
				if (MIN_X <= x && MIN_Y <= y && x <= MAX_X && y <= MAX_Y && !isNearSensor(x, y)) {
					candidates.add(new Point(x, y));
				}
			}
			System.out.println("Found " + candidates.size() + " candidates so far");
		}

		return candidates;
	}

	private static void initSensors(List<String> rawInput) {
		for (var line : rawInput) {
			line = line.substring(12);
			var parts = line.split(", y=|: closest beacon is at x=");
			var sensorX = Integer.valueOf(parts[0]);
			var sensorY = Integer.valueOf(parts[1]);
			var beaconX = Integer.valueOf(parts[2]);
			var beaconY = Integer.valueOf(parts[3]);

			int range = Math.abs(beaconX - sensorX) + Math.abs(beaconY - sensorY);
			sensors.put(new Point(sensorX, sensorY), range);
		}
	}

	private static boolean isNearSensor(int x, int y) {
		for (var sensor : sensors.entrySet()) {
			var sensorX = sensor.getKey().x;
			var sensorY = sensor.getKey().y;
			var range = sensor.getValue();

			int distance = Math.abs(x - sensorX) + Math.abs(y - sensorY);
			if (distance <= range) {
				return true;
			}
		}

		return false;
	}
}
