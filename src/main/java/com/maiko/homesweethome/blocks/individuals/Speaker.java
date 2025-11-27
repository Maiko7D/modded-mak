package com.maiko.homesweethome.blocks.individuals;

import com.maiko.homesweethome.blocks.ExtendDirectionalBlock;
import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Speaker implements ExternalBlockProvider {

    @Override
    public String getRegistryName() {
        return "speaker";
    }

    @Override
    public Block createBlock() {
        return new ExtendDirectionalBlock(Block.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f, 2.0f));
    }
}
