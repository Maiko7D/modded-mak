package com.maiko.homesweethome.items.individuals;

import com.maiko.homesweethome.items.ExternalItemProvider;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class Lemon implements ExternalItemProvider {

    private static final FoodProperties LEMON_FOOD = new FoodProperties.Builder()
            .nutrition(2)           // hunger restored
            .saturationMod(0.3F)    // saturation modifier
            .build();

    @Override
    public String getRegistryName() {
        return "lemon";
    }

    @Override
    public Item createItem() {
        return new Item(new Item.Properties().food(LEMON_FOOD));
    }
}
