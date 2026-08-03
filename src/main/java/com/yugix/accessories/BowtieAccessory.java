package com.yugix.accessories;

import com.yugix.item.BocchiItems;
import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BowtieAccessory implements Accessory {
    public static void init(){
        AccessoriesAPI.registerAccessory(BocchiItems.BOWTIE, new BowtieAccessory());
    }

    private final int COOLDOWN = 590; //nijika's birthday is 5/29. 29.5 * 20 ticks = 590
    private int t = 0;

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (t > COOLDOWN){
            reference.entity().heal(1f);
            t = 0;
        }
        t++;
    }

    @Override
    public void getExtraTooltip(ItemStack stack, List<Component> tooltips, Item.TooltipContext tooltipContext, TooltipFlag tooltipType) {
        tooltips.add(Component.translatable("item.modifiers.any").withStyle(ChatFormatting.GRAY));
        tooltips.add(Component.translatable("item.bocchi-mod.nijika_bowtie.effect").withStyle(ChatFormatting.BLUE));
        tooltips.add(Component.translatable("item.bocchi-mod.nijika_bowtie.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        Accessory.super.getExtraTooltip(stack, tooltips, tooltipContext, tooltipType);
    }
}
