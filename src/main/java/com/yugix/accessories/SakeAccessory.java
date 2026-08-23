package com.yugix.accessories;

import com.yugix.item.BocchiItems;
import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.List;
import java.util.Random;

public class SakeAccessory implements Accessory {
    public static void init(){
        AccessoriesAPI.registerAccessory(BocchiItems.SAKE, new SakeAccessory());
    }
    private boolean flag;
    private final PotionContents potionContents = BocchiItems.SAKE_CONTENTS;

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        LivingEntity entity = reference.entity();

        if (entity.getHealth() <= entity.getMaxHealth() * 0.3 && flag) {
            this.flag = false;
            entity.heal(2.0f);
            entity.addEffect(potionContents.customEffects().get(new Random().nextInt(5)));
        }
        if (entity.getHealth() >= entity.getMaxHealth() * 0.95){
            this.flag = true;
        }
    }

    @Override
    public void getExtraTooltip(ItemStack stack, List<Component> tooltips, Item.TooltipContext tooltipContext, TooltipFlag tooltipType) {
        tooltips.add(Component.translatable("item.bocchi-melodies.onikoroshi_sake.ability").withStyle(ChatFormatting.GOLD));
        tooltips.add(Component.translatable("item.bocchi-melodies.onikoroshi_sake.effect").withStyle(ChatFormatting.GRAY));
        potionContents.addPotionTooltip(tooltips::add, 1.0F, tooltipContext.tickRate());
        tooltips.removeLast(); tooltips.removeLast(); tooltips.removeLast(); tooltips.removeLast();
        tooltips.add(Component.translatable("item.bocchi-melodies.onikoroshi_sake.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        Accessory.super.getExtraTooltip(stack, tooltips, tooltipContext, tooltipType);
    }
}
