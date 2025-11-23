package com.maiko.homesweethome.items;

import com.maiko.homesweethome.blocks.Blocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "homesweethome");

    // Register the block as an item
    public static final RegistryObject<Item> STOVE_ITEM =
            ITEMS.register("stove",
                    () -> new BlockItem(Blocks.STOVE.get(),
                            new Item.Properties().tab(CreativeModeTabs.TAB_BUILDING_BLOCKS)));

    // Add more block items here
    // public static final RegistryObject<Item> OVEN_ITEM = ...
}
