package com.yugix.item;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;

public class KessokuBandItem extends TrinketItem {
    public KessokuBandItem(Properties settings) {
        super(settings);
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, ResourceLocation id) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = Multimaps.newMultimap(Maps.newLinkedHashMap(), ArrayList::new);
        AttributeModifier modifier = new AttributeModifier(id,
        1, AttributeModifier.Operation.ADD_VALUE); // add 1 damage to player damage
        modifiers.put(Attributes.ATTACK_DAMAGE, modifier);
        // If the player has access to another offhand glove slot, this will give them an extra one since realistically a zip tie shouldn't take up an entire glove slot
        SlotAttributes.addSlotModifier(modifiers, "offhand/gloves", id, 1, AttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.bocchi-mod.kessoku_band.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
    }
}
