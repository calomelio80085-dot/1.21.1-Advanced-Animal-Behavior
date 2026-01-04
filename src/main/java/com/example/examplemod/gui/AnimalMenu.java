package com.example.examplemod.gui;

import com.example.examplemod.registry.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
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

    private final Inventory inventory;
    private int animalId = -1;
    @Nullable
    private LivingEntity animal;

    //Client constructor (ModMenus)
    public AnimalMenu(int containerId, Inventory inventory) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);
        this.addDataSlot(new net.minecraft.world.inventory.DataSlot() {
            @Override
            public int get() {
                return animalId;
            }

            @Override
            public void set(int value) {
                animalId = value;
            }

        });
        this.inventory = inventory;

        System.out.println("CLIENT: AnimalMenu received animalId = " + this.animalId);
    }

    public void setAnimal(LivingEntity animal) {
        this.animal = animal;
        this.animalId = animal.getId();
        System.out.println("SERVER: AnimalMenu set animal ID = " + animal.getId());
    }

    @Nullable
    public LivingEntity getAnimal(Level level) {
        System.out.println("CLIENT: getAnimal called with animalId = " + this.animalId);
        if (level == null) return null;
        if (this.animal == null) {
            Entity e = level.getEntity(this.animalId);
            if (e instanceof LivingEntity living) {
                this.animal = living;
                System.out.println("CLIENT: resolved animal " + this.animal);
            }
        }
        return this.animal;
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