package com.example.examplemod.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class AnimalScreen extends AbstractContainerScreen<AnimalMenu> {

    public AnimalScreen(AnimalMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    //Helper methods
    private LivingEntity getAnimal() {
        return this.menu.getAnimal();
    }

    private void drawPanel(GuiGraphics g, int x, int y, int w, int h) {
        g.fill(x, y, x + w, y + h, 0xFF373737);
        g.fill(x + 1, y + 1, x + w - 1, y + h - 1, 0xFF8B8B8B);
    }

    private Component hungerText() {
        return Component.literal("Hungry");
    }

    private Component hydrationText() {
        return Component.literal("A little dry");
    }

    private void drawTemperatureScale(GuiGraphics g, int x, int y, float deviation) {
        //Background bar
        g.fill(x, y, x + 80, y + 6, 0xFF555555);

        //Center marker
        int center = x + 40;
        int offset = (int)(deviation * 40);
        int marker = Mth.clamp(center + offset, x, x + 80);

        g.fill(marker - 1, y, marker + 1, y + 6, 0xFFFFFFFF);
    }

    private void drawProductivityBar(GuiGraphics g, int x, int y, float value) {
        g.fill(x, y, x + 100, y + 8, 0xFF404040);
        g.fill(x, y, x + (int)(value * 100), y + 8, 0xFF7FBF7F);
    }

    private void drawPlaqueSlots(GuiGraphics g) {
        int x = leftPos + 30;
        int y = topPos + 130;

        for (int i = 0; i < 6; i++) {
            g.fill(x + i * 18, y, x + i * 18 + 16, y + 16, 0xFF2A2A2A);
        }
    }

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("examplemod", "textures/gui/animal.png");

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
    int x = this.leftPos;
    int y = this.topPos;

        //Main panel
        g.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        //Player inventory
        g.blit(TEXTURE, x, y + imageHeight - 90, 0, 166, imageWidth, 90);

        //Easy beveling (light)
        g.fill(x, y, x + imageWidth, y + 1, 0x33FFFFFF);
        g.fill(x, y, x + 1, y + imageHeight, 0x33FFFFFF);

        //Easy beveling (dark)
        g.fill(x, y + imageHeight - 1,  x + imageWidth, y + imageHeight, 0x55000000);
        g.fill(x + imageWidth - 1, y, x + imageWidth, y + imageHeight, 0x55000000);


    drawPanel(g, x, y, this.imageWidth, this.imageHeight);
    drawPanel(g, x + 8, y + 18, 60, 60);
    drawPanel(g, x + 74, y + 18, 94, 60);
    drawPanel(g, x + 8, y + 82, 160, 16);
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);

        LivingEntity animal = getAnimal();

        Component displayName;
        if (animal != null) {
            displayName = animal.hasCustomName()
                    ? animal.getCustomName()
                    : animal.getType().getDescription();
        } else {
            displayName = Component.literal("Animal");
        }

        if (animal != null) {
            Vector3f translation = new Vector3f(0.0F, 0.0F, 0.0F);
            Quaternionf rotation = new Quaternionf().rotateY((float) Math.PI);
            Quaternionf cameraOrientation = new Quaternionf();

            InventoryScreen.renderEntityInInventory(
                    g,
                    leftPos + 45,
                    topPos + 70,
                    30,
                    translation,
                    rotation,
                    cameraOrientation,
                    animal
            );
        }

        g.drawString(
                this.font,
                displayName,
                this.leftPos + 8,
                this.topPos + 6,
                0x404040,
                false
        );

        g.drawString(
                this.font,
                hungerText(),
                this.leftPos + 78,
                this. topPos + 24,
                0x404040,
                false
        );

        g.drawString(
                this.font,
                hydrationText(),
                this.leftPos + 78,
                this.topPos + 36,
                0x303030,
                false
        );

        drawTemperatureScale(g, leftPos + 30, topPos + 95, 0.0f);
        drawProductivityBar(g, leftPos + 30, topPos + 115, 0.65f);
        drawPlaqueSlots(g);

        this.renderTooltip(g, mouseX, mouseY);
    }

}
