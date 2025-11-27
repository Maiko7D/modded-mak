package com.maiko.homesweethome.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public interface ExternalBlockProvider {

    String getRegistryName();

    Block createBlock();

    default boolean hasBlockEntity() {
        return false;
    }

    @Nullable
    default BlockEntityType<?> createBlockEntityType(RegistryObject<Block> block) {
        return null;
    }
}

