package com.yugix;


import com.yugix.item.BocchiInstrumentItem;
import com.yugix.item.BowtieItem;
import com.yugix.item.KessokuBandItem;
import immersive_melodies.Sounds;
import immersive_melodies.client.animation.ItemAnimators;
import immersive_melodies.client.animation.animators.Animator;
import immersive_melodies.client.animation.animators.LuteAnimator;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import static immersive_melodies.Items.*;


public class BocchiItems {
    public static void initialize(){
    }
    public static final Item BOCCHI_GUITAR = registerInstrument(BocchiMod.MOD_ID, "bocchi_guitar", new LuteAnimator(), 300, new Vector3f(0.0f, 0.0f, 0.5f),
            Component.translatable("item.bocchi-mod.bocchi_guitar.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY), Component.translatable("item.bocchi-mod.bocchi_guitar.owner").withStyle(ChatFormatting.GRAY));
    public static final Item KITA_GUITAR = registerInstrument(BocchiMod.MOD_ID, "kita_guitar", new LuteAnimator(), 300, new Vector3f(0.0f, 0.0f, 0.5f),
            Component.translatable("item.bocchi-mod.kita_guitar.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY), Component.translatable("item.bocchi-mod.kita_guitar.owner").withStyle(ChatFormatting.GRAY));
    public static final Item RYO_BASS = registerInstrument(BocchiMod.MOD_ID, "ryo_bass", new LuteAnimator(), 300, new Vector3f(0.0f, 0.0f, 0.5f),
            Component.translatable("item.bocchi-mod.ryo_bass.subtitle").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY), Component.translatable("item.bocchi-mod.ryo_bass.owner").withStyle(ChatFormatting.GRAY));

    public static final Item KESSOKU_BAND = register(new KessokuBandItem(new Item.Properties().stacksTo(1)), "kessoku_band");
    public static final Item BOWTIE = register(new BowtieItem(new Item.Properties().stacksTo(1)), "nijika_bowtie");

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


