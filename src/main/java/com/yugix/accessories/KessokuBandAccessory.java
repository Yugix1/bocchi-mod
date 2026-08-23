package com.yugix.accessories;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yugix.BocchiMod;
import com.yugix.item.BocchiItems;
import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.joml.Quaternionf;

import java.util.List;

public class KessokuBandAccessory implements Accessory {
    public static void init(){
        AccessoriesAPI.registerAccessory(BocchiItems.KESSOKU_BAND, new KessokuBandAccessory());
    }
    @Environment(EnvType.CLIENT)
    public static void clientInit(){
        AccessoriesRendererRegistry.registerRenderer(BocchiItems.KESSOKU_BAND, KessokuBandAccessory.Renderer::new);
    }

    @Override
    public void getDynamicModifiers(ItemStack stack, io.wispforest.accessories.api.slot.SlotReference reference, AccessoryAttributeBuilder builder) {
        if (reference.slotName().equals("wrist"))
            builder.addStackable(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(BocchiMod.MOD_ID, "kessoku_band_extra_attack_damage"), 1, AttributeModifier.Operation.ADD_VALUE));
    }
    @Override
    public void getExtraTooltip(ItemStack stack, List<Component> tooltips, Item.TooltipContext tooltipContext, TooltipFlag tooltipType) {
        tooltips.add(Component.translatable("item.bocchi-melodies.kessoku_band.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        Accessory.super.getExtraTooltip(stack, tooltips, tooltipContext, tooltipType);
    }
    @Environment(EnvType.CLIENT)
    public static class Renderer implements SimpleAccessoryRenderer {

        @Override
        public <M extends LivingEntity> void align(ItemStack stack, SlotReference reference, EntityModel<M> model, PoseStack matrices) {
            if (!(model instanceof HumanoidModel<? extends LivingEntity> humanoidModel)) return;

            AccessoryRenderer.transformToFace(matrices, humanoidModel.rightArm, Side.BOTTOM);
        }

        @Override
        public boolean shouldRenderInFirstPerson(HumanoidArm arm, ItemStack stack, SlotReference reference) {
            return true;
        }

        @Override
        public <M extends LivingEntity> void render(ItemStack stack, SlotReference reference, PoseStack matrices, EntityModel<M> model, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            stack = BocchiItems.WORN_KESSOKU_BAND.getDefaultInstance();
            align(stack, reference, model, matrices);
            matrices.scale(1.5f,1.3f,1.5f);
            matrices.translate(0,.3,0);
            matrices.rotateAround(new Quaternionf(Math.sqrt(0.5), 0, 0, Math.sqrt(0.5)), 0, 0, 0);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, matrices, multiBufferSource, reference.entity().level(), 0);
        }
    }
}
