package com.maiko.homesweethome.items;

import net.minecraft.world.item.Item;

public interface ExternalItemProvider {
    /**
     * Must return a unique registry name for the item, e.g. "ruby_sword"
     */
    String getRegistryName();

    /**
     * Create and return the actual Item instance.
     */
    Item createItem();
}
