package com.github.maliciousfiles.tutorialmod.util;

import com.github.maliciousfiles.tutorialmod.blocks.tutorial_block.TutorialBlockScreen;
import com.github.maliciousfiles.tutorialmod.init.TutorialContainers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

	@Override
	public void init() {
		ScreenManager.registerFactory(TutorialContainers.tutorial_block_container, TutorialBlockScreen::new);
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}

	@Override
	public PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;	}

}
