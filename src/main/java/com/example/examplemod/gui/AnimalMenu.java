package com.example.examplemod.gui;

import com.example.examplemod.registry.ModMenus;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.Mod;

import javax.annotation.Nullable;

public class AnimalMenu extends AbstractContainerMenu {

    private final int animalId;
    private LivingEntity animal;

    public AnimalMenu(int containerId, Inventory inventory) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);
        this.animalId = -1;
    }

    public AnimalMenu(int containerId, Inventory inventory, LivingEntity animal) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);
        this.animal = animal;
        this.animalId = ((Entity) animal).getId();
    }

    public void resolveAnimal(Level level) {
        if (animal == null && animalId != -1) {
            Entity e = level.getEntity(animalId);
            if (e instanceof LivingEntity living) {
                animal = living;
                System.out.println("CLIENT: resolved animal " + animal);
            }
        }
    }

    @Nullable
    public LivingEntity getAnimal() {
        return animal;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}