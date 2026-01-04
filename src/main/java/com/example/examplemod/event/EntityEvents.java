package com.example.examplemod.event;

import com.example.examplemod.gui.AnimalMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber
public class EntityEvents {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide()) return;

        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
        if (!(event.getTarget() instanceof LivingEntity livingEntity)) return;

        System.out.println("SERVER: Opening AnimalMenu for " + livingEntity);

        serverPlayer.openMenu(new SimpleMenuProvider(
        (containerId, inventory, player) -> {
                    AnimalMenu menu = new AnimalMenu(containerId, inventory);
                    menu.setAnimal(livingEntity);
                    return menu;
                },
                Component.literal("Animal")
        ));
    }
}
