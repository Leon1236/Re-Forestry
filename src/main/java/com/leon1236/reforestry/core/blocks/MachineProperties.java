package com.leon1236.reforestry.core.blocks;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.core.tiles.TileForestry;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;

public class MachineProperties<T extends TileForestry> implements IMachineProperties<T> {
    private final String name;
    private final FeatureBlockEntityType<? extends T> teType;
    @Nullable
    private final BlockEntityTicker<T> clientTicker;
    @Nullable
    private final BlockEntityTicker<T> serverTicker;
    @Nullable
    private Block block;

    public MachineProperties(FeatureBlockEntityType<? extends T> teType, String name,
                              @Nullable BlockEntityTicker<T> clientTicker, @Nullable BlockEntityTicker<T> serverTicker) {
        this.teType = teType;
        this.name = name;
        this.clientTicker = clientTicker;
        this.serverTicker = serverTicker;
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

    public static class Builder<T extends TileForestry> {
        private final FeatureBlockEntityType<? extends T> type;
        private final String name;
        @Nullable
        private BlockEntityTicker<T> clientTicker;
        @Nullable
        private BlockEntityTicker<T> serverTicker;

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

        public MachineProperties<T> create() {
            return new MachineProperties<>(type, name, clientTicker, serverTicker);
        }
    }
}
