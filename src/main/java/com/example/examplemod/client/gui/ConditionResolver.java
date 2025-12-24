package com.example.examplemod.client.gui;

import com.example.examplemod.util.AnimalCondition;
import com.example.examplemod.util.NeedSeverity;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

public final class ConditionResolver {
    List<ConditionPlaque> list = new ArrayList<>();

    private final LivingEntity animal;

    public ConditionResolver(LivingEntity animal) {
        this.animal = animal;
    }

    public boolean isBurning() {
        return animal.isOnFire();
    }

    public float healthFraction() {
        return animal.getHealth() / animal.getMaxHealth();
    }

}
