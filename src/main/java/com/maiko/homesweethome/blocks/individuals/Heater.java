package com.maiko.homesweethome.blocks.individuals;

import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Heater implements ExternalBlockProvider {

    @Override
    public String getRegistryName() {
        return "heater";
    }

    @Override
    public Block createBlock() {
        return new HeaterBlock();
    }
}
