package com.maiko.homesweethome.blocks.individuals;

import com.maiko.homesweethome.menu.PotionShelfMenu;
import com.maiko.homesweethome.registration.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.Container;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class PotionShelfBlockEntity extends BlockEntity implements MenuProvider, Container {

    private NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

    public PotionShelfBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.POTION_SHELF.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Potion Shelf");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new PotionShelfMenu(id, playerInv, this);
    }

    // -------- INVENTORY IMPLEMENTATION --------

    public int getContainerSize() {
        return 3;
    }

    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    public ItemStack removeItem(int slot, int amount) {
        ItemStack stack = ContainerHelper.removeItem(items, slot, amount);
        if (!stack.isEmpty()) setChanged();
        return stack;
    }

    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
        setChanged();
    }

    public boolean stillValid(Player player) {
        return level.getBlockEntity(worldPosition) == this &&
                player.distanceToSqr(
                        worldPosition.getX() + 0.5,
                        worldPosition.getY() + 0.5,
                        worldPosition.getZ() + 0.5) <= 64;
    }

    public void clearContent() {
        items.clear();
    }

    // -------- SAVE / LOAD --------

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, items);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        items = NonNullList.withSize(3, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items);
    }
}
