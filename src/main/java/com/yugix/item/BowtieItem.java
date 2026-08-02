package com.yugix.item;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.yugix.BocchiAttributes;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;

public class BowtieItem extends TrinketItem {
    public BowtieItem(Properties settings) {
        super(settings);
    }

    private final int COOLDOWN = 590; //nijika's birthday is 5/29. 29.5 * 20 ticks = 590
    private int t = 0;

    // this implementation is TERRIBLE but i can't be bothered to fix it
    // the HEALTH_REGENERATION does literally nothing, it's only here because it looks prettier in the tooltip
    // the actual health regen code is handled in tick()
    public Multimap<Holder<Attribute>, AttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, ResourceLocation id) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = Multimaps.newMultimap(Maps.newLinkedHashMap(), ArrayList::new);
        AttributeModifier modifier = new AttributeModifier(id,
        1, AttributeModifier.Operation.ADD_VALUE);
        modifiers.put(BocchiAttributes.HEALTH_REGENERATION, modifier);
        return modifiers;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        t++;
        if (t > COOLDOWN){
            entity.heal(1f);
            t = 0;
        }
        super.tick(stack, slot, entity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.bocchi-mod.nijika_bowtie.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
    }
}
