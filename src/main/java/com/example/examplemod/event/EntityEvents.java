package com.example.examplemod.event;

import com.example.examplemod.gui.AnimalMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.animal.Sheep;
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
        if (!(event.getTarget() instanceof Sheep sheep)) return;

        //Stop the entity from moving temporarily
        sheep.setNoAi(true);
        sheep.setDeltaMovement(0, 0, 0);
        sheep.setPos(sheep.position());

        serverPlayer.openMenu(new SimpleMenuProvider(
        (containerId, inventory, player) -> {
                    AnimalMenu menu = new AnimalMenu(containerId, inventory);
                    menu.setAnimal(sheep);
                    return menu;
                },
                Component.literal("Animal")
        ));
    }
}
