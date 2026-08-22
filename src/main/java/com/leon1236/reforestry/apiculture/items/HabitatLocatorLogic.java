package com.leon1236.reforestry.apiculture.items;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.apiculture.genetics.IBee;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.apiculture.network.HabitatBiomePointerPayload;
import com.leon1236.reforestry.core.network.PacketRegistry;

public final class HabitatLocatorLogic {
	private static final int SPACING = 20;
	private static final int MIN_BIOME_RADIUS = 8;
	private static final int MAX_RADIUS = 500 * SPACING;

	private HabitatLocatorLogic() {
	}

	public static void searchFromSpecimen(Player player, IBee bee) {
		if (!(player instanceof ServerPlayer serverPlayer) || player.level().isClientSide()) {
			return;
		}
		Level level = player.level();
		Set<Holder<Biome>> targets = new HashSet<>(bee.getSuitableBiomes(level.registryAccess().lookupOrThrow(Registries.BIOME)));
		filterTargets(level, player.blockPosition(), targets);
		if (targets.isEmpty()) {
			return;
		}
		BlockPos playerPos = player.blockPosition();
		BlockPos localMatch = findLocalMatch(level, playerPos, targets);
		BlockPos target = localMatch != null ? playerPos : findNearestBiome(level, playerPos, targets);
		if (target != null) {
			PacketRegistry.sendToPlayer(serverPlayer, new HabitatBiomePointerPayload(target));
		}
	}

	@Nullable
	public static IBee beeFromStack(net.minecraft.world.item.ItemStack stack) {
		if (stack.isEmpty() || !IndividualItems.isIndividual(stack)) {
			return null;
		}
		var genome = IndividualItems.getGenome(stack);
		return genome instanceof IBee bee ? bee : null;
	}

	private static void filterTargets(Level level, BlockPos pos, Set<Holder<Biome>> targets) {
		targets.removeIf(holder -> holder.is(BiomeTags.IS_OCEAN) || holder.is(BiomeTags.IS_RIVER) || holder.is(BiomeTags.IS_BEACH));
		Holder<Biome> current = level.getBiome(pos);
		if (current.is(BiomeTags.IS_NETHER)) {
			targets.removeIf(holder -> !holder.is(BiomeTags.IS_NETHER));
		} else {
			targets.removeIf(holder -> holder.is(BiomeTags.IS_NETHER));
		}
		if (current.is(BiomeTags.IS_END)) {
			targets.removeIf(holder -> !holder.is(BiomeTags.IS_END));
		} else {
			targets.removeIf(holder -> holder.is(BiomeTags.IS_END));
		}
	}

	@Nullable
	private static BlockPos findLocalMatch(Level level, BlockPos playerPos, Set<Holder<Biome>> targets) {
		if (matchesBiomeArea(level, playerPos, targets)) {
			return playerPos;
		}
		return null;
	}

	@Nullable
	private static BlockPos findNearestBiome(Level level, BlockPos center, Set<Holder<Biome>> targets) {
		for (int radius = SPACING; radius <= MAX_RADIUS; radius += SPACING) {
			double angleSpacing = 2.0 * Math.asin(SPACING / (2.0 * radius));
			angleSpacing = 2.0 * Math.PI / Math.round(2.0 * Math.PI / angleSpacing);
			for (double angle = 0.0; angle < 2.0 * Math.PI; angle += angleSpacing) {
				int xOffset = Math.round((float) (radius * Math.cos(angle)));
				int zOffset = Math.round((float) (radius * Math.sin(angle)));
				BlockPos pos = center.offset(xOffset, 0, zOffset);
				if (matchesBiomeArea(level, pos, targets)) {
					return pos;
				}
			}
		}
		return null;
	}

	private static boolean matchesBiomeArea(Level level, BlockPos pos, Set<Holder<Biome>> targets) {
		return isMatchingBiome(level, pos, targets)
				&& isMatchingBiome(level, pos.offset(-MIN_BIOME_RADIUS, 0, 0), targets)
				&& isMatchingBiome(level, pos.offset(MIN_BIOME_RADIUS, 0, 0), targets)
				&& isMatchingBiome(level, pos.offset(0, 0, -MIN_BIOME_RADIUS), targets)
				&& isMatchingBiome(level, pos.offset(0, 0, MIN_BIOME_RADIUS), targets);
	}

	private static boolean isMatchingBiome(Level level, BlockPos pos, Set<Holder<Biome>> targets) {
		return targets.contains(level.getBiome(pos));
	}
}
