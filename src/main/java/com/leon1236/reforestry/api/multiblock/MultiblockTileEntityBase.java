package com.leon1236.reforestry.api.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class MultiblockTileEntityBase<T extends IMultiblockLogic> extends BlockEntity implements IMultiblockComponent {
	private final T multiblockLogic;

	public MultiblockTileEntityBase(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state, T multiblockLogic) {
		super(tileEntityType, pos, state);
		this.multiblockLogic = multiblockLogic;
	}

	@Override
	public BlockPos getCoordinates() {
		return getBlockPos();
	}

	@Override
	public T getMultiblockLogic() {
		return this.multiblockLogic;
	}

	@Override
	public abstract void onMachineAssembled(IMultiblockController multiblockController, BlockPos minCoord, BlockPos maxCoord);

	@Override
	public abstract void onMachineBroken();

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.multiblockLogic.read(input);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		HolderLookup.Provider registries = this.level != null ? this.level.registryAccess() : RegistryAccess.EMPTY;
		this.multiblockLogic.write(output, registries);
	}

	@Override
	public void setRemoved() {
		if (this.level != null) {
			this.multiblockLogic.invalidate(this.level, this);
		}
		super.setRemoved();
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag updateTag = saveCustomOnly(registries);
		this.multiblockLogic.encodeDescriptionPacket(updateTag);
		this.encodeDescriptionPacket(updateTag);
		return updateTag;
	}

	protected void encodeDescriptionPacket(CompoundTag packetData) {
	}

	protected void decodeDescriptionPacket(CompoundTag packetData) {
	}

	public void handleMultiblockUpdateTag(CompoundTag tag) {
		this.multiblockLogic.decodeDescriptionPacket(tag);
		this.decodeDescriptionPacket(tag);
	}
}
