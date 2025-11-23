package com.maiko.building;

import com.maiko.building.blocks.ModBlocks;
import com.maiko.building.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTab {
    public static final DeferredRegister<net.minecraft.world.item.CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HomeSweetHome.MOD_ID);

    public static final RegistryObject<net.minecraft.world.item.CreativeModeTab> HOMESWEETHOME_TAB = CREATIVE_MODE_TABS.register("home_sweet_home_tab",
            () -> net.minecraft.world.item.CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PAN.get()))
                    .title(Component.translatable("creativetab.home_sweet_home_tab"))
                    .displayItems((pParamenters, pOutput) -> {

                        })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
