package com.github.maliciousfiles.tutorialmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.maliciousfiles.tutorialmod.blocks.tutorial_block.TutorialBlock;
import com.github.maliciousfiles.tutorialmod.blocks.tutorial_block.TutorialBlockContainer;
import com.github.maliciousfiles.tutorialmod.blocks.tutorial_block.TutorialBlockTileEntity;
import com.github.maliciousfiles.tutorialmod.dimensions.TutorialModDimension;
import com.github.maliciousfiles.tutorialmod.init.TutorialArmorMaterials;
import com.github.maliciousfiles.tutorialmod.init.TutorialBiomes;
import com.github.maliciousfiles.tutorialmod.init.TutorialBlocks;
import com.github.maliciousfiles.tutorialmod.init.TutorialContainers;
import com.github.maliciousfiles.tutorialmod.init.TutorialDimensions;
import com.github.maliciousfiles.tutorialmod.init.TutorialEntities;
import com.github.maliciousfiles.tutorialmod.init.TutorialItems;
import com.github.maliciousfiles.tutorialmod.init.TutorialTileEntities;
import com.github.maliciousfiles.tutorialmod.init.TutorialToolMaterials;
import com.github.maliciousfiles.tutorialmod.items.CustomAxeItem;
import com.github.maliciousfiles.tutorialmod.items.CustomPickaxeItem;
import com.github.maliciousfiles.tutorialmod.world.biomes.TutorialBiome;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class TutorialModRegistries {
	public static final String MODID = "tutorialmod";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ItemGroup TUTORIAL = new TutorialItemGroup();
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				TutorialItems.tutorial_ingot = new Item(new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_ingot")),
				
				TutorialItems.tutorial_nugget = new Item(new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_nugget")),
				
				TutorialItems.tutorial_sword = new SwordItem(TutorialToolMaterials.tutorial, 0, 6.0f, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_sword")),
				TutorialItems.tutorial_pickaxe = new CustomPickaxeItem(TutorialToolMaterials.tutorial, -2, 6.0f, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_pickaxe")),
				TutorialItems.tutorial_axe = new CustomAxeItem(TutorialToolMaterials.tutorial, -1.0f, 6.0f, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_axe")),
				TutorialItems.tutorial_shovel = new ShovelItem(TutorialToolMaterials.tutorial, -1.0f, 6.0f, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_shovel")),
				TutorialItems.tutorial_hoe = new HoeItem(TutorialToolMaterials.tutorial, 6.0f, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_hoe")),
				
				TutorialItems.tutorial_helmet = new ArmorItem(TutorialArmorMaterials.tutorial, EquipmentSlotType.HEAD, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_helmet")),
				TutorialItems.tutorial_chestplate = new ArmorItem(TutorialArmorMaterials.tutorial, EquipmentSlotType.CHEST, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_chestplate")),
				TutorialItems.tutorial_leggings = new ArmorItem(TutorialArmorMaterials.tutorial, EquipmentSlotType.LEGS, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_leggings")),
				TutorialItems.tutorial_boots = new ArmorItem(TutorialArmorMaterials.tutorial, EquipmentSlotType.FEET, new Item.Properties().group(TUTORIAL)).setRegistryName(location("tutorial_boots")),

				TutorialItems.tutorial_block = new BlockItem(TutorialBlocks.tutorial_block, new Item.Properties().group(TUTORIAL)).setRegistryName(TutorialBlocks.tutorial_block.getRegistryName()),
				
				TutorialItems.tutorial_ore = new BlockItem(TutorialBlocks.tutorial_ore, new Item.Properties().group(TUTORIAL)).setRegistryName(TutorialBlocks.tutorial_ore.getRegistryName()),
				TutorialItems.tutorial_ore_nether = new BlockItem(TutorialBlocks.tutorial_ore_nether, new Item.Properties().group(TUTORIAL)).setRegistryName(TutorialBlocks.tutorial_ore_nether.getRegistryName()),
				TutorialItems.tutorial_ore_end = new BlockItem(TutorialBlocks.tutorial_ore_end, new Item.Properties().group(TUTORIAL)).setRegistryName(TutorialBlocks.tutorial_ore_end.getRegistryName())
		);
		
		TutorialEntities.registerEntitySpawnEggs(event);
		
		LOGGER.info("TutorialMod: items registered");
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				TutorialBlocks.tutorial_block = new TutorialBlock(),	
				TutorialBlocks.tutorial_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName(location("tutorial_ore")),
				TutorialBlocks.tutorial_ore_nether = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName(location("tutorial_ore_nether")),
				TutorialBlocks.tutorial_ore_end = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName(location("tutorial_ore_end"))
		);
	}
	
	
	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(
				TutorialEntities.tutorial_entity
		);
		
		TutorialEntities.registerEntityWorldSpawns();
	}
	
	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(
				TutorialBiomes.tutorial_biome = new TutorialBiome()
		);
		
		TutorialBiomes.registerBiomes();
		
		LOGGER.info("TutorialMod: biomes registered");
	}
	
	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
        		TutorialTileEntities.tutorial_block_tile_entity = (TileEntityType<TutorialBlockTileEntity>) TileEntityType.Builder.create(TutorialBlockTileEntity::new, TutorialBlocks.tutorial_block).build(null).setRegistryName(location("tutorial_block_tile_entity"))
        );
    }
	
	@SuppressWarnings("unchecked")
	@SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().registerAll(
        		TutorialContainers.tutorial_block_container = (ContainerType<TutorialBlockContainer>) IForgeContainerType.create((windowId, inv, data) -> {
        			BlockPos pos = data.readBlockPos();
        			return new TutorialBlockContainer(windowId, TutorialMod.proxy.getClientWorld(), pos, inv, TutorialMod.proxy.getClientPlayer());
        		}).setRegistryName(location("tutorial_block_container"))
        );
    }
	
	@SubscribeEvent
	public static void registerDimensions(final RegistryEvent.Register<ModDimension> event) {
		event.getRegistry().registerAll(
				TutorialDimensions.tutorial_dimension = new TutorialModDimension().setRegistryName(TutorialDimensions.TUTORIAL_DIMENSION_ID)
		);
	}
	
	public static ResourceLocation location(String name) {
		return new ResourceLocation(MODID, name);
	}
}
