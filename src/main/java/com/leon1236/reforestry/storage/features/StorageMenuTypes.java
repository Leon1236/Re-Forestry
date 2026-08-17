package com.leon1236.reforestry.storage.features;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.storage.gui.BackpackMenuData;
import com.leon1236.reforestry.storage.gui.ContainerBackpack;
import com.leon1236.reforestry.storage.gui.ContainerNaturalistBackpack;
import com.leon1236.reforestry.storage.gui.NaturalistBackpackMenuData;

public class StorageMenuTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("storage"));

	public static final StreamCodec<io.netty.buffer.ByteBuf, BackpackMenuData> BACKPACK_MENU_DATA =
			StreamCodec.composite(
					ByteBufCodecs.BOOL, BackpackMenuData::woven,
					ByteBufCodecs.BOOL, BackpackMenuData::mainHand,
					BackpackMenuData::new);

	public static final FeatureMenuType<ContainerBackpack, BackpackMenuData> BACKPACK =
			REGISTRY.menuType("backpack", ContainerBackpack::fromNetwork, BACKPACK_MENU_DATA);

	public static final StreamCodec<io.netty.buffer.ByteBuf, NaturalistBackpackMenuData> NATURALIST_BACKPACK_MENU_DATA =
			StreamCodec.composite(
					ByteBufCodecs.VAR_INT, NaturalistBackpackMenuData::page,
					net.minecraft.resources.Identifier.STREAM_CODEC, NaturalistBackpackMenuData::typeId,
					ByteBufCodecs.BOOL, NaturalistBackpackMenuData::mainHand,
					NaturalistBackpackMenuData::new);

	public static final FeatureMenuType<ContainerNaturalistBackpack, NaturalistBackpackMenuData> NATURALIST_BACKPACK =
			REGISTRY.menuType("naturalist_backpack", ContainerNaturalistBackpack::fromNetwork, NATURALIST_BACKPACK_MENU_DATA);

	public static void init() {
	}
}
