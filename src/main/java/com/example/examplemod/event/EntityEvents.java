package com.example.examplemod.event;

import com.example.examplemod.gui.AnimalMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber
public class EntityEvents {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide()) return;

        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
        if (!(event.getTarget() instanceof net.minecraft.world.entity.Mob mob)) return;

        //Stop the entity from moving temporarily
        mob.setNoAi(true);
        mob.setDeltaMovement(0, 0, 0);
        mob.setPos(mob.position());

        serverPlayer.openMenu(new SimpleMenuProvider(
        (containerId, inventory, player) -> {
                    AnimalMenu menu = new AnimalMenu(containerId, inventory);
                    menu.setAnimal(mob);
                    return menu;
                },
                Component.literal("Animal")
        ));
    }
}
