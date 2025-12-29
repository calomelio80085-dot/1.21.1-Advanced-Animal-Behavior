package com.example.examplemod.event;

import com.example.examplemod.gui.AnimalMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class EntityEvents {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof LivingEntity living)) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        player.openMenu(
                new SimpleMenuProvider(
                (id, inv, p) ->
                        new AnimalMenu(id, inv, living),
                living.getDisplayName()
        ));
        event.setCanceled(true);
    }
}