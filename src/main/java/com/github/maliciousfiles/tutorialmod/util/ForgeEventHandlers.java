package com.github.maliciousfiles.tutorialmod.util;

import com.github.maliciousfiles.tutorialmod.init.TutorialCommands;
import com.github.maliciousfiles.tutorialmod.init.TutorialDimensions;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class ForgeEventHandlers {
	
	@SubscribeEvent
    public void serverLoad(FMLServerStartingEvent event) {
        TutorialCommands.register(event.getCommandDispatcher());
    }
	
	@SubscribeEvent
	public void onDimensionRegistry(RegisterDimensionsEvent event) {
		TutorialDimensions.tutorial_dimension_type = DimensionManager.registerOrGetDimension(TutorialDimensions.TUTORIAL_DIMENSION_ID, TutorialDimensions.tutorial_dimension, null, true);
	}
}
