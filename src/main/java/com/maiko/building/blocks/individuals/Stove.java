package com.maiko.building.blocks.individuals;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Stove extends Block {
    public Stove() {
        super(BlockBehaviour.Properties.of()
                .strength(3.0f)
        );
    }
}
