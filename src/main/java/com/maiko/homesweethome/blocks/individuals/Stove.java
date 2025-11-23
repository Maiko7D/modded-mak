package com.maiko.homesweethome.blocks.individuals;

import com.maiko.homesweethome.blocks.IModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Stove extends Block implements IModBlocks {

    public Stove() {
        // Copy all properties from the existing stone block
        super(BlockBehaviour.Properties.copy(Blocks.STONE));
    }

    @Override
    public String getRegistryName() {
        return "stove"; // registry name used for registration
    }
}
