package net.g4ngar_;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class VexoraScreen extends Screen {
    private static final int PURPLE = 0xFF9B5CFF;
    private static final int BG = 0xF20D0B14;
    private static final int PANEL = 0xFF17131F;
    private static final int PANEL2 = 0xFF211A2C;
    private static final int MUTED = 0xFFB8AEC8;
    private String category = "Combat";
    private final List<String> modules = new ArrayList<>();
    private int panelX, panelY, panelW, panelH;

    public VexoraScreen() {
        super(Text.literal("VEXORA Client"));
    }

    @Override
    protected void init() {
        clearChildren();
        panelW = Math.min(650, width - 32);
        panelH = Math.min(390, height - 32);
        panelX = (width - panelW) / 2;
        panelY = (height - panelH) / 2;

        String[] cats = {"Combat", "Movement", "Render", "Player", "World", "Settings"};
        int navX = panelX + 14;
        int navY = panelY + 74;
        for (int i = 0; i < cats.length; i++) {
            final String cat = cats[i];
            addDrawableChild(ButtonWidget.builder(Text.literal(cat), b -> {
                category = cat;
                refreshModules();
            }).dimensions(navX, navY + i * 38, 124, 29).build());
        }
        addDrawableChild(ButtonWidget.builder(Text.literal("ZAMKNIJ"), b -> close())
                .dimensions(panelX + panelW - 104, panelY + panelH - 40, 88, 25).build());
        refreshModules();
    }

    private void refreshModules() {
        modules.clear();
        switch (category) {
            case "Combat" -> modules.addAll(List.of("Combat HUD", "Hit Color", "Target Info"));
            case "Movement" -> modules.addAll(List.of("Keystrokes HUD", "Sprint Indicator", "Motion Display"));
            case "Render" -> modules.addAll(List.of("Potion HUD", "Armor Display", "Coordinates"));
            case "Player" -> modules.addAll(List.of("Item Counter", "Durability HUD", "Status Display"));
            case "World" -> modules.addAll(List.of("World Info", "Time Display", "Direction HUD"));
            default -> modules.addAll(List.of("GUI Scale", "Accent Color", "Menu Animation"));
        }
        // Rebuild only the module buttons, leaving navigation and close button in place.
        clearChildren();
        String[] cats = {"Combat", "Movement", "Render", "Player", "World", "Settings"};
        int navX = panelX + 14;
        int navY = panelY + 74;
        for (int i = 0; i < cats.length; i++) {
            final String cat = cats[i];
            addDrawableChild(ButtonWidget.builder(Text.literal((category.equals(cat) ? "◆ " : "   ") + cat), b -> {
                category = cat;
                refreshModules();
            }).dimensions(navX, navY + i * 38, 124, 29).build());
        }
        int x = panelX + 166;
        int y = panelY + 93;
        for (int i = 0; i < modules.size(); i++) {
            String name = modules.get(i);
            addDrawableChild(ButtonWidget.builder(Text.literal(name + "   •"), b -> {
                // Decorative GUI-only control: intentionally no gameplay action.
            }).dimensions(x + (i % 2) * 218, y + (i / 2) * 54, 202, 38).build());
        }
        addDrawableChild(ButtonWidget.builder(Text.literal("ZAMKNIJ"), b -> close())
                .dimensions(panelX + panelW - 104, panelY + panelH - 40, 88, 25).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        context.fill(0, 0, width, height, 0x9905000B);
        context.fill(panelX, panelY, panelX + panelW, panelY + panelH, BG);
        context.fill(panelX, panelY, panelX + panelW, panelY + 3, PURPLE);
        context.fill(panelX + 12, panelY + 55, panelX + 138, panelY + panelH - 54, PANEL);
        context.fill(panelX + 150, panelY + 55, panelX + panelW - 14, panelY + panelH - 54, PANEL);
        context.fill(panelX + 14, panelY + 14, panelX + 46, panelY + 46, PURPLE);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("V"), panelX + 30, panelY + 24, 0xFFFFFFFF);
        context.drawTextWithShadow(textRenderer, Text.literal("VEXORA"), panelX + 56, panelY + 15, 0xFFFFFFFF);
        context.drawTextWithShadow(textRenderer, Text.literal("CLIENT  /  1.21.4"), panelX + 56, panelY + 31, MUTED);
        context.drawTextWithShadow(textRenderer, Text.literal(category.toUpperCase()), panelX + 166, panelY + 66, PURPLE);
        context.drawTextWithShadow(textRenderer, Text.literal("Cosmetic interface  •  GUI only"), panelX + 166, panelY + 79, MUTED);
        context.drawTextWithShadow(textRenderer, Text.literal("VEXORA  •  visual menu"), panelX + 18, panelY + panelH - 30, MUTED);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
