package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockDecorativeLeaves extends Block {
    private final ForestryLeafType type;

    public BlockDecorativeLeaves(ForestryLeafType type, BlockBehaviour.Properties properties) {
        super(properties.strength(0.2f).sound(SoundType.GRASS).noOcclusion());
        this.type = type;
    }

    public Identifier getSpeciesId() {
        return this.type.getSpeciesId();
    }

    public ForestryLeafType getType() {
        return this.type;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (this.type == ForestryLeafType.WILLOW) {
            return Shapes.empty();
        }
        return super.getCollisionShape(state, level, pos, context);
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
            InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        Vec3 motion = entity.getDeltaMovement();
        entity.setDeltaMovement(motion.multiply(0.4D, 1.0D, 0.4D));
    }
}
