package com.github.maliciousfiles.tutorialmod.blocks.tutorial_block;

import com.github.maliciousfiles.tutorialmod.TutorialMod;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TutorialBlockScreen extends ContainerScreen<TutorialBlockContainer> {

    private static final ResourceLocation GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/tutorial_block_gui.png");
    
    public TutorialBlockScreen(TutorialBlockContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawString(Minecraft.getInstance().fontRenderer, "Flux: " + container.getEnergy(), 10, 37, 0xffffff);
        String s = "Tutorial Block";
        this.font.drawString(s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
        if (((TutorialBlockContainer)this.container).checkInt(1)) {
            int k = ((TutorialBlockContainer)this.container).getInt(1);
            this.blit(i + 103, j + 34 + (14 - k), 176, 14 - k, 9, k);
        }
        //this.blit(i + 103, j + 34, 176, 0, 9, 14);
    }
    
}