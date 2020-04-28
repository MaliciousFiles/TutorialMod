package com.github.maliciousfiles.tutorialmod.init;

import com.github.maliciousfiles.tutorialmod.TutorialModRegistries;
import com.github.maliciousfiles.tutorialmod.entities.TutorialEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialEntities {
	public static EntityType<?> tutorial_entity = EntityType.Builder.create(TutorialEntity::new, EntityClassification.CREATURE).build(TutorialModRegistries.MODID + ":tutorial_entity").setRegistryName(TutorialModRegistries.location("tutorial_entity"));
	
	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				TutorialItems.tutorial_entity_egg = registerEntitySpawnEgg(tutorial_entity, 0xFF0B00, 0x0005FF, "tutorial_entity_egg")
		);
	}
	
	public static void registerEntityWorldSpawns() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			registerEntityWorldSpawn(tutorial_entity, biome);
		}
	}
	
	public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name) {
		SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(TutorialModRegistries.TUTORIAL));
		item.setRegistryName(TutorialModRegistries.location(name));
		return item;
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, Biome...biomes) {
		for (Biome biome : biomes) {
			if (biome != null) {
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, 10, 1, 10)); //entity, how often it spawns, min spawn, max spawn
			}
		}
	}
}
