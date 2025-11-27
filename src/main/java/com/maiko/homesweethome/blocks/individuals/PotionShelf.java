package com.maiko.homesweethome.blocks.individuals;

import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import com.maiko.homesweethome.registration.BlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class PotionShelf implements ExternalBlockProvider {

    @Override
    public String getRegistryName() {
        return "potion_shelf";
    }

    @Override
    public Block createBlock() {
        return new Block(Block.Properties.copy(Blocks.OAK_PLANKS));
    }
}

