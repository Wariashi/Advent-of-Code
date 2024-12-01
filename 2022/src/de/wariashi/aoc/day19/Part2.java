package de.wariashi.aoc.day19;

import java.util.ArrayList;
import java.util.List;

import de.wariashi.aoc.common.AdventUtil;

public class Part2 {
	public static void main(String[] args) {
		var blueprints = getBlueprints().subList(0, 3);

		for (var blueprint : blueprints) {
			System.out.print("Blueprint " + blueprint.getId() + ": ");
			var geodes = getMostGeodesFor(blueprint, new Inventory(0, 0, 0, 0), new RobotFarm(1, 0, 0, 0), 32);
			System.out.println(geodes + " geodes");
		}

		// runs for approximately 4 hours and then prints this:
		// Blueprint 1: 38 geodes
		// Blueprint 2: 10 geodes
		// Blueprint 3: 18 geodes
	}

	private static int getMostGeodesFor(Blueprint blueprint, Inventory inventory, RobotFarm robots, int minutesLeft) {
		if (minutesLeft == 0) {
			return inventory.geodes();
		}

		// if a geode bot can be built, build one
		if (blueprint.getObsidianForGeode() <= inventory.obsidian() && blueprint.getOreForGeode() <= inventory.ore()) {
			var ore = inventory.ore() + robots.ore() - blueprint.getOreForGeode();
			var clay = inventory.clay() + robots.clay();
			var obsidian = inventory.obsidian() + robots.obsidian() - blueprint.getObsidianForGeode();
			var geodes = inventory.geodes() + robots.geodes();
			return getMostGeodesFor(blueprint, new Inventory(ore, clay, obsidian, geodes),
					new RobotFarm(robots.ore(), robots.clay(), robots.obsidian(), robots.geodes() + 1),
					minutesLeft - 1);
		} else {
			// best result for doing nothing
			var bestResult = getMostGeodesFor(blueprint,
					new Inventory(inventory.ore() + robots.ore(), inventory.clay() + robots.clay(),
							inventory.obsidian() + robots.obsidian(), inventory.geodes() + robots.geodes()),
					robots, minutesLeft - 1);

			// best result for buying ore bot
			if (robots.ore() < blueprint.getMaxOreBotsNeeded() && blueprint.getOreForOre() <= inventory.ore()) {
				var ore = inventory.ore() + robots.ore() - blueprint.getOreForOre();
				var clay = inventory.clay() + robots.clay();
				var obsidian = inventory.obsidian() + robots.obsidian();
				var geodes = inventory.geodes() + robots.geodes();
				var result = getMostGeodesFor(blueprint, new Inventory(ore, clay, obsidian, geodes),
						new RobotFarm(robots.ore() + 1, robots.clay(), robots.obsidian(), robots.geodes()),
						minutesLeft - 1);
				if (bestResult < result) {
					bestResult = result;
				}
			}

			// best result for buying clay bot
			if (robots.clay() < blueprint.getMaxClayBotsNeeded() && blueprint.getOreForClay() <= inventory.ore()) {
				var ore = inventory.ore() + robots.ore() - blueprint.getOreForClay();
				var clay = inventory.clay() + robots.clay();
				var obsidian = inventory.obsidian() + robots.obsidian();
				var geodes = inventory.geodes() + robots.geodes();
				var result = getMostGeodesFor(blueprint, new Inventory(ore, clay, obsidian, geodes),
						new RobotFarm(robots.ore(), robots.clay() + 1, robots.obsidian(), robots.geodes()),
						minutesLeft - 1);
				if (bestResult < result) {
					bestResult = result;
				}
			}

			// best result for buying obsidian bot
			if (robots.obsidian() < blueprint.getMaxObsidianBotsNeeded()
					&& blueprint.getClayForObsidian() <= inventory.clay()
					&& blueprint.getOreForObsidian() <= inventory.ore()) {
				var ore = inventory.ore() + robots.ore() - blueprint.getOreForObsidian();
				var clay = inventory.clay() + robots.clay() - blueprint.getClayForObsidian();
				var obsidian = inventory.obsidian() + robots.obsidian();
				var geodes = inventory.geodes() + robots.geodes();
				var result = getMostGeodesFor(blueprint, new Inventory(ore, clay, obsidian, geodes),
						new RobotFarm(robots.ore(), robots.clay(), robots.obsidian() + 1, robots.geodes()),
						minutesLeft - 1);
				if (bestResult < result) {
					bestResult = result;
				}
			}

			return bestResult;
		}
	}

	private static List<Blueprint> getBlueprints() {
		var blueprints = new ArrayList<Blueprint>();

		var input = AdventUtil.getLines("src/de/wariashi/aoc/day19/input");
		for (var line : input) {
			line = line.substring(10, line.length() - 10);
			var parts = line.split(
					": Each ore robot costs | ore. Each clay robot costs | ore. Each obsidian robot costs | clay. Each geode robot costs ");

			var id = Integer.parseInt(parts[0]);
			var oreForOre = Integer.parseInt(parts[1]);
			var oreForClay = Integer.parseInt(parts[2]);
			var oreForObsidian = Integer.valueOf(parts[3].split(" ore and ")[0]);
			var clayForObsidian = Integer.valueOf(parts[3].split(" ore and ")[1]);
			var oreForGeode = Integer.valueOf(parts[4].split(" ore and ")[0]);
			var obsidianForGeode = Integer.valueOf(parts[4].split(" ore and ")[1]);
			blueprints.add(new Blueprint(id, oreForOre, oreForClay, oreForObsidian, clayForObsidian, oreForGeode,
					obsidianForGeode));
		}

		return blueprints;
	}
}
