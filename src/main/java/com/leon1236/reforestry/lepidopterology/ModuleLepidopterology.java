package com.leon1236.reforestry.lepidopterology;

import java.util.List;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import net.minecraft.commands.Commands;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.lepidopterology.client.LepidopterologyClientHandler;
import com.leon1236.reforestry.lepidopterology.commands.CommandButterfly;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyCreativeTabs;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyDataComponents;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyEntities;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyItems;
import com.leon1236.reforestry.lepidopterology.genetics.LepidopterologyGenetics;

@ForestryModule(name = "Lepidopterology", description = "Butterflies. Pretty.")
public class ModuleLepidopterology implements IForestryModule {
	private static final float SERUM_CHANCE = 0.55f;
	private static final float SECOND_SERUM_CHANCE = 0f;
	private static final int MAX_DISTANCE = 64;

	@Override
	public Identifier getId() {
		return ReForestry.id("lepidopterology");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"), ReForestry.id("arboriculture"));
	}

	@Override
	public void init() {
		LepidopterologyItems.init();
		LepidopterologyDataComponents.init();
		LepidopterologyEntities.init();
		FabricDefaultAttributeRegistry.register(LepidopterologyEntities.BUTTERFLY.entityType(), Mob.createMobAttributes());
		LepidopterologyGenetics.finalizeRegistration();
		LepidopterologyCreativeTabs.init();
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
				dispatcher.register(Commands.literal(ReForestry.MOD_ID).then(CommandButterfly.register())));
	}

	public static int getMaxDistance() {
		return MAX_DISTANCE;
	}

	public static float getSerumChance() {
		return SERUM_CHANCE;
	}

	public static float getSecondSerumChance() {
		return SECOND_SERUM_CHANCE;
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new LepidopterologyClientHandler());
	}
}
