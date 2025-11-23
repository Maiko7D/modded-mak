package com.maiko.homesweethome.blocks;

import com.maiko.homesweethome.blocks.individuals.Stove;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, "homesweethome");

    // Each block as a RegistryObject
    public static final RegistryObject<Block> STOVE =
            BLOCKS.register("stove", Stove::new);

    // Add new blocks here
    // public static final RegistryObject<Block> OVEN = BLOCKS.register("oven", Oven::new);
}
