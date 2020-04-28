package com.github.maliciousfiles.tutorialmod.world.gen;

import com.github.maliciousfiles.tutorialmod.config.OregenConfig;
import com.github.maliciousfiles.tutorialmod.init.TutorialBlocks;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
	
	private static final EndOreFeature END_OREGEN = new EndOreFeature();
	
	public static void setupOreGeneration() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (OregenConfig.generate_overworld.get() && biome != Biomes.NETHER && biome != Biomes.THE_END) {
				biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, TutorialBlocks.tutorial_ore.getDefaultState(), OregenConfig.tutorial_chance.get()), Placement.COUNT_RANGE, new CountRangeConfig(OregenConfig.tutorial_ore_vein_length.get(), OregenConfig.tutorial_ore_min.get(), 0, OregenConfig.tutorial_ore_max.get())));
			}
		}
		if (OregenConfig.generate_nether.get()) {
			Biomes.NETHER.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NETHERRACK, TutorialBlocks.tutorial_ore_nether.getDefaultState(), OregenConfig.tutorial_chance_nether.get()), Placement.COUNT_RANGE, new CountRangeConfig(OregenConfig.tutorial_ore_nether_vein_length.get(), OregenConfig.tutorial_ore_nether_min.get(), 0, OregenConfig.tutorial_ore_nether_max.get())));
		}
		
		if (OregenConfig.generate_end.get()) {
			Biomes.THE_END.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(END_OREGEN, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, TutorialBlocks.tutorial_ore_end.getDefaultState(), OregenConfig.tutorial_chance_nether.get()), Placement.COUNT_RANGE, new CountRangeConfig(OregenConfig.tutorial_ore_end_vein_length.get(), OregenConfig.tutorial_ore_end_min.get(), 0, OregenConfig.tutorial_ore_end_max.get())));
		}
	}
}
