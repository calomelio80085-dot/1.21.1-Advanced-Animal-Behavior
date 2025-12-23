package com.example.examplemod.registry;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.attachment.SurvivalState;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
                DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ExampleMod.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<SurvivalState>> SURVIVAL =
                ATTACHMENTS.register(
                        "survival",
                        () -> AttachmentType.builder(SurvivalState::new)
                                .build()
                );

    public static void register(IEventBus bus) {
            ATTACHMENTS.register(bus);
        }
    }