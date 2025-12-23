package com.example.examplemod.registry;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.gui.AnimalMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.flag.FeatureFlags;

import java.awt.*;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.MENU, ExampleMod.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<AnimalMenu>> ANIMAL_MENU =
            MENUS.register("animal_menu",
                    () -> new MenuType<>(
                            (containerId, inventory) -> new AnimalMenu(containerId, inventory),
                            FeatureFlags.DEFAULT_FLAGS
            ));

}
