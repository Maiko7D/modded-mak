package com.maiko.homesweethome.plugins;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.maiko.homesweethome.blocks.ExternalBlockProvider;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PluginBlockLoader {

    private static final Gson GSON = new Gson();

    public static List<ExternalBlockProvider> loadPluginBlocks() {
        List<ExternalBlockProvider> providers = new ArrayList<>();

        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();

            Enumeration<java.net.URL> resources = cl.getResources("plugins/blocks");

            while (resources.hasMoreElements()) {
                java.net.URL folderURL = resources.nextElement();

                try (var stream = cl.getResourceAsStream("plugins/blocks/blocks.json")) {
                    if (stream == null) continue;

                    JsonObject json = GSON.fromJson(new InputStreamReader(stream), JsonObject.class);

                    json.entrySet().forEach(entry -> {
                        String registryName = entry.getKey();
                        JsonObject data = entry.getValue().getAsJsonObject();

                        providers.add(new ExternalBlockProvider() {
                            @Override
                            public String getRegistryName() {
                                return registryName;
                            }

                            @Override
                            public Block createBlock() {
                                return new Block(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_YELLOW)
                                        .strength(1.0f)
                                );
                            }
                        });
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return providers;
    }
}
