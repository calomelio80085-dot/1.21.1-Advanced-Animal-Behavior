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
import java.util.function.Supplier;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, ExampleMod.MODID);

    //ignore the "lambda can be replaced" as the methods are ambiguous and we need to call the right one directly
    public static final DeferredHolder<MenuType<?>, MenuType<AnimalMenu>> ANIMAL_MENU =
            MENUS.register("animal_menu",
                    () -> new MenuType<>(AnimalMenu::new, FeatureFlags.DEFAULT_FLAGS)
            );
}

