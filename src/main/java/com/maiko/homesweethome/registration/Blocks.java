package com.maiko.homesweethome.registration;

import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import com.maiko.homesweethome.generated.GeneratedBlockProviders;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class Blocks {

    // Block DeferredRegister
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, "homesweethome");

    // Item DeferredRegister for BlockItems
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "homesweethome");

    // Map to store BlockItems for creative tab usage
    public static final Map<String, RegistryObject<BlockItem>> DYNAMIC_BLOCK_ITEMS = new HashMap<>();

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);

        System.out.println(">>> Providers loaded: " + GeneratedBlockProviders.PROVIDERS.length);

        // Loop through all dynamically generated blocks
        for (ExternalBlockProvider provider : GeneratedBlockProviders.PROVIDERS) {

            System.out.println("Registering block: " + provider.getRegistryName());

            // Register the block
            RegistryObject<Block> blockReg = BLOCKS.register(provider.getRegistryName(), provider::createBlock);

            // Register the BlockItem and store the RegistryObject in a variable
            RegistryObject<BlockItem> blockItem = ITEMS.register(provider.getRegistryName(), () ->
                    new BlockItem(blockReg.get(), new Item.Properties())
            );

            // Put the BlockItem into the map for creative tab access
            DYNAMIC_BLOCK_ITEMS.put(provider.getRegistryName(), blockItem);
        }
    }
}
