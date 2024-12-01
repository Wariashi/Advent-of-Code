package de.wariashi.aoc.day19;

public class Blueprint {
	private final int id;

	// ore costs
	private final int oreForOre;

	// clay costs
	private final int oreForClay;

	// obsidian costs
	private final int oreForObsidian;
	private final int clayForObsidian;

	// geode costs
	private final int oreForGeode;
	private final int obsidianForGeode;

	// limits
	private final int maxOreBotsNeeded;
	private final int maxClayBotsNeeded;
	private final int maxObsidianBotsNeeded;

	public Blueprint(int id, int oreForOre, int oreForClay, int oreForObsidian, int clayForObsidian, int oreForGeode,
			int obsidianForGeode) {
		this.id = id;
		this.oreForOre = oreForOre;
		this.oreForClay = oreForClay;
		this.oreForObsidian = oreForObsidian;
		this.clayForObsidian = clayForObsidian;
		this.oreForGeode = oreForGeode;
		this.obsidianForGeode = obsidianForGeode;

		maxOreBotsNeeded = Math.max(Math.max(oreForOre, oreForClay), Math.max(oreForObsidian, oreForGeode));
		maxClayBotsNeeded = clayForObsidian;
		maxObsidianBotsNeeded = obsidianForGeode;
	}

	public int getClayForObsidian() {
		return clayForObsidian;
	}

	public int getId() {
		return id;
	}

	public int getMaxClayBotsNeeded() {
		return maxClayBotsNeeded;
	}

	public int getMaxObsidianBotsNeeded() {
		return maxObsidianBotsNeeded;
	}

	public int getMaxOreBotsNeeded() {
		return maxOreBotsNeeded;
	}

	public int getObsidianForGeode() {
		return obsidianForGeode;
	}

	public int getOreForClay() {
		return oreForClay;
	}

	public int getOreForGeode() {
		return oreForGeode;
	}

	public int getOreForObsidian() {
		return oreForObsidian;
	}

	public int getOreForOre() {
		return oreForOre;
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();
		builder.append("Blueprint[");

		builder.append("id=");
		builder.append(id);

		builder.append(", oreCost=[");
		builder.append(oreForOre);
		builder.append("ore]");

		builder.append(", clayCost=[");
		builder.append(oreForClay);
		builder.append("ore]");

		builder.append(", obsidianCost=[");
		builder.append(oreForObsidian);
		builder.append("ore, ");
		builder.append(clayForObsidian);
		builder.append("clay]");

		builder.append(", geodeCost=[");
		builder.append(oreForGeode);
		builder.append("ore, ");
		builder.append(obsidianForGeode);
		builder.append("obsidian]");

		builder.append("]");
		return builder.toString();
	}
}
