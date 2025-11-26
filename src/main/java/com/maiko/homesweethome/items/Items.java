package com.maiko.homesweethome.items;

import com.maiko.homesweethome.HomeSweetHome;

import com.maiko.homesweethome.plugins.PluginItemLoader;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Items {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HomeSweetHome.MOD_ID);

    public static final Map<String, RegistryObject<Item>> DYNAMIC_ITEMS = new HashMap<>();

    public static void loadExternalItems() {
        List<ExternalItemProvider> providers = PluginItemLoader.loadInternalProviders();

        for (ExternalItemProvider provider : providers) {
            RegistryObject<Item> reg = ITEMS.register(provider.getRegistryName(), provider::createItem);
            DYNAMIC_ITEMS.put(provider.getRegistryName(), reg);
        }
    }
}
