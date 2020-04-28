package com.github.maliciousfiles.tutorialmod.blocks.tutorial_block;

import com.github.maliciousfiles.tutorialmod.init.TutorialBlocks;
import com.github.maliciousfiles.tutorialmod.init.TutorialContainers;
import com.github.maliciousfiles.tutorialmod.util.CustomEnergyStorage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;;

public class TutorialBlockContainer extends Container {

    private TutorialBlockTileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;
    private ResourceLocation fuelTagId = new ResourceLocation("tutorialmod", "tutorial_block_fuel");
    private final IIntArray intArray = new IIntArray() {
    	@Override
        public int get(int index) {
    		switch(index) {
    		case 0:
    			return getEnergy();
    		case 1:
    			return getEnergyBarLeft();
    		case 2:
    			return getCountMax();
    		default:
    			return 0;
    		}
        }
        
        @Override
        public void set(int index, int value) {
        	switch(index) {
        	case 0:
        		tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomEnergyStorage)h).setEnergy(value));
        		break;
        	case 1:
        		break;
        	case 2:
        		break;
        	}
        }

		@Override
		public int size() {
			return 3;
		}
    };
    
    public TutorialBlockContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(TutorialContainers.tutorial_block_container, windowId);
        tileEntity = (TutorialBlockTileEntity) world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 80, 33));
        });
        layoutPlayerInventorySlots(8, 84);
        
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getEnergy();
            }
            
            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomEnergyStorage)h).setEnergy(value));
            }
        });
        
        trackIntArray(intArray);
    }
    
    public int getEnergy() {
        return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }
    
    public int getCountMax() {
    	return tileEntity.getCounterMax();
    }
    
    public int getEnergyBarLeft() {
    	return tileEntity.getEnergyLeftScaled();
    }
    
    public int getInt(int index) {
    	return intArray.get(index);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, TutorialBlocks.tutorial_block);
    }


    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }
    
    public boolean checkInt(int index) {
    	return intArray.get(index) > 0;
    }
    
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.mergeItemStack(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else {
                if (ItemTags.getCollection().getOrCreate(fuelTagId).contains(stack.getItem())) {
                    if (!this.mergeItemStack(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) {
                    if (!this.mergeItemStack(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

}