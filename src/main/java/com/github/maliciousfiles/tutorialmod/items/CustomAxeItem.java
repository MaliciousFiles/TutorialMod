package com.github.maliciousfiles.tutorialmod.items;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class CustomAxeItem extends AxeItem {
	public CustomAxeItem(IItemTier tier, float attackDamage, float attackSpeed, Properties builder) {
		super(tier, attackDamage, attackSpeed, builder);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 400;
	}
}
