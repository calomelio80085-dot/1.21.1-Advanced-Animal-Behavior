package com.example.examplemod;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.gui.AnimalScreen;
import com.example.examplemod.registry.ModMenus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class ExampleModClient {

    public static void onRegisterScreens(RegisterMenuScreensEvent event) {
        event.register(
                    ModMenus.ANIMAL_MENU.get(),
                    AnimalScreen::new
            );
        };
    }

