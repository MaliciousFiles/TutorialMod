package com.github.maliciousfiles.tutorialmod.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;

public class TutorialBiomes {
	
	public static Biome tutorial_biome;
	
	public static void registerBiomes() {
		registerBiome(tutorial_biome, Type.PLAINS, Type.DRY);
	}
	
	public static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
}
