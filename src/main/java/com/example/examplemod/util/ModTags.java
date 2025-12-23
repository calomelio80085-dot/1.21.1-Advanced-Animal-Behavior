package com.example.examplemod.util;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModTags {

    public static final TagKey<EntityType<?>> HAS_SURVIVAL_NEEDS =
            TagKey.create(
                    Registries.ENTITY_TYPE,
                    ResourceLocation.fromNamespaceAndPath(
                            ExampleMod.MODID,
                            "has_survival_needs"
                            )
            );
}

