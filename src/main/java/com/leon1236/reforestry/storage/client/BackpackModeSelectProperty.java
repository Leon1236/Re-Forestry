package com.leon1236.reforestry.storage.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import org.jspecify.annotations.Nullable;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.storage.BackpackMode;
import com.leon1236.reforestry.storage.items.ItemBackpack;

public record BackpackModeSelectProperty() implements SelectItemModelProperty<BackpackMode> {
	public static final BackpackModeSelectProperty INSTANCE = new BackpackModeSelectProperty();
	public static final SelectItemModelProperty.Type<BackpackModeSelectProperty, BackpackMode> TYPE =
			SelectItemModelProperty.Type.create(MapCodec.unit(INSTANCE), BackpackMode.CODEC);

	@Override
	public @Nullable BackpackMode get(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner, int seed, ItemDisplayContext displayContext) {
		return ItemBackpack.getMode(itemStack);
	}

	@Override
	public Codec<BackpackMode> valueCodec() {
		return BackpackMode.CODEC;
	}

	@Override
	public SelectItemModelProperty.Type<BackpackModeSelectProperty, BackpackMode> type() {
		return TYPE;
	}
}
