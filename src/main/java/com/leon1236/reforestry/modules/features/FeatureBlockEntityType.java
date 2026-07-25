package com.leon1236.reforestry.modules.features;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class FeatureBlockEntityType<T extends BlockEntity> extends ModFeature {
    private final BlockEntityType<T> type;

    public FeatureBlockEntityType(Identifier moduleId, String name, Identifier registryId,
                                   FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        super(moduleId, name);
        BlockEntityType<T> builtType = FabricBlockEntityTypeBuilder.create(factory, blocks).build();
        this.type = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, registryId, builtType);
    }

    public BlockEntityType<T> type() {
        return type;
    }
}
