package com.leon1236.reforestry.core.owner;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;

import com.leon1236.reforestry.api.core.INbtReadable;
import com.leon1236.reforestry.api.core.INbtWritable;

public class OwnerHandler implements IOwnerHandler, INbtWritable, INbtReadable {
	@Nullable
	private GameProfile owner;

	@Override
	@Nullable
	public GameProfile getOwner() {
		return this.owner;
	}

	@Override
	public void setOwner(GameProfile owner) {
		this.owner = owner;
	}

	@Override
	public void read(CompoundTag data, HolderLookup.Provider registries) {
		data.getString("owner_name").ifPresent(name -> {
			UUID id = data.getIntArray("owner_uuid")
					.map(UUIDUtil::uuidFromIntArray)
					.orElse(null);
			setOwner(new GameProfile(id, name));
		});
	}

	@Override
	public CompoundTag write(CompoundTag data, HolderLookup.Provider registries) {
		if (this.owner != null) {
			if (this.owner.id() != null) {
				data.putIntArray("owner_uuid", UUIDUtil.uuidToIntArray(this.owner.id()));
			}
			data.putString("owner_name", this.owner.name());
		}
		return data;
	}
}
