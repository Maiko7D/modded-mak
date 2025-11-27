package com.maiko.homesweethome.registration;

import com.maiko.homesweethome.HomeSweetHome;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class BlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HomeSweetHome.MOD_ID);

    // Store for lookup/use later
    public static final Map<String, RegistryObject<BlockEntityType<?>>> DYNAMIC_BLOCK_ENTITIES = new HashMap<>();

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
