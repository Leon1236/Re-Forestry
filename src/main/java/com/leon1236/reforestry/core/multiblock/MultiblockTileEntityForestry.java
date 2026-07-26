package com.leon1236.reforestry.core.multiblock;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.core.ILocationProvider;
import com.leon1236.reforestry.api.core.ISpectacleBlock;
import com.leon1236.reforestry.api.multiblock.IMultiblockLogic;
import com.leon1236.reforestry.api.multiblock.MultiblockTileEntityBase;

public abstract class MultiblockTileEntityForestry<T extends IMultiblockLogic> extends MultiblockTileEntityBase<T> implements ILocationProvider, ISpectacleBlock {
	@Nullable
	private GameProfile owner;

	public MultiblockTileEntityForestry(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state, T multiblockLogic) {
		super(tileEntityType, pos, state, multiblockLogic);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		input.getString("owner_name").ifPresent(name -> {
			UUID id = input.read("owner_uuid", UUIDUtil.CODEC).orElse(null);
			this.owner = new GameProfile(id, name);
		});
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		if (this.owner != null) {
			if (this.owner.id() != null) {
				output.store("owner_uuid", UUIDUtil.CODEC, this.owner.id());
			}
			output.putString("owner_name", this.owner.name());
		}
	}

	@Override
	@Nullable
	public final Level getWorldObj() {
		return this.level;
	}

	@Override
	@Nullable
	public final GameProfile getOwner() {
		return this.owner;
	}

	public final void setOwner(GameProfile owner) {
		this.owner = owner;
	}

	@Override
	public boolean isHighlighted(Player player) {
		return player.isCreative()
				&& getMultiblockLogic().getController() instanceof IMultiblockControllerInternal internal
				&& getBlockPos().equals(internal.getReferenceCoord());
	}
}
