package com.yugix.client;

import com.yugix.block.BocchiBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class BocchiModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(BocchiBlocks.SMALL_RHODOCHROSITE_BUD, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BocchiBlocks.MEDIUM_RHODOCHROSITE_BUD, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BocchiBlocks.LARGE_RHODOCHROSITE_BUD, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BocchiBlocks.RHODOCHROSITE_CLUSTER, RenderType.cutout());
	}
}