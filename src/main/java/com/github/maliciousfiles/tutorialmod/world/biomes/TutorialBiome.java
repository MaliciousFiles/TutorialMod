package com.github.maliciousfiles.tutorialmod.world.biomes;

import com.github.maliciousfiles.tutorialmod.TutorialModRegistries;
import com.github.maliciousfiles.tutorialmod.init.TutorialBlocks;
import com.github.maliciousfiles.tutorialmod.init.TutorialEntities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TutorialBiome extends Biome {

	public TutorialBiome() {
		super(new Biome.Builder()
		.surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(TutorialBlocks.tutorial_block.getDefaultState(), TutorialBlocks.tutorial_ore.getDefaultState(), Blocks.ACACIA_LOG.getDefaultState())))
		.precipitation(RainType.SNOW)
		.category(Category.PLAINS)
		.downfall(0.3f)
		.depth(0.2f)
		.temperature(0.1f)
		.scale(0.5f)
		.waterColor(0xC3391B)
		.waterFogColor(0xC3391B)
		.parent(null));
		
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addOres(this);
		
		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(TutorialEntities.tutorial_entity, 100, 1, 5));
		
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		
		this.setRegistryName(TutorialModRegistries.location("tutorial_biomie"));
	}

}
