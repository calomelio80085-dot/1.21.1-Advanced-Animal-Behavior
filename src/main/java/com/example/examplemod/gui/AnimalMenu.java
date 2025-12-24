package com.example.examplemod.gui;

import com.example.examplemod.registry.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.common.Mod;

import javax.annotation.Nullable;

public class AnimalMenu extends AbstractContainerMenu {

    private final LivingEntity animal;

    public AnimalMenu(int containerId, Inventory inventory) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);
        this.animal = null;
    }

    public AnimalMenu(int containerId, Inventory inventory, LivingEntity animal) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);
        this.animal = animal;
    }

    public AnimalMenu(int containerId, Inventory inventory, FriendlyByteBuf buf) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);

        int entityId = buf.readInt();
        this.animal = (LivingEntity) inventory.player.level().getEntity(entityId);
    }

    public LivingEntity getAnimal() {
        return animal;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return ItemStack.EMPTY;
    }
}