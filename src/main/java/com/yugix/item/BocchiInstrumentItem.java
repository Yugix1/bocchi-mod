package com.yugix.item;

import immersive_melodies.Sounds;
import immersive_melodies.item.InstrumentItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.joml.Vector3f;

import java.util.List;

public class BocchiInstrumentItem extends InstrumentItem {
    private Component subtitle;
    private Component owner;

    public BocchiInstrumentItem(Properties settings, Sounds.Instrument sound, long sustain, Vector3f offset, Component subtitle, Component owner) {
        super(settings, sound, sustain, offset);
        this.subtitle = subtitle;
        this.owner = owner;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(owner);
        components.add(subtitle);
        super.appendHoverText(stack, context, components, tooltipFlag);
    }
}
