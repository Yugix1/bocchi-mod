package com.yugix.accessories;

import com.yugix.BocchiMod;
import com.yugix.item.BocchiItems;
import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public class BowtieAccessory implements Accessory {
    public static void init(){
        AccessoriesAPI.registerAccessory(BocchiItems.BOWTIE, new BowtieAccessory());
    }

    private final int COOLDOWN = 106; //nijika's birthday is 5/29. 5.29 * 20 ticks = 105.8. dont tell anyone i rounded it down to 106
    private int t = 0;

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!hasEffect(stack)){return;}
        if (t > COOLDOWN){
            reference.entity().heal(.25f);
            t = 0;
        }
        t++;
    }

    @Override
    public void getExtraTooltip(ItemStack stack, List<Component> tooltips, Item.TooltipContext tooltipContext, TooltipFlag tooltipType) {
        tooltips.add(Component.translatable("item.bocchi-mod.nijika_bowtie.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        Accessory.super.getExtraTooltip(stack, tooltips, tooltipContext, tooltipType);
    }

    private boolean hasEffect(ItemStack stack){
        ItemAttributeModifiers component = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        if (component == null) {
            return false;
        }
        for (ItemAttributeModifiers.Entry entry : component.modifiers()) {
            AttributeModifier modifier = entry.modifier();
            if (modifier.id().equals(ResourceLocation.fromNamespaceAndPath(BocchiMod.MOD_ID, "health_regen"))) {
                return true;
            }
        }
        return false;
    }
}
