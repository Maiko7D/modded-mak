package com.maiko.homesweethome;

import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import com.maiko.homesweethome.generated.GeneratedBlockProviders;
import com.maiko.homesweethome.items.Items;
import com.maiko.homesweethome.registration.Blocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HomeSweetHome.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HOME_SWEET_HOME_TAB = CREATIVE_MODE_TABS.register(
            "homesweethome",
            () -> CreativeModeTab.builder()
                    // Icon: use any guaranteed existing item
                    .icon(() -> new ItemStack(net.minecraft.world.item.Items.PUMPKIN_PIE))
                    .title(Component.translatable("creativetab.homesweethome"))
                    .displayItems((parameters, output) -> {

                        // Add dynamically loaded plugin items
                        Items.DYNAMIC_ITEMS.values().forEach(reg -> output.accept(reg.get()));

                        // Add all dynamically generated block items
                        for (ExternalBlockProvider provider : GeneratedBlockProviders.PROVIDERS) {
                            // Get the corresponding BlockItem from the ITEMS DeferredRegister
                            RegistryObject<BlockItem> blockItem = Blocks.DYNAMIC_BLOCK_ITEMS.get(provider.getRegistryName());
                            if (blockItem != null) {
                                output.accept(blockItem.get());
                            }
                        }
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
