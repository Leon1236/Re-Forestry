package com.leon1236.reforestry.core.blocks;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.core.tiles.TileForestry;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;

@FunctionalInterface
interface MachineShapeProvider {
	VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context);
}

public class MachineProperties<T extends TileForestry> implements IMachineProperties<T> {
    private final String name;
    private final FeatureBlockEntityType<? extends T> teType;
    @Nullable
    private final BlockEntityTicker<T> clientTicker;
    @Nullable
    private final BlockEntityTicker<T> serverTicker;
    private final MachineShapeProvider shape;
    @Nullable
    private Block block;

    public MachineProperties(FeatureBlockEntityType<? extends T> teType, String name,
                              @Nullable BlockEntityTicker<T> clientTicker, @Nullable BlockEntityTicker<T> serverTicker) {
        this(teType, name, clientTicker, serverTicker, (state, level, pos, context) -> Shapes.block());
    }

    public MachineProperties(FeatureBlockEntityType<? extends T> teType, String name,
                              @Nullable BlockEntityTicker<T> clientTicker, @Nullable BlockEntityTicker<T> serverTicker,
                              MachineShapeProvider shape) {
        this.teType = teType;
        this.name = name;
        this.clientTicker = clientTicker;
        this.serverTicker = serverTicker;
        this.shape = shape;
    }

    @Override
    public void setBlock(Block block) {
        this.block = block;
        ((FabricBlockEntityType) teType.type()).addValidBlock(block);
    }

    @Nullable
    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public BlockEntity createTileEntity(BlockPos pos, BlockState state) {
        return teType.type().create(pos, state);
    }

    @Nullable
    @Override
    public BlockEntityTicker<T> getClientTicker() {
        return clientTicker;
    }

    @Nullable
    @Override
    public BlockEntityTicker<T> getServerTicker() {
        return serverTicker;
    }

    @Override
    public BlockEntityType<? extends T> getTeType() {
        return teType.type();
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shape.getShape(state, level, pos, context);
    }

    public static class Builder<T extends TileForestry> {
        private final FeatureBlockEntityType<? extends T> type;
        private final String name;
        @Nullable
        private BlockEntityTicker<T> clientTicker;
        @Nullable
        private BlockEntityTicker<T> serverTicker;
        private MachineShapeProvider shape = (state, level, pos, context) -> Shapes.block();

        public Builder(FeatureBlockEntityType<? extends T> type, String name) {
            this.type = type;
            this.name = name;
        }

        public Builder<T> setClientTicker(@Nullable BlockEntityTicker<T> clientTicker) {
            this.clientTicker = clientTicker;
            return this;
        }

        public Builder<T> setServerTicker(@Nullable BlockEntityTicker<T> serverTicker) {
            this.serverTicker = serverTicker;
            return this;
        }

        public Builder<T> setShape(MachineShapeProvider shape) {
            this.shape = shape;
            return this;
        }

        public MachineProperties<T> create() {
            return new MachineProperties<>(type, name, clientTicker, serverTicker, shape);
        }
    }
}
