package com.leon1236.reforestry.extra_bees.items;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.hives.IHiveFrame;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;

public class ItemExtraBeeHiveFrame extends Item implements IHiveFrame {
	private final EnumExtraBeeFrame type;
	private final FrameBeeModifier beeModifier;

	public ItemExtraBeeHiveFrame(EnumExtraBeeFrame type, Properties properties) {
		super(properties.durability(type.maxDamage));
		this.type = type;
		this.beeModifier = new FrameBeeModifier(type);
	}

	@Override
	public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IGenome queen, int wear) {
		Level level = housing.level();
		if (level instanceof ServerLevel serverLevel) {
			frame.hurtAndBreak(wear, serverLevel, null, item -> {
			});
		}
		return frame.isEmpty() ? ItemStack.EMPTY : frame;
	}

	@Override
	public IBeeModifier getBeeModifier(ItemStack frame) {
		return beeModifier;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		beeModifier.addInformation(tooltip);
		if (!stack.isDamaged()) {
			tooltip.accept(Component.translatable("item.reforestry.durability", stack.getMaxDamage()));
		}
	}

	public EnumExtraBeeFrame getFrameType() {
		return type;
	}

	private static final class FrameBeeModifier implements IBeeModifier {
		private final EnumExtraBeeFrame type;

		private FrameBeeModifier(EnumExtraBeeFrame type) {
			this.type = type;
		}

		private static float apply(float mult, float limit, float current) {
			if (mult == 0f && limit == 0f) {
				return 1.0f;
			}
			if (limit >= 1.0f) {
				if (limit <= current) {
					return 1.0f;
				}
				return Math.min(limit / current, mult);
			}
			if (limit >= current) {
				return 1.0f;
			}
			return Math.max(limit / current, mult);
		}

		@Override
		public Vec3i modifyTerritory(IGenome genome, Vec3i currentModifier) {
			float current = Math.max(currentModifier.getX(), Math.max(currentModifier.getY(), currentModifier.getZ()));
			float factor = apply(type.territory, type.territoryMax, current <= 0f ? 1.0f : current);
			if (factor == 1.0f) {
				return currentModifier;
			}
			return new Vec3i(
					Math.max(1, Math.round(currentModifier.getX() * factor)),
					Math.max(1, Math.round(currentModifier.getY() * factor)),
					Math.max(1, Math.round(currentModifier.getZ() * factor)));
		}

		@Override
		public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
			return currentChance * apply(type.mutation, type.mutationMax, currentChance);
		}

		@Override
		public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
			float currentLifespan = currentAging == 0f ? Float.MAX_VALUE : 1.0f / currentAging;
			float lifespanFactor = apply(type.lifespan, type.lifespanMax, currentLifespan);
			if (lifespanFactor == 0f) {
				return currentAging * 10000f;
			}
			return currentAging / lifespanFactor;
		}

		@Override
		public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
			return currentSpeed * apply(type.production, type.productionMax, currentSpeed);
		}

		private void addInformation(Consumer<Component> tooltip) {
			if (type.lifespan != 0f || type.lifespanMax != 0f) {
				tooltip.accept(Component.translatable("item.reforestry.bee.modifier.lifespan", type.lifespan));
			}
			if (type.production != 0f || type.productionMax != 0f) {
				tooltip.accept(Component.translatable("item.reforestry.bee.modifier.production", type.production));
			}
			if (type.mutation != 0f || type.mutationMax != 0f) {
				tooltip.accept(Component.translatable("item.reforestry.bee.modifier.mutation", type.mutation));
			}
			if (type.territory != 0f || type.territoryMax != 0f) {
				tooltip.accept(Component.translatable("item.reforestry.bee.modifier.territory", type.territory));
			}
		}
	}
}
