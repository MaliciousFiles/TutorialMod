package com.github.maliciousfiles.tutorialmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OregenConfig {
	public static ForgeConfigSpec.IntValue tutorial_chance;
	public static ForgeConfigSpec.IntValue tutorial_chance_nether;
	public static ForgeConfigSpec.IntValue tutorial_chance_end;
	public static ForgeConfigSpec.BooleanValue generate_overworld;
	public static ForgeConfigSpec.BooleanValue generate_nether;
	public static ForgeConfigSpec.BooleanValue generate_end;
	public static ForgeConfigSpec.IntValue tutorial_ore_vein_length;
	public static ForgeConfigSpec.IntValue tutorial_ore_nether_vein_length;
	public static ForgeConfigSpec.IntValue tutorial_ore_end_vein_length;
	public static ForgeConfigSpec.IntValue tutorial_ore_min;
	public static ForgeConfigSpec.IntValue tutorial_ore_max;
	public static ForgeConfigSpec.IntValue tutorial_ore_nether_min;
	public static ForgeConfigSpec.IntValue tutorial_ore_nether_max;
	public static ForgeConfigSpec.IntValue tutorial_ore_end_min;
	public static ForgeConfigSpec.IntValue tutorial_ore_end_max;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		server.push("Oregen Config");
		tutorial_chance = server
				.comment("Maximum number of veins of Tutorial Ore that can spawn in one chunk in the Overworld")
				.defineInRange("oregen.tutorial_chance", 10, 1, 100000);
		tutorial_chance_nether = server
				.comment("Maximum number of veins of Tutorial Ore that can spawn in one chunk in the Nether")
				.defineInRange("oregen.tutorial_chance_nether", 5, 1, 100000);
		tutorial_chance_end = server
				.comment("Maximum number of veins of Tutorial Ore that can spawn in one chunk in the End")
				.defineInRange("oregen.tutorial_chance_end", 3, 1, 100000);
		generate_overworld = server
				.comment("Decide if you want Tutorial Ore to spawn in the Overworld")
				.define("oregen.generate_overworld", true);
		generate_nether = server
				.comment("Decide if you want Tutorial Ore to spawn in the Nether")
				.define("oregen.generate_nether", true);
		generate_end = server
				.comment("Decide if you want Tutorial Ore to spawn in the End")
				.define("oregen.generate_end", true);
		tutorial_ore_vein_length = server
				.comment("Amount of Tutorial Ore that will spawn in one vein in the Overworld")
				.defineInRange("oregen.tutorial_ore_vein_length", 10, 1, 20);
		tutorial_ore_nether_vein_length = server
				.comment("Amount of Tutorial Ore that will spawn in one vein in the Nether")
				.defineInRange("oregen.tutorial_ore_nether_vein_length", 5, 1, 15);
		tutorial_ore_end_vein_length = server
				.comment("Amount of Tutorial Ore that will spawn in one vein in the End")
				.defineInRange("oregen.tutorial_ore_end_vein_length", 3, 1, 5);
		tutorial_ore_min = server
				.comment("Minimum Y level required for Tutorial Ore to spawn in the Overworld")
				.defineInRange("oregen.tutorial_ore_min", 10, 1, 150);
		tutorial_ore_max = server
				.comment("Maximum Y level required for Tutorial Ore to spawn in the Overworld")
				.defineInRange("oregen.tutorial_ore_max", 100, 151, 300);
		tutorial_ore_nether_min = server
				.comment("Minimum Y level required for Tutorial Ore to spawn in the Nether")
				.defineInRange("oregen.tutorial_ore_nether_min", 10, 1, 150);
		tutorial_ore_nether_max = server
				.comment("Maximum Y level required for Tutorial Ore to spawn in the Nether")
				.defineInRange("oregen.tutorial_ore_nether_max", 100, 151, 300);
		tutorial_ore_end_min = server
				.comment("Minimum Y level required for Tutorial Ore to spawn in the End")
				.defineInRange("oregen.tutorial_ore_end_min", 20, 0, 30);
		tutorial_ore_end_max = server
				.comment("Maximum Y level required for Tutorial Ore to spawn in the End")
				.defineInRange("oregen.tutorial_ore_end_max", 60, 31, 70);
		server.pop();
	}
}
