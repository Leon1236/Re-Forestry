package com.leon1236.reforestry.core.compat.trinkets;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Predicate;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.IArmorNaturalist;
import com.leon1236.reforestry.core.NaturalistEyeHooks;
import com.leon1236.reforestry.core.features.CoreItems;

public final class TrinketsCompat {
	private static final String TRINKETS_API = "dev.emi.trinkets.api.TrinketsApi";
	private static final String TRINKET = "dev.emi.trinkets.api.Trinket";

	private TrinketsCompat() {
	}

	public static void init() {
		try {
			registerTrinket();
			NaturalistEyeHooks.registerTrinketsCheck(TrinketsCompat::hasNaturalistEye);
		} catch (ReflectiveOperationException exception) {
			ReForestry.LOGGER.warn("Trinkets compat failed to initialize", exception);
		}
	}

	private static void registerTrinket() throws ReflectiveOperationException {
		Class<?> trinketType = Class.forName(TRINKET);
		Item spectacles = CoreItems.SPECTACLES.item();
		Object trinket = Proxy.newProxyInstance(trinketType.getClassLoader(), new Class<?>[] { trinketType }, new TrinketHandler());
		Class.forName(TRINKETS_API)
				.getMethod("registerTrinket", Item.class, trinketType)
				.invoke(null, spectacles, trinket);
	}

	private static boolean hasNaturalistEye(Player player) {
		try {
			Object component = Class.forName(TRINKETS_API)
					.getMethod("getTrinketComponent", net.minecraft.world.entity.LivingEntity.class)
					.invoke(null, player);
			if (!(component instanceof java.util.Optional<?> optional) || optional.isEmpty()) {
				return false;
			}
			Predicate<ItemStack> predicate = stack -> hasNaturalistEye(player, stack);
			Object result = optional.get().getClass().getMethod("isEquipped", Predicate.class).invoke(optional.get(), predicate);
			return Boolean.TRUE.equals(result);
		} catch (ReflectiveOperationException exception) {
			return false;
		}
	}

	private static boolean hasNaturalistEye(Player player, ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		}
		if (stack.getItem() instanceof IArmorNaturalist naturalist) {
			return naturalist.canSeePollination(player, stack, true);
		}
		return false;
	}

	private static final class TrinketHandler implements InvocationHandler {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) {
			return switch (method.getName()) {
				case "canEquip" -> isHeadSlot(args[1]);
				case "canEquipFromUse" -> true;
				case "canUnequip" -> true;
				case "hashCode" -> System.identityHashCode(proxy);
				case "equals" -> proxy == args[0];
				case "toString" -> "ReForestrySpectaclesTrinket";
				default -> defaultValue(method);
			};
		}

		private static boolean isHeadSlot(Object slotReference) {
			if (slotReference == null) {
				return false;
			}
			for (String methodName : new String[] { "group", "getGroup" }) {
				try {
					Method method = slotReference.getClass().getMethod(methodName);
					if ("head".equals(method.invoke(slotReference))) {
						return true;
					}
				} catch (ReflectiveOperationException ignored) {
				}
			}
			return false;
		}

		private static Object defaultValue(Method method) {
			Class<?> returnType = method.getReturnType();
			if (returnType == boolean.class) {
				return false;
			}
			if (returnType == int.class) {
				return 0;
			}
			if (returnType == long.class) {
				return 0L;
			}
			if (returnType == float.class) {
				return 0.0f;
			}
			if (returnType == double.class) {
				return 0.0d;
			}
			return null;
		}
	}
}
