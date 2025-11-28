package com.maiko.homesweethome.registration;

import com.maiko.homesweethome.blocks.individuals.PotionShelfBlockEntity;
import com.maiko.homesweethome.menu.PotionShelfMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Menus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, "homesweethome");

    public static final RegistryObject<MenuType<PotionShelfMenu>> POTION_SHELF =
            MENUS.register("potion_shelf", () ->
                    IForgeMenuType.create((id, inv, buf) -> {
                        BlockPos pos = buf.readBlockPos(); // Reads block position sent from client
                        Player player = inv.player;
                        BlockEntity be = player.getCommandSenderWorld().getBlockEntity(pos);
                        if (be instanceof PotionShelfBlockEntity shelf) {
                            return new PotionShelfMenu(id, inv, shelf);
                        }
                        return null;
                    })
            );
}
