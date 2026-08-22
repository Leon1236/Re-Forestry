package com.leon1236.reforestry.extratrees;

import java.util.Arrays;
import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.genetics.IGenome;

public enum ExtraTreeWoodType implements IWoodType {
	CEDAR(0xd86634),
	HEMLOCK(0xc7b56c),
	CYPRESS(0xf6b85c),
	FIG(0xc8882a),
	ALDER(0xb88553),
	HAZEL(0xcdb195),
	HORNBEAM(0xc39860),
	BOX(0xfbf1c6),
	BUTTERNUT(0xecaa7a),
	HICKORY(0xdab48e),
	WHITEBEAM(0xc9c2b9),
	APPLE(0x603528),
	YEW(0xe0a57a),
	HAWTHORN(0xcc8362),
	ROWAN(0xcfad9a),
	ELDER(0xbe9279),
	MACLURA(0xf3b22e),
	SYZGIUM(0xe6c3c1),
	BRAZILWOOD(0x723e57),
	LOGWOOD(0xa4372c),
	IROKO(0x753400),
	LOCUST(0xc39160),
	EUCALYPTUS(0xf6ab8b),
	PURPLEHEART(0x5b1c2f),
	ASH(0xf5c768, true, 2.0f, "et_ash"),
	HOLLY(0xfbf6e7),
	SWEETGUM(0xd59658),
	ROSEWOOD(0x761500),
	PINK_IVORY(0xec8ca0),
	BANANA(0x907343),

	EUCALYPTUS2(0xf6ab8b, false, 2.0f, "eucalyptus2"),
	EUCALYPTUS3(0xf6ab8b, false, 2.0f, "eucalyptus3"),
	ET_CHERRY(0xb58234, false, 2.0f, "et_cherry"),
	CINNAMON(0xb1825c, false, 2.0f, "cinnamon"),
	SHRUB(0xc9c2b9, false, 5.0f, "shrub");

	public static final ExtraTreeWoodType[] VALUES = values();
	public static final ExtraTreeWoodType[] WITH_PRODUCTS = Arrays.stream(VALUES)
			.filter(ExtraTreeWoodType::hasProducts)
			.toArray(ExtraTreeWoodType[]::new);
	public static final ExtraTreeWoodType[] LOG_TYPES = Arrays.stream(VALUES)
			.filter(type -> type != SHRUB)
			.toArray(ExtraTreeWoodType[]::new);
	public static final ExtraTreeWoodType[] ALL_LOG_TYPES = VALUES;

	public final String serializedName;
	private final int colour;
	private final boolean hasProducts;
	private final float hardness;
	private final BlockSetType blockSetType;
	private final WoodType woodType;

	ExtraTreeWoodType(int colour) {
		this(colour, true, 2.0f, null);
	}

	ExtraTreeWoodType(int colour, boolean hasProducts, float hardness, @Nullable String serializedName) {
		this.serializedName = serializedName != null ? serializedName : name().toLowerCase(Locale.ENGLISH);
		this.colour = colour;
		this.hasProducts = hasProducts;
		this.hardness = hardness;
		this.blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(ReForestry.id(this.serializedName));
		this.woodType = WoodTypeBuilder.copyOf(WoodType.OAK).register(ReForestry.id(this.serializedName), this.blockSetType);
	}

	public boolean hasProducts() {
		return hasProducts;
	}

	public int getColour() {
		return colour;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}

	@Override
	public float getHardness() {
		return hardness;
	}

	@Override
	public WoodType getVanillaWoodType() {
		return woodType;
	}

	@Override
	public BlockSetType getBlockSetType() {
		return blockSetType;
	}

	@Override
	public boolean setDefaultLeaves(LevelAccessor level, BlockPos pos, IGenome genome, RandomSource rand, @Nullable GameProfile owner) {
		return false;
	}

	@Override
	public String toString() {
		return serializedName;
	}
}
