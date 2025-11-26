package com.maiko.homesweethome;

import com.maiko.homesweethome.items.Items;
import com.maiko.homesweethome.plugins.PluginItemLoader;
import com.maiko.homesweethome.items.ExternalItemProvider;

import com.maiko.homesweethome.registration.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HomeSweetHome.MOD_ID)
public class HomeSweetHome
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "homesweethome";

    public HomeSweetHome(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        Items.loadExternalItems();

        Blocks.register(modEventBus);
        Items.ITEMS.register(modEventBus);

        ModCreativeModTabs.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Add the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Any common setup code
    }

    private void loadPluginItems()
    {
        // Load item providers inside the mod (items/individuals package)
        List<ExternalItemProvider> providers = PluginItemLoader.loadInternalProviders();

        for (ExternalItemProvider provider : providers)
        {
            Items.DYNAMIC_ITEMS.put(
                    provider.getRegistryName(),
                    Items.ITEMS.register(provider.getRegistryName(), provider::createItem)
            );
        }
    }


    // Add items to creative tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // Example: add all dynamic items to your creative tab
        Items.DYNAMIC_ITEMS.values().forEach(reg -> event.accept(reg));
    }

    // Server starting event
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Server-specific code
    }

    // Client-side setup
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Client-specific setup (renderers, item models)
        }
    }
}
