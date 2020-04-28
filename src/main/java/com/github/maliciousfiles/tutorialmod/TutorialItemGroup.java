package com.github.maliciousfiles.tutorialmod;

import com.github.maliciousfiles.tutorialmod.init.TutorialItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TutorialItemGroup extends ItemGroup {

	public TutorialItemGroup() {
		super("tutorial_mod");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(TutorialItems.tutorial_ingot);
	}
	
}
