package com.maiko.homesweethome.registration;

import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import com.maiko.homesweethome.blocks.individuals.PotionShelfBlock;
import com.maiko.homesweethome.blocks.individuals.PotionShelfBlockEntity;
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

    public static final RegistryObject<Block> POTION_SHELF =
            BLOCKS.register("potion_shelf", PotionShelfBlock::new);

    public static final RegistryObject<Item> POTION_SHELF_ITEM =
            ITEMS.register("potion_shelf",
                    () -> new BlockItem(POTION_SHELF.get(), new Item.Properties()));

    // Map to store BlockItems for creative tab usage
    public static final Map<String, RegistryObject<BlockItem>> DYNAMIC_BLOCK_ITEMS = new HashMap<>();

    public static void register(IEventBus bus) {

        System.out.println(">>> Providers loaded: " + GeneratedBlockProviders.PROVIDERS.length);

        for (ExternalBlockProvider provider : GeneratedBlockProviders.PROVIDERS) {

            // Register the block
            RegistryObject<Block> blockReg = BLOCKS.register(provider.getRegistryName(), provider::createBlock);

            // Register the BlockItem
            RegistryObject<BlockItem> itemReg = ITEMS.register(provider.getRegistryName(), () ->
                    new BlockItem(blockReg.get(), new Item.Properties())
            );

            DYNAMIC_BLOCK_ITEMS.put(provider.getRegistryName(), itemReg);
        }

        BlockEntities.BLOCK_ENTITIES.register(bus);

        // ✅ Now attach the DeferredRegisters to the mod event bus
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }



}
