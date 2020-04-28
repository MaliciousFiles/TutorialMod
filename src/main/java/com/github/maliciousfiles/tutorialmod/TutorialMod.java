package com.github.maliciousfiles.tutorialmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.maliciousfiles.tutorialmod.client.renders.TutorialRenderRegistry;
import com.github.maliciousfiles.tutorialmod.config.Config;
import com.github.maliciousfiles.tutorialmod.network.Networking;
import com.github.maliciousfiles.tutorialmod.util.ClientProxy;
import com.github.maliciousfiles.tutorialmod.util.ForgeEventHandlers;
import com.github.maliciousfiles.tutorialmod.util.IProxy;
import com.github.maliciousfiles.tutorialmod.util.ServerProxy;
import com.github.maliciousfiles.tutorialmod.world.TutorialWorldType;
import com.github.maliciousfiles.tutorialmod.world.gen.OreGeneration;

import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("tutorialmod")
public class TutorialMod {
	
	public static TutorialMod instance;
	public static final String MODID = "tutorialmod";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final WorldType TUTORIAL_TYPE = new TutorialWorldType();
	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
	
	public TutorialMod() {
		instance = this;
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG/*, "tutorialmod-client.toml"*/);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG/*, "tutorialmod-server.toml"*/);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("tutorialmod-client.toml").toString());
		Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("tutorialmod-server.toml").toString());
		
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		Networking.registerMessages();
		OreGeneration.setupOreGeneration();
		proxy.init();
		LOGGER.info("TutorialMod: setup() method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		TutorialRenderRegistry.registryEntityRenders();
		LOGGER.info("TutorialMod: clientRegistries() method registered.");
	}
}
