package com.github.maliciousfiles.tutorialmod.world;

import com.github.maliciousfiles.tutorialmod.init.TutorialBiomes;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class TutorialWorldType extends WorldType {

	public TutorialWorldType() {
		super("tutorial_type");
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {
		OverworldGenSettings settings = new OverworldGenSettings();
		SingleBiomeProviderSettings single = new SingleBiomeProviderSettings();
		single.setBiome(TutorialBiomes.tutorial_biome);
		return new OverworldChunkGenerator(world, new SingleBiomeProvider(single), settings);
	}
}
