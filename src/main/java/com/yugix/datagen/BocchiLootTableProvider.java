package com.yugix.datagen;

import com.yugix.block.BocchiBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class BocchiLootTableProvider extends FabricBlockLootTableProvider {
    protected BocchiLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(BocchiBlocks.RHODOCHROSITE_BLOCK);
        dropWhenSilkTouch(BocchiBlocks.SMALL_RHODOCHROSITE_BUD);
        dropWhenSilkTouch(BocchiBlocks.MEDIUM_RHODOCHROSITE_BUD);
        dropWhenSilkTouch(BocchiBlocks.LARGE_RHODOCHROSITE_BUD);
        dropSelf(BocchiBlocks.RHODOCHROSITE_CLUSTER);
    }
}
