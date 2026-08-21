package com.leon1236.reforestry.cultivation.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.cultivation.tiles.TilePlanter;

public class BlockPlanter extends BlockMachine<BlockTypePlanter> {
	private final MapCodec<BlockPlanter> codec;
	private final boolean manual;

	public BlockPlanter(BlockTypePlanter type, boolean manual, BlockBehaviour.Properties properties) {
		super(type, properties.noOcclusion());
		this.manual = manual;
		this.codec = simpleCodec(props -> new BlockPlanter(type, manual, props));
	}

	public boolean isManual() {
		return this.manual;
	}

	@Override
	protected MapCodec<? extends BlockPlanter> codec() {
		return this.codec;
	}

	@Override
	public MutableComponent getName() {
		return Component.translatable("block.reforestry.planter." + (this.manual ? "manual" : "managed"),
				Component.translatable("block.reforestry." + this.blockType.getSerializedName()));
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		if (this.blockType != BlockTypePlanter.FARM_ENDER) {
			return;
		}
		for (int i = 0; i < 3; i++) {
			int j = rand.nextInt(2) * 2 - 1;
			int k = rand.nextInt(2) * 2 - 1;
			double xPos = pos.getX() + 0.5 + 0.25 * j;
			double yPos = pos.getY() + rand.nextFloat();
			double zPos = pos.getZ() + 0.5 + 0.25 * k;
			double xSpeed = rand.nextFloat() * j;
			double ySpeed = (rand.nextFloat() - 0.5) * 0.125;
			double zSpeed = rand.nextFloat() * k;
			level.addParticle(ParticleTypes.PORTAL, xPos, yPos, zPos, xSpeed, ySpeed, zSpeed);
		}
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		BlockEntity tile = super.newBlockEntity(pos, state);
		if (tile instanceof TilePlanter planter) {
			planter.setManual(this.manual);
		}
		return tile;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (!level.isClientSide() && placer instanceof Player player && level.getBlockEntity(pos) instanceof TilePlanter planter) {
			planter.getOwnerHandler().setOwner(player.getGameProfile());
		}
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof TilePlanter planter) {
			Containers.dropContents(level, pos, planter);
		}
		return super.playerWillDestroy(level, pos, state, player);
	}
}
