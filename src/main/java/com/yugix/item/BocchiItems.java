package com.yugix.item;


import com.yugix.BocchiMod;
import immersive_melodies.Sounds;
import immersive_melodies.client.animation.ItemAnimators;
import immersive_melodies.client.animation.animators.Animator;
import immersive_melodies.client.animation.animators.LuteAnimator;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;

import static immersive_melodies.Items.*;


public class BocchiItems {
    public static void initialize(){
    }
    public static final Item KESSOKUNITE_CRYSTAL = register(new Item(new Item.Properties()), "kessokunite_crystal");

    public static final Item BOCCHI_GUITAR = registerInstrument(BocchiMod.MOD_ID, "bocchi_guitar", new LuteAnimator(), 300, new Vector3f(0.0f, 0.0f, 0.5f),
            Component.translatable("item.bocchi-mod.bocchi_guitar.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY), Component.translatable("item.bocchi-mod.bocchi_guitar.owner").withStyle(ChatFormatting.GRAY));
    public static final Item KITA_GUITAR = registerInstrument(BocchiMod.MOD_ID, "kita_guitar", new LuteAnimator(), 300, new Vector3f(0.0f, 0.0f, 0.5f),
            Component.translatable("item.bocchi-mod.kita_guitar.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY), Component.translatable("item.bocchi-mod.kita_guitar.owner").withStyle(ChatFormatting.GRAY));
    public static final Item RYO_BASS = registerInstrument(BocchiMod.MOD_ID, "ryo_bass", new LuteAnimator(), 300, new Vector3f(0.0f, 0.0f, 0.5f),
            Component.translatable("item.bocchi-mod.ryo_bass.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY), Component.translatable("item.bocchi-mod.ryo_bass.owner").withStyle(ChatFormatting.GRAY));

    public static final Item KESSOKU_BAND = register(new Item(new Item.Properties().stacksTo(1)), "kessoku_band");
    public static final Item WORN_KESSOKU_BAND = register(new Item(new Item.Properties()), "worn_kessoku_band");
    public static final Item BOWTIE = register(new Item(new Item.Properties().stacksTo(1)), "nijika_bowtie");
    public static final PotionContents SAKE_CONTENTS = new PotionContents(Optional.empty(), Optional.empty(), List.of(
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0),
            new MobEffectInstance(MobEffects.HUNGER, 600, 0),
            new MobEffectInstance(MobEffects.WEAKNESS, 600, 0),
            new MobEffectInstance(MobEffects.POISON, 600, 0)));
    public static final Item SAKE = register(new Item(new Item.Properties().stacksTo(1).component(DataComponents.POTION_CONTENTS, SAKE_CONTENTS)), "onikoroshi_sake");


    static Item register(Item item, String id){
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(BocchiMod.MOD_ID, id), item);
    }
    static Item registerInstrument(@NotNull String namespace, @NotNull String name, Animator animator, long sustain, Vector3f offset, Component subtitle, Component owner) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(namespace, name);
        ItemAnimators.register(location, animator);
        Sounds.Instrument instrument = new Sounds.Instrument(namespace, name);
        Item item = new BocchiInstrumentItem(baseProps(), instrument, sustain, offset, subtitle, owner);
        item = Registry.register(BuiltInRegistries.ITEM, location, item);
        customInventoryModels.add(location);
        return item;
    }
}


