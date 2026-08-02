package com.yugix;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BocchiMod implements ModInitializer {
	public static final String MOD_ID = "bocchi-mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");

		BocchiItems.initialize();
		initItemGroup();
		BocchiAttributes.init();
	}

	public static void initItemGroup(){
		final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(BocchiMod.MOD_ID, "item_group"));
		final CreativeModeTab CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
				.icon(() -> new ItemStack(BocchiItems.BOCCHI_GUITAR))
				.title(Component.translatable("itemGroup.bocchi-mod"))
				.build();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
		ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
			itemGroup.accept(BocchiItems.BOCCHI_GUITAR);
			itemGroup.accept(BocchiItems.KITA_GUITAR);
			itemGroup.accept(BocchiItems.RYO_BASS);
			itemGroup.accept(BocchiItems.KESSOKU_BAND);
			itemGroup.accept(BocchiItems.BOWTIE);
		});
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
