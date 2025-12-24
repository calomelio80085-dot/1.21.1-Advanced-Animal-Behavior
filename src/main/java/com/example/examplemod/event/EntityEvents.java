package com.example.examplemod.event;

import com.example.examplemod.attachment.SurvivalState;
import com.example.examplemod.gui.AnimalMenu;
import com.example.examplemod.registry.ModAttachments;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.util.ModTags;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class EntityEvents {

    public static void register() {
        NeoForge.EVENT_BUS.addListener(EntityEvents::onEntityTick);
        NeoForge.EVENT_BUS.addListener(EntityEvents::onEntityInteract);
    }

    private static void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof LivingEntity living)) return;
        if (!living.level().isClientSide) return;
        if (!living.getType().is(ModTags.HAS_SURVIVAL_NEEDS)) return;

        if (living.tickCount % 200 != 0) return;

        SurvivalState state = living.getData(ModAttachments.SURVIVAL);
        state.tick();
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();

        //Only run on server
        if (player.level().isClientSide) return;

        //Only main hand
        if (event.getHand() != InteractionHand.MAIN_HAND) return;

        //Only living entities
        if (!(event.getTarget() instanceof LivingEntity living)) return;

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(
                    new SimpleMenuProvider(
                            (containerId, inventory, p) ->
                                    new AnimalMenu(containerId, inventory, living),
                            Component.literal("Animal")
                    )
            );

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}