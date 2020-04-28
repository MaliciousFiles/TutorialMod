package com.github.maliciousfiles.tutorialmod.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.github.maliciousfiles.tutorialmod.TutorialMod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SERVER_CONFIG;
	public static final ForgeConfigSpec CLIENT_CONFIG;
	
	static {
		OregenConfig.init(SERVER_BUILDER, CLIENT_BUILDER);
		
		SERVER_CONFIG = SERVER_BUILDER.build();
		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}
	
	public static void loadConfig(ForgeConfigSpec config, String path) {
		TutorialMod.LOGGER.info("TutorialMod: loading config " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		TutorialMod.LOGGER.info("TutorialMod: built config " + path);
		file.load();
		TutorialMod.LOGGER.info("TutorialMod: loaded config " + path);
		config.setConfig(file);
	}
}
