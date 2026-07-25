package com.leon1236.reforestry.core.owner;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

public interface IOwnerHandler {
	@Nullable
	GameProfile getOwner();

	void setOwner(GameProfile owner);
}
