package com.maiko.homesweethome.registration;

import com.maiko.homesweethome.HomeSweetHome;
import com.maiko.homesweethome.blocks.individuals.PotionShelfBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HomeSweetHome.MOD_ID);

    public static final RegistryObject<BlockEntityType<PotionShelfBlockEntity>> POTION_SHELF =
            BLOCK_ENTITIES.register("potion_shelf", () ->
                    BlockEntityType.Builder.of(
                            PotionShelfBlockEntity::new,
                            Blocks.POTION_SHELF.get()   // ← MUST NOT BE NULL
                    ).build(null)
            );
}
