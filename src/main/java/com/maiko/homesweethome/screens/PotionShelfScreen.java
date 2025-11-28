package com.maiko.homesweethome.screens;

import com.maiko.homesweethome.menu.PotionShelfMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PotionShelfScreen extends AbstractContainerScreen<PotionShelfMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("homesweethome", "textures/gui/potion_shelf.png");

    public PotionShelfScreen(PotionShelfMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 133;
    }

    @Override
    protected void renderBg(GuiGraphics gfx, float partialTicks, int mouseX, int mouseY) {
        gfx.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}
