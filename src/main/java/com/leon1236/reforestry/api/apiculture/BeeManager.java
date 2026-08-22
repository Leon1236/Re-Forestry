package com.leon1236.reforestry.api.apiculture;

import java.util.Objects;

import org.jetbrains.annotations.Nullable;

public final class BeeManager {
	@Nullable
	private static IArmorApiaristHelper armorApiaristHelper;

	@Nullable
	private static IJubilanceFactory jubilanceFactory;

	private BeeManager() {
	}

	public static IArmorApiaristHelper getArmorApiaristHelper() {
		IArmorApiaristHelper helper = armorApiaristHelper;
		if (helper == null) {
			throw new NullPointerException("BeeManager.armorApiaristHelper used before init");
		}
		return helper;
	}

	public static void setArmorApiaristHelper(IArmorApiaristHelper helper) {
		armorApiaristHelper = Objects.requireNonNull(helper, "armorApiaristHelper");
	}

	public static IJubilanceFactory getJubilanceFactory() {
		IJubilanceFactory factory = jubilanceFactory;
		if (factory == null) {
			throw new NullPointerException("BeeManager.jubilanceFactory used before init");
		}
		return factory;
	}

	public static void setJubilanceFactory(IJubilanceFactory factory) {
		jubilanceFactory = Objects.requireNonNull(factory, "jubilanceFactory");
	}
}
