package com.yugix.block;

import com.yugix.BocchiMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static net.minecraft.world.level.block.Blocks.AMETHYST_CLUSTER;

public class BocchiBlocks {
    public static void init(){}

    public static final Block RHODOCHROSITE_BLOCK = register(new Block(BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()), "rhodochrosite_block");
    public static final Block BUDDING_RHODOCHROSITE_BLOCK = register(new BuddingRhodochrosite(BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()), "budding_rhodochrosite_block");
    public static final Block SMALL_RHODOCHROSITE_BUD = register(new AmethystClusterBlock(3.0F, 4.0F,BlockBehaviour.Properties.ofLegacyCopy(AMETHYST_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((blockStatex) -> 2)), "small_rhodochrosite_bud");
    public static final Block MEDIUM_RHODOCHROSITE_BUD = register(new AmethystClusterBlock(3.0F, 4.0F,BlockBehaviour.Properties.ofLegacyCopy(AMETHYST_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((blockStatex) -> 6)), "medium_rhodochrosite_bud");
    public static final Block LARGE_RHODOCHROSITE_BUD = register(new AmethystClusterBlock(3.0F, 4.0F,BlockBehaviour.Properties.ofLegacyCopy(AMETHYST_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((blockStatex) -> 10)), "large_rhodochrosite_bud");
    public static final Block RHODOCHROSITE_CLUSTER = register(new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((blockStatex) -> 12).pushReaction(PushReaction.DESTROY)), "rhodochrosite_cluster");

    static Block register(Block block, String id){
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(BocchiMod.MOD_ID, id), new BlockItem(block, new Item.Properties()));
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(BocchiMod.MOD_ID, id), block);
    }
}
