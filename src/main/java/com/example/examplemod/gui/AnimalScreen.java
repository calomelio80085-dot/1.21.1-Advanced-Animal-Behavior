package com.example.examplemod.gui;

import com.example.examplemod.ExampleMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import com.example.examplemod.gui.AnimalMenu;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;

public class AnimalScreen extends AbstractContainerScreen<AnimalMenu> {

    //Get GUI flat background "texture"
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "textures/gui/animalgui.png");

    //Plaque constants
    private static final int PLAQUE_COUNT = 6;
    private static final int PLAQUE_SIZE = 16;
    private static final int PLAQUE_SPACING = 18;

    @Nullable
    private LivingEntity renderClone;

    public AnimalScreen(AnimalMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    //Find the particular animal for the GUI entity (plus failsafe)
    private void prepareRenderClone() {
        if (renderClone != null) return;

        LivingEntity animal = menu.getAnimal(this.minecraft.level);
        if (animal != null) {
            renderClone = createClone(animal);
        }
    }

    //Create the GUI entity and set everything to 0
    private LivingEntity createClone(LivingEntity original) {
        LivingEntity clone = (LivingEntity) original.getType().create(this.minecraft.level);
        if (clone != null) {
            clone.moveTo(0, 0, 0);
            clone.setYRot(0);
            clone.setXRot(0);
            clone.setYBodyRot(0);
            clone.setYHeadRot(0);
        }
        return clone;
    }

    @Override
    public void removed() {
        super.removed();
        renderClone = null;
    }

    //Render entity helpers
    private void applyMouseRotation(LivingEntity entity, float lookYaw, float lookPitch) {
        entity.setYRot(lookYaw);
        entity.setXRot(-lookPitch * 0.2F);
        entity.yHeadRot = lookYaw;
        entity.yBodyRot = lookYaw;
        entity.yHeadRotO = lookYaw;
        entity.yBodyRotO = lookYaw;
    }

    private Quaternionf getBaseRotation(float lookPitch) {
        return new Quaternionf()
                .rotateX((float) Math.PI)
                .rotateX(-lookPitch * 0.02F);
    }

    private void drawPlaqueSlots(GuiGraphics g) {
        int x = leftPos + 30;
        int y = topPos + 130;

        for (int i = 0; i < PLAQUE_COUNT; i++) {
            g.fill(x + i * PLAQUE_SPACING, y, x + i * PLAQUE_SPACING + PLAQUE_SIZE, y + PLAQUE_SIZE, 0xFF2A2A2A);
        }
    }

    private void drawPanel(GuiGraphics g, int x, int y, int w, int h) {
        g.fill(x, y, x + w, y + h, 0xFF373737);
        g.fill(x + 1, y + 1, x + w - 1, y + h - 1, 0xFF8B8B8B);
    }

    private void drawTemperatureScale(GuiGraphics g, int x, int y, float deviation) {
        g.fill(x, y, x + 80, y + 6, 0xFF555555);
        int center = x + 40;
        int offset = (int) (deviation * 40);
        int marker = Mth.clamp(center + offset, x, x + 80);
        g.fill(marker - 1, y, marker + 1, y + 6, 0xFFFFFFFF);
    }

    private void drawProductivityBar(GuiGraphics g, int x, int y, float value) {
        g.fill(x, y, x + 100, y + 8, 0xFF404040);
        g.fill(x, y, x + (int) (value * 100), y + 8, 0xFF7FBF7F);
    }

    private Component hungerText() {
        return Component.literal("Hungry");
    }

    private Component hydrationText() {
        return Component.literal("A little dry");
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {

        //Draw GUI texture where?
        graphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

        prepareRenderClone();
        if (renderClone != null) {
            int entityX = leftPos + 35;
            int entityY = topPos + 65;

            //Calculate rotations based on mouse
            float lookYaw = (entityX - mouseX) * 0.15F;
            float lookPitch = (entityY - mouseY) * 0.15F;

            applyMouseRotation(renderClone, lookYaw, lookPitch);

            InventoryScreen.renderEntityInInventory(
                    graphics,
                    entityX,
                    entityY,
                    30,
                    new Vector3f(0, 0, 0),
                    getBaseRotation(lookPitch),
                    new Quaternionf().rotateX(-lookPitch * 0.02F).rotateX((float) Math.PI),
                    renderClone
            );

        }

        // Draw GUI panels and overlays
        drawPanel(graphics, leftPos, topPos, imageWidth, imageHeight);
        drawPanel(graphics, leftPos + 8, topPos + 18, 60, 60);
        drawPanel(graphics, leftPos + 74, topPos + 18, 94, 60);
        drawPanel(graphics, leftPos + 8, topPos + 82, 160, 16);

        drawTemperatureScale(graphics, leftPos + 30, topPos + 95, 0.0f);
        drawProductivityBar(graphics, leftPos + 30, topPos + 115, 0.65f);
        drawPlaqueSlots(graphics);

    }
}
