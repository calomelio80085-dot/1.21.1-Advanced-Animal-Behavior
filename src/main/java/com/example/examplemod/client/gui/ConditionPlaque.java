package com.example.examplemod.client.gui;

import com.example.examplemod.util.AnimalCondition;
import com.example.examplemod.util.NeedSeverity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class ConditionPlaque {

    private final AnimalCondition condition;
    private final NeedSeverity severity;
    private final ResourceLocation icon;
    private final Component tooltip;

    public ConditionPlaque(
            AnimalCondition condition,
            NeedSeverity severity,
            ResourceLocation icon,
            Component tooltip
    ) {
        this.condition = condition;
        this.severity = severity;
        this.icon = icon;
        this.tooltip = tooltip;
    }

    public AnimalCondition getCondition() {
        return condition;
    }

    public NeedSeverity getSeverity() {
        return severity;
    }

    public ResourceLocation getIcon() {
        return icon;
    }

    public Component getTooltip() {
        return tooltip;
    }
}
