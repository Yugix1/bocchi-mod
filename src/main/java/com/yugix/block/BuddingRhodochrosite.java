package com.yugix.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingRhodochrosite extends BuddingAmethystBlock {
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingRhodochrosite(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[randomSource.nextInt(DIRECTIONS.length)];
            BlockPos blockPos2 = blockPos.relative(direction);
            BlockState blockState2 = serverLevel.getBlockState(blockPos2);
            Block block = null;
            if (canClusterGrowAtState(blockState2)) {
                block = BocchiBlocks.SMALL_RHODOCHROSITE_BUD;
            } else if (blockState2.is(BocchiBlocks.SMALL_RHODOCHROSITE_BUD) && blockState2.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BocchiBlocks.MEDIUM_RHODOCHROSITE_BUD;
            } else if (blockState2.is(BocchiBlocks.MEDIUM_RHODOCHROSITE_BUD) && blockState2.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BocchiBlocks.LARGE_RHODOCHROSITE_BUD;
            } else if (blockState2.is(BocchiBlocks.LARGE_RHODOCHROSITE_BUD) && blockState2.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BocchiBlocks.RHODOCHROSITE_CLUSTER;
            }

            if (block != null) {
                BlockState blockState3 = (BlockState)((BlockState)block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction)).setValue(AmethystClusterBlock.WATERLOGGED, blockState2.getFluidState().getType() == Fluids.WATER);
                serverLevel.setBlockAndUpdate(blockPos2, blockState3);
            }

        }
    }
}
