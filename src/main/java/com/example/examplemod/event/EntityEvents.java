package com.example.examplemod.event;

import com.example.examplemod.gui.AnimalMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;

import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class EntityEvents {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof LivingEntity animal)) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        player.sendSystemMessage(Component.literal("Interacted with entity"));

        player.openMenu(new SimpleMenuProvider(
                (containerId, inventory, playerEntity) ->
                        new AnimalMenu(containerId, inventory, animal),
                animal.getDisplayName()
                ),
                buf -> {
                    buf.writeInt(animal.getId());
                    event.setCanceled(true);
                    }
                );
    }
}
