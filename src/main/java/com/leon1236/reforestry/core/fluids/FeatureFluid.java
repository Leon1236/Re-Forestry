package com.leon1236.reforestry.core.fluids;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.modules.features.ModFeature;

public final class FeatureFluid extends ModFeature {
    private final Fluid source;
    private final Fluid flowing;
    private final BlockForestryFluid block;
    private final BucketItem bucket;
    private final ForestryFluidProperties properties;

    private FeatureFluid(Identifier moduleId, String name, ForestryFluidProperties properties) {
        super(moduleId, name);
        this.properties = properties;

        Identifier fluidId = Identifier.fromNamespaceAndPath(moduleId.getNamespace(), name);
        Identifier flowingId = Identifier.fromNamespaceAndPath(moduleId.getNamespace(), name + "_flowing");
        Identifier blockId = Identifier.fromNamespaceAndPath(moduleId.getNamespace(), "fluid_" + name);
        Identifier bucketId = Identifier.fromNamespaceAndPath(moduleId.getNamespace(), "bucket_" + name);

        BaseFlowingFluid.Holder holder = new BaseFlowingFluid.Holder();
        BaseFlowingFluid.Source sourceFluid = new BaseFlowingFluid.Source(holder);
        BaseFlowingFluid.Flowing flowingFluid = new BaseFlowingFluid.Flowing(holder);
        holder.source = () -> sourceFluid;
        holder.flowing = () -> flowingFluid;

        this.source = Registry.register(BuiltInRegistries.FLUID, fluidId, sourceFluid);
        this.flowing = Registry.register(BuiltInRegistries.FLUID, flowingId, flowingFluid);

        this.block = Registry.register(BuiltInRegistries.BLOCK, blockId,
                new BlockForestryFluid(sourceFluid, properties,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).setId(ResourceKey.create(Registries.BLOCK, blockId))));
        holder.block = () -> this.block;

        this.bucket = Registry.register(BuiltInRegistries.ITEM, bucketId,
                new BucketItem(sourceFluid, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)
                        .setId(ResourceKey.create(Registries.ITEM, bucketId))));
        holder.bucket = () -> this.bucket;
    }

    public static FeatureFluid create(Identifier moduleId, String name, ForestryFluidProperties properties) {
        return new FeatureFluid(moduleId, name, properties);
    }

    public Fluid source() {
        return this.source;
    }

    public Fluid flowing() {
        return this.flowing;
    }

    public BlockForestryFluid block() {
        return this.block;
    }

    public BucketItem bucket() {
        return this.bucket;
    }

    public ForestryFluidProperties properties() {
        return this.properties;
    }
}
