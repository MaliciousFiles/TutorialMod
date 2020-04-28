package com.github.maliciousfiles.tutorialmod.client.renders;

import com.github.maliciousfiles.tutorialmod.entities.TutorialEntity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class TutorialRenderRegistry {
	
	@OnlyIn(Dist.CLIENT)
	public static void registryEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(TutorialEntity.class, new TutorialEntityRender.RenderFactory());
	}
}
