package com.maiko.building.blocks;

import com.maiko.building.HomeSweetHome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import org.reflections.Reflections;

import java.util.Set;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HomeSweetHome.MOD_ID);

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HomeSweetHome.MOD_ID);


    public static void register(IEventBus bus) {
        autoRegisterBlocks();
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

    private static void autoRegisterBlocks() {

        // Scan the block package:
        Reflections reflections = new Reflections("com.maiko.building.blocks.individuals");
        Set<Class<? extends Block>> classes = reflections.getSubTypesOf(Block.class);

        for (Class<? extends Block> clazz : classes) {
            try {

                // Generate registry name from class name
                String id = clazz.getSimpleName().toLowerCase();

                // Create an instance of the block
                Block blockInstance = clazz.getDeclaredConstructor().newInstance();

                // Register block
                RegistryObject<Block> reg = BLOCKS.register(id, () -> blockInstance);

                // Register block item
                ITEMS.register(id, () -> new BlockItem(reg.get(), new Item.Properties()));

            } catch (Exception e) {
                throw new RuntimeException("Failed to auto-register block: " + clazz.getName(), e);
            }
        }
    }
}
