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

import javax.annotation.Nullable;

public class AnimalMenu extends AbstractContainerMenu {

    private final int animalId;
    @Nullable
    private LivingEntity animal;

    //"Dummy" constructor
    public AnimalMenu(int containerId, Inventory inventory) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);
        this.animalId = -1;
        this.animal = null;
    }

    //Server constructor
    public AnimalMenu(int containerId, Inventory inventory, LivingEntity animal) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);
        this.animal = animal;
        this.animalId = animal.getId();
    }

    //Client constructor
    public AnimalMenu(int containerId, Inventory inventory, FriendlyByteBuf buf) {
        super(ModMenus.ANIMAL_MENU.get(), containerId);
        this.animalId = buf.readInt();
        this.animal = null;

    }

    @Nullable
    public LivingEntity getAnimal(Level level) {
        if (this.animalId < 0) return null;
        Entity entity = level.getEntity(this.animalId);
        return entity instanceof LivingEntity living ? living : null;
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