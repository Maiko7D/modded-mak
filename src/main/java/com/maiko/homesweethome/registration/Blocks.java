package com.maiko.homesweethome.registration;

import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import com.maiko.homesweethome.generated.GeneratedBlockProviders;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
        BlockEntities.register(bus);  // <-- ADD THIS

        System.out.println(">>> Providers loaded: " + GeneratedBlockProviders.PROVIDERS.length);

        for (ExternalBlockProvider provider : GeneratedBlockProviders.PROVIDERS) {

            System.out.println("Registering block: " + provider.getRegistryName());

            // Register block
            RegistryObject<Block> blockReg = BLOCKS.register(provider.getRegistryName(), provider::createBlock);

            // Register BlockItem
            RegistryObject<BlockItem> itemReg = ITEMS.register(provider.getRegistryName(), () ->
                    new BlockItem(blockReg.get(), new Item.Properties())
            );

            DYNAMIC_BLOCK_ITEMS.put(provider.getRegistryName(), itemReg);

            // 🔥 NEW: Only register block entity if provider supplies one
            if (provider.hasBlockEntity()) {

                RegistryObject<BlockEntityType<?>> beType =
                        BlockEntities.BLOCK_ENTITIES.register(provider.getRegistryName(), () ->
                                provider.createBlockEntityType(blockReg)
                        );

                BlockEntities.DYNAMIC_BLOCK_ENTITIES.put(provider.getRegistryName(), beType);

                System.out.println("Registered BlockEntity for: " + provider.getRegistryName());
            }
        }
    }
}
