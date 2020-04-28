package com.github.maliciousfiles.tutorialmod.blocks.tutorial_block;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.maliciousfiles.tutorialmod.init.TutorialTileEntities;
import com.github.maliciousfiles.tutorialmod.util.CustomEnergyStorage;
import com.google.common.collect.Maps;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TutorialBlockTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    private int counter;
    private ResourceLocation fuelTagId = new ResourceLocation("tutorialmod", "tutorial_block_fuel");
	private final static int ENERGY_CAPACITY = 1000000;
    private int counterMax;
    private final Map<Item, Integer> energyTimes = Maps.newHashMap();
    private static final int FLUX_PER_TICK = 150;

    public TutorialBlockTileEntity() {
        super(TutorialTileEntities.tutorial_block_tile_entity);
        energyTimes.put(Items.DIAMOND, 800); // 40 seconds
        energyTimes.put(Items.EMERALD, 500); // 25 seconds
        energyTimes.put(Items.DIAMOND_BLOCK, 8000); // 400 seconds
        energyTimes.put(Items.EMERALD_BLOCK, 5000); // 250 seconds
    }

    @Override
    public void tick() {
    	if (getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0) < ENERGY_CAPACITY) {
	        if (counter > 0) {
	            counter--;
	            energy.ifPresent(e -> ((CustomEnergyStorage)e).addEnergy(FLUX_PER_TICK));
	        } else {
	            handler.ifPresent(h -> {
	                ItemStack stack = h.getStackInSlot(0);
	                if (ItemTags.getCollection().getOrCreate(fuelTagId).contains(stack.getItem())) {
	                    extractItem(0, 1, false);
	                    counter = energyTimes.get(stack.getItem());
	                    counterMax = energyTimes.get(stack.getItem());
	                }
	            });
	        }
    	}
    }	
    
    public int getEnergyLeftScaled() {
    	if (counter == 0) {
    		return 0;
    	}
    	return (int) Math.ceil(counter * 14.0 / counterMax);
    }
    
    private void extractItem(int slot, int amount, boolean simulate) {
    	handler.ifPresent(h -> {
    		h.extractItem(slot, amount, simulate);
    	});
    }
    
    public int getCounterMax() {
    	return counterMax;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        CompoundNBT energyTag = tag.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(energyTag));
        counter = tag.getInt("counter");
        super.read(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompoundNBT write(CompoundNBT tag) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("inv", compound);
        });
        energy.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("energy", compound);
        });
        tag.putInt("counter", counter);
        return super.write(tag);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return ItemTags.getCollection().getOrCreate(fuelTagId).contains(stack.getItem());
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!ItemTags.getCollection().getOrCreate(fuelTagId).contains(stack.getItem())) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(ENERGY_CAPACITY, 0);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    
    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new TutorialBlockContainer(i, world, pos, playerInventory, playerEntity);
    }
}