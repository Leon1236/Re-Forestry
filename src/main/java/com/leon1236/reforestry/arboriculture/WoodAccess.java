package com.leon1236.reforestry.arboriculture;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;

public enum WoodAccess {
    INSTANCE;

    private final Map<IWoodType, Map<WoodBlockKind, BlockState>> normal = new HashMap<>();
    private final Map<IWoodType, Map<WoodBlockKind, BlockState>> fireproof = new HashMap<>();

    public void register(IWoodType woodType, WoodBlockKind kind, boolean isFireproof, BlockState state) {
        Map<IWoodType, Map<WoodBlockKind, BlockState>> table = isFireproof ? fireproof : normal;
        table.computeIfAbsent(woodType, ignored -> new EnumMap<>(WoodBlockKind.class)).put(kind, state);
    }

    public <T extends Block & IWoodTyped> void register(T block) {
        register(block.getWoodType(), block.getBlockKind(), block.isFireproof(), block.defaultBlockState());
    }

    public BlockState getBlockState(IWoodType woodType, WoodBlockKind kind, boolean isFireproof) {
        Map<WoodBlockKind, BlockState> byKind = (isFireproof ? fireproof : normal).get(woodType);
        if (byKind == null) {
            throw new IllegalArgumentException("No wood blocks registered for wood type: " + woodType.getSerializedName());
        }
        BlockState state = byKind.get(kind);
        if (state == null) {
            throw new IllegalArgumentException("No " + kind.getSerializedName() + " registered for wood type: " + woodType.getSerializedName());
        }
        return state;
    }
}
