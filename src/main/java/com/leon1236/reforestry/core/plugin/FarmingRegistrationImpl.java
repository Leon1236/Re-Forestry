package com.leon1236.reforestry.core.plugin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.plugin.IFarmTypeBuilder;
import com.leon1236.reforestry.api.plugin.IFarmingRegistration;

public final class FarmingRegistrationImpl implements IFarmingRegistration {
	private final LinkedHashMap<Identifier, FarmTypeBuilderImpl> farmTypes = new LinkedHashMap<>();
	private final HashMap<Identifier, Consumer<IFarmTypeBuilder>> pendingModifications = new HashMap<>();
	private final Object2IntOpenHashMap<Item> fertilizers = new Object2IntOpenHashMap<>();

	@Override
	public IFarmTypeBuilder createFarmType(Identifier id, BiFunction<IFarmType, Boolean, IFarmLogic> logicFactory, ItemStack icon) {
		if (this.farmTypes.containsKey(id)) {
			throw new IllegalStateException("A IFarmTypeBuilder already exists with key " + id + ": " + this.farmTypes.get(id));
		}
		FarmTypeBuilderImpl builder = new FarmTypeBuilderImpl(id, logicFactory, icon);
		this.farmTypes.put(id, builder);
		Consumer<IFarmTypeBuilder> pending = this.pendingModifications.remove(id);
		if (pending != null) {
			pending.accept(builder);
		}
		return builder;
	}

	@Override
	public void modifyFarmType(Identifier id, Consumer<IFarmTypeBuilder> action) {
		IFarmTypeBuilder existing = this.farmTypes.get(id);
		if (existing != null) {
			action.accept(existing);
		} else {
			this.pendingModifications.merge(id, action, Consumer::andThen);
		}
	}

	@Override
	public void registerFertilizer(Item fertilizer, int amount) {
		this.fertilizers.put(fertilizer, amount);
	}

	public Object2IntOpenHashMap<Item> getFertilizers() {
		return this.fertilizers;
	}

	public ImmutableMap<Identifier, IFarmType> buildFarmTypes() {
		if (!this.pendingModifications.isEmpty()) {
			Identifier leftover = this.pendingModifications.keySet().iterator().next();
			throw new IllegalStateException("Tried to modify non-existent IFarmTypeBuilder with ID " + leftover);
		}
		ImmutableMap.Builder<Identifier, IFarmType> built = ImmutableMap.builder();
		for (var entry : this.farmTypes.entrySet()) {
			built.put(entry.getKey(), entry.getValue().build());
		}
		return built.build();
	}
}
