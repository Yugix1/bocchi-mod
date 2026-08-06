package com.yugix.accessories;

import com.yugix.item.BocchiItems;
import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.Accessory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class KessokuBandAccessory implements Accessory {
    public static void init(){
        AccessoriesAPI.registerAccessory(BocchiItems.KESSOKU_BAND, new KessokuBandAccessory());
    }
/* for future reference
    @Override
    public void getDynamicModifiers(ItemStack stack, io.wispforest.accessories.api.slot.SlotReference reference, AccessoryAttributeBuilder builder) {
        if(reference.slotName().equals("wrist"))
            builder.addStackable(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(BocchiMod.MOD_ID, "attack_damage"), 1, AttributeModifier.Operation.ADD_VALUE));
    }
*/
    @Override
    public void getExtraTooltip(ItemStack stack, List<Component> tooltips, Item.TooltipContext tooltipContext, TooltipFlag tooltipType) {
        tooltips.add(Component.translatable("item.bocchi-mod.kessoku_band.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        Accessory.super.getExtraTooltip(stack, tooltips, tooltipContext, tooltipType);
    }
}
