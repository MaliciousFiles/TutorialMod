package com.github.maliciousfiles.tutorialmod.init;

import com.github.maliciousfiles.tutorialmod.TutorialMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class TutorialDimensions {
	
	public static final ResourceLocation TUTORIAL_DIMENSION_ID = new ResourceLocation(TutorialMod.MODID, "tutorial_dimension");
	
	public static ModDimension tutorial_dimension;
	
	public static DimensionType tutorial_dimension_type;
}
