package com.maiko.homesweethome.menus;

import com.maiko.homesweethome.blocks.individuals.PotionShelfBlockEntity;
import com.maiko.homesweethome.registration.Menus;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class PotionShelfMenu extends AbstractContainerMenu {
    private final PotionShelfBlockEntity blockEntity;

    // Server-side constructor
    public PotionShelfMenu(int id, Inventory playerInventory, PotionShelfBlockEntity blockEntity) {
        super(Menus.POTION_SHELF.get(), id);
        this.blockEntity = blockEntity;

        // Add 3 slots for the block
        for (int i = 0; i < 3; i++) {
            this.addSlot(new Slot(blockEntity, i, 62 + i * 18, 17));
        }

        // Add player inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 50 + row * 18));
            }
        }
        // Hotbar
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 108));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}

