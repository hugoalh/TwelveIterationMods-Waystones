package net.blay09.mods.waystones.client.gui.widget;

import net.blay09.mods.waystones.Waystones;
import net.blay09.mods.waystones.api.WaystoneVisibility;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Locale;

public class WaystoneVisbilityButton extends Button implements ITooltipProvider {

    private static final ResourceLocation WAYSTONE_GUI_TEXTURES = new ResourceLocation(Waystones.MOD_ID, "textures/gui/menu/waystone.png");

    private WaystoneVisibility visibility;

    public WaystoneVisbilityButton(int x, int y, WaystoneVisibility visibility) {
        super(x, y, 18, 18, Component.empty(), button -> {
        }, Button.DEFAULT_NARRATION);
        this.visibility = visibility;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partial) {
        guiGraphics.blit(WAYSTONE_GUI_TEXTURES, getX(), getY(), 176 + (isHovered ? 18 : 0), 14, 18, 18);
        guiGraphics.blit(WAYSTONE_GUI_TEXTURES, getX(), getY(), 176, 32 + (visibility.ordinal() * 18), 18, 18);
    }

    @Override
    public boolean shouldShowTooltip() {
        return isHovered;
    }

    @Override
    public List<Component> getTooltipComponents() {
        final var visibilityComponent = Component.translatable("tooltip.waystones.visibility." + visibility.name().toLowerCase(Locale.ROOT))
                .withStyle(ChatFormatting.WHITE);
        return List.of(Component.translatable("tooltip.waystones.visibility", visibilityComponent).withStyle(ChatFormatting.YELLOW));
    }

    public WaystoneVisibility getVisibility() {
        return visibility;
    }

    @Override
    public void onPress() {
        visibility = WaystoneVisibility.values()[(visibility.ordinal() + 1) % WaystoneVisibility.values().length];
    }
}
