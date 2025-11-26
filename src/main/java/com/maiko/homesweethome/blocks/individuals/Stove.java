package com.maiko.homesweethome.blocks.individuals;

import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

public class Stove implements ExternalBlockProvider {
    @Override
    public String getRegistryName() {
        return "stove";
    }

    @Override
    public Block createBlock() {
        return new HorizontalDirectionalBlock(Block.Properties.copy(Blocks.IRON_BLOCK)
                .strength(3.0f, 3.0f)) {
        };
    }
}
