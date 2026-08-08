package com.yugix.accessories;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yugix.BocchiMod;
import com.yugix.item.BocchiItems;
import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.client.Side;
import io.wispforest.accessories.api.client.SimpleAccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public class BowtieAccessory implements Accessory {
    public static void init(){
        AccessoriesAPI.registerAccessory(BocchiItems.BOWTIE, new BowtieAccessory());
    }
    @Environment(EnvType.CLIENT)
    public static void clientInit(){
        AccessoriesRendererRegistry.registerRenderer(BocchiItems.BOWTIE, Renderer::new);
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

    @Environment(EnvType.CLIENT)
    public static class Renderer implements SimpleAccessoryRenderer {

        @Override
        public <M extends LivingEntity> void align(ItemStack stack, SlotReference reference, EntityModel<M> model, PoseStack matrices) {
            if (!(model instanceof HumanoidModel<? extends LivingEntity> humanoidModel)) return;

            AccessoryRenderer.transformToFace(matrices, humanoidModel.body, Side.FRONT);
        }

        @Override
        public <M extends LivingEntity> void render(ItemStack stack, SlotReference reference, PoseStack matrices, EntityModel<M> model, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            align(stack, reference, model, matrices);
            matrices.translate(0,.35f,0.02f);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, matrices, multiBufferSource, reference.entity().level(), 0);
        }
    }
}
