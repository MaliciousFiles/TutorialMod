package com.github.maliciousfiles.tutorialmod.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {
	void init();
	
	World getClientWorld();
	
	PlayerEntity getClientPlayer();
}
