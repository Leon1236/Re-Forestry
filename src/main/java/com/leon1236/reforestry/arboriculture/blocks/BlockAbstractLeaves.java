package com.leon1236.reforestry.arboriculture.blocks;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public abstract class BlockAbstractLeaves extends LeavesBlock {
    public static final int FOLIAGE_COLOR_INDEX = 0;
    public static final int FRUIT_COLOR_INDEX = 2;

    public BlockAbstractLeaves(BlockBehaviour.Properties properties) {
        super(0.1f, properties.strength(0.2f).sound(SoundType.GRASS).randomTicks().noOcclusion());
    }

    @Override
    public abstract MapCodec<? extends BlockAbstractLeaves> codec();

    @Nullable
    protected abstract IGenome getGenome(BlockGetter level, BlockPos pos);

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        IGenome genome = getGenome(level, pos);
        if (genome == null) {
            return ItemStack.EMPTY;
        }
        ItemStack decorative = genome.getActiveAllele(TreeChromosomes.SPECIES).value().getDecorativeLeaves();
        return decorative.isEmpty() ? ItemStack.EMPTY : decorative.copy();
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        IGenome genome = getGenome(level, pos);
        if (genome != null && genome.getActiveAllele(TreeChromosomes.SPECIES).value().id().getPath().equals("tree_willow")) {
            return Shapes.empty();
        }
        return super.getCollisionShape(state, level, pos, context);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
            net.minecraft.world.entity.InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        Vec3 motion = entity.getDeltaMovement();
        entity.setDeltaMovement(motion.multiply(0.4D, 1.0D, 0.4D));
    }

    protected abstract void getLeafDrop(List<ItemStack> drops, Level level, @Nullable BlockPos pos,
            @Nullable Player player, float saplingModifier, LootParams.Builder context);

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        BlockPos pos = BlockPos.containing(params.getParameter(LootContextParams.ORIGIN));
        IGenome genome = getGenome(params.getLevel(), pos);
        if (genome != null && keepsLeaves(params)) {
            ItemStack decorative = genome.getActiveAllele(TreeChromosomes.SPECIES).value().getDecorativeLeaves();
            if (!decorative.isEmpty()) {
                return List.of(decorative.copy());
            }
        }

        List<ItemStack> drops = new ArrayList<>();
        Player player = params.getOptionalParameter(LootContextParams.THIS_ENTITY) instanceof Player p ? p : null;
        getLeafDrop(drops, params.getLevel(), pos, player, 1.0f, params);
        return drops;
    }

    private static boolean keepsLeaves(LootParams.Builder params) {
        ItemInstance toolInstance = params.getOptionalParameter(LootContextParams.TOOL);
        if (!(toolInstance instanceof ItemStack tool)) {
            return false;
        }
        if (tool.is(Items.SHEARS)) {
            return true;
        }
        Holder<Enchantment> silkTouch = params.getLevel().registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(Enchantments.SILK_TOUCH);
        return EnchantmentHelper.getItemEnchantmentLevel(silkTouch, tool) > 0;
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
        ColorParticleOption particle = ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, level.getClientLeafTintColor(pos));
        ParticleUtils.spawnParticleBelow(level, pos, random, particle);
    }
}
