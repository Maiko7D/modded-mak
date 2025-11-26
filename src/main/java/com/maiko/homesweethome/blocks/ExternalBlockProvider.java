package com.maiko.homesweethome.blocks;

import net.minecraft.world.level.block.Block;

public interface ExternalBlockProvider {
    String getRegistryName();
    Block createBlock();
}
