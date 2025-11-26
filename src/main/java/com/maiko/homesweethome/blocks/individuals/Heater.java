package com.maiko.homesweethome.blocks.individuals;

import com.maiko.homesweethome.blocks.ExternalBlockProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class Heater implements ExternalBlockProvider {

    @Override
    public String getRegistryName() {
        return "heater";
    }

    @Override
    public Block createBlock() {

        return new Block(Block.Properties.copy(Blocks.IRON_BLOCK)
                .strength(3.0f, 3.0f)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
        ) {

            {
                // ✔ Set default state here (constructor block)
                this.registerDefaultState(
                        this.stateDefinition.any().setValue(BlockStateProperties.LIT, false)
                );
            }

            @Override
            protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
                builder.add(BlockStateProperties.LIT);
            }

            @Override
            public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean moving) {
                boolean powered = level.hasNeighborSignal(pos);
                boolean lit = state.getValue(BlockStateProperties.LIT);

                if (powered != lit) {
                    level.setBlock(pos, state.setValue(BlockStateProperties.LIT, powered), 3);
                }
            }
        };
    }
}
