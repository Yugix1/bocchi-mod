package com.yugix.datagen;

import com.yugix.block.BocchiBlocks;
import com.yugix.item.BocchiItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class BocchiModelProvider extends FabricModelProvider {
    public BocchiModelProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(BocchiBlocks.RHODOCHROSITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(BocchiBlocks.BUDDING_RHODOCHROSITE_BLOCK);
        blockStateModelGenerator.createAmethystCluster(BocchiBlocks.SMALL_RHODOCHROSITE_BUD);
        blockStateModelGenerator.createAmethystCluster(BocchiBlocks.MEDIUM_RHODOCHROSITE_BUD);
        blockStateModelGenerator.createAmethystCluster(BocchiBlocks.LARGE_RHODOCHROSITE_BUD);
        blockStateModelGenerator.createAmethystCluster(BocchiBlocks.RHODOCHROSITE_CLUSTER);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(BocchiItems.BOWTIE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BocchiItems.KESSOKU_BAND, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BocchiItems.WORN_KESSOKU_BAND, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BocchiItems.KESSOKUNITE_CRYSTAL, ModelTemplates.FLAT_ITEM);
    }
}