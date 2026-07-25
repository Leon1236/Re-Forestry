package com.leon1236.reforestry.apiculture.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.redstone.Orientation;

import com.leon1236.reforestry.apiculture.multiblock.IAlvearyControllerInternal;
import com.leon1236.reforestry.apiculture.multiblock.TileAlveary;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyFan;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyHeater;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyHygroregulator;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyPlain;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearySieve;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyStabiliser;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearySwarmer;
import com.leon1236.reforestry.core.blocks.BlockStructure;
import com.leon1236.reforestry.core.tiles.IActivatable;
import com.leon1236.reforestry.core.tiles.TileUtil;

public class BlockAlveary extends BlockStructure {
    public static final EnumProperty<State> STATE = EnumProperty.create("state", State.class);
    public static final EnumProperty<AlvearyPlainType> PLAIN_TYPE = EnumProperty.create("type", AlvearyPlainType.class);

    public enum State implements StringRepresentable {
        ON, OFF;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ENGLISH);
        }
    }

    public enum AlvearyPlainType implements StringRepresentable {
        NORMAL, ENTRANCE, ENTRANCE_LEFT, ENTRANCE_RIGHT;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ENGLISH);
        }
    }

    private final BlockAlvearyType type;
    private final MapCodec<BlockAlveary> codec;

    public BlockAlveary(BlockAlvearyType type, BlockBehaviour.Properties properties) {
        super(properties);
        this.type = type;
        this.codec = simpleCodec(props -> new BlockAlveary(type, props));
        registerDefaultState(getStateDefinition().any()
                .setValue(PLAIN_TYPE, AlvearyPlainType.NORMAL)
                .setValue(STATE, State.OFF));
    }

    @Override
    protected MapCodec<? extends BlockAlveary> codec() {
        return codec;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PLAIN_TYPE, STATE);
    }

    public BlockAlvearyType getType() {
        return type;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return switch (type) {
            case SWARMER -> new TileAlvearySwarmer(pos, state);
            case FAN -> new TileAlvearyFan(pos, state);
            case HEATER -> new TileAlvearyHeater(pos, state);
            case HYGRO -> new TileAlvearyHygroregulator(pos, state);
            case STABILISER -> new TileAlvearyStabiliser(pos, state);
            case SIEVE -> new TileAlvearySieve(pos, state);
            case PLAIN -> new TileAlvearyPlain(pos, state);
        };
    }

    public BlockState getNewState(TileAlveary tile) {
        BlockState state = defaultBlockState();

        if (tile instanceof IActivatable activatable) {
            return state.setValue(STATE, activatable.isActive() ? State.ON : State.OFF);
        }
        if (type != BlockAlvearyType.PLAIN) {
            return state;
        }

        Level level = tile.getLevel();
        if (level == null || !tile.getMultiblockLogic().getController().isAssembled()) {
            return state;
        }

        BlockPos pos = tile.getBlockPos();
        if (!level.getBlockState(pos.above()).is(BlockTags.WOODEN_SLABS)) {
            return state;
        }

        List<Direction> blocksTouching = getBlocksTouching(level, pos);
        return switch (blocksTouching.size()) {
            case 3 -> state.setValue(PLAIN_TYPE, AlvearyPlainType.ENTRANCE);
            case 2 -> {
                boolean left = blocksTouching.contains(Direction.SOUTH) && blocksTouching.contains(Direction.EAST)
                        || blocksTouching.contains(Direction.NORTH) && blocksTouching.contains(Direction.WEST);
                yield state.setValue(PLAIN_TYPE, left ? AlvearyPlainType.ENTRANCE_LEFT : AlvearyPlainType.ENTRANCE_RIGHT);
            }
            default -> state;
        };
    }

    private static List<Direction> getBlocksTouching(BlockGetter level, BlockPos pos) {
        List<Direction> touching = new ArrayList<>();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (level.getBlockState(pos.relative(direction)).getBlock() instanceof BlockAlveary) {
                touching.add(direction);
            }
        }
        return touching;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            @Nullable Orientation orientation, boolean movedByPiston) {
        TileUtil.actOnTile(level, pos, TileAlveary.class, tile -> {
            IAlvearyControllerInternal alveary = tile.getMultiblockLogic().getController();
            alveary.reassemble();
        });
    }
}
