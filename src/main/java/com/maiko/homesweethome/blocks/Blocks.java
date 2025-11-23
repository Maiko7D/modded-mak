package com.maiko.homesweethome.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.reflections.Reflections;

import java.util.Set;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "yourmodid");

    public static void registerAllBlocks() {
        Reflections reflections = new Reflections("com.maiko.homesweethome.blocks.individuals");
        Set<Class<? extends Block>> blockClasses = reflections.getSubTypesOf(Block.class);

        for (Class<? extends Block> blockClass : blockClasses) {
            try {
                Block block = blockClass.getDeclaredConstructor().newInstance();

                if (block instanceof IModBlocks modBlock) {
                    BLOCKS.register(modBlock.getRegistryName(), () -> block);
                } else {
                    System.err.println("Block " + blockClass.getSimpleName() + " is missing registry name!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
