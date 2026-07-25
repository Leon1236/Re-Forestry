package com.leon1236.reforestry.core.blocks;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockController;
import com.leon1236.reforestry.core.multiblock.MultiblockTileEntityForestry;
import com.leon1236.reforestry.core.tiles.TileUtil;

public abstract class BlockStructure extends BaseEntityBlock {
	private long previousMessageTick;

	protected BlockStructure(Properties properties) {
		super(properties.strength(1f));
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}

		MultiblockTileEntityForestry<?> part = TileUtil.getTile(level, pos, MultiblockTileEntityForestry.class);
		if (part == null) {
			return InteractionResult.FAIL;
		}

		IMultiblockController controller = part.getMultiblockLogic().getController();
		if (!controller.isAssembled()) {
			String validationError = controller.getLastValidationError();
			if (validationError != null) {
				long tick = level.getGameTime();
				if (tick > this.previousMessageTick + 20) {
					player.sendOverlayMessage(Component.literal(validationError));
					this.previousMessageTick = tick;
				}
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.PASS;
		}

		if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer
				&& part instanceof MenuProvider provider) {
			serverPlayer.openMenu(provider);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (level.isClientSide()) {
			return;
		}
		if (placer instanceof Player player) {
			TileUtil.actOnTile(level, pos, MultiblockTileEntityForestry.class, tile -> {
				GameProfile profile = player.getGameProfile();
				tile.setOwner(profile);
			});
		}
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof IMultiblockComponent.HasInventory component) {
			Containers.dropContents(level, pos, component.getInternalInventory());
		}
		return super.playerWillDestroy(level, pos, state, player);
	}
}
