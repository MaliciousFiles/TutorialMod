package com.github.maliciousfiles.tutorialmod.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

public class CustomPickaxeItem extends PickaxeItem {
	public CustomPickaxeItem(IItemTier tier, int attackDamage, float attackSpeed, Properties builder) {
		super(tier, attackDamage, attackSpeed, builder);
	}
}
