package com.leon1236.reforestry.lepidopterology.items;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.IIndividualItem;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.lepidopterology.genetics.ButterflyLifeStage;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.lepidopterology.entities.EntityButterfly;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyEntities;
import com.leon1236.reforestry.core.genetics.GeneticsTooltips;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyDataComponents;
import com.leon1236.reforestry.lepidopterology.genetics.Butterfly;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflySpeciesType;

public class ItemButterflyGE extends Item implements IIndividualItem {
	private final ButterflyLifeStage lifeStage;

	public ItemButterflyGE(Properties properties, ButterflyLifeStage lifeStage) {
		super(properties);
		this.lifeStage = lifeStage;
	}

	public ButterflyLifeStage lifeStage() {
		return lifeStage;
	}

	@Override
	@Nullable
	public IIndividual getIndividualFromComponent(ItemStack stack) {
		return Butterfly.fromStack(stack);
	}

	@Override
	public ILifeStage getLifeStage() {
		return lifeStage;
	}

	@Override
	public ISpeciesType<?, ?> getSpeciesType() {
		return ButterflySpeciesType.INSTANCE;
	}

	@Override
	public Component getName(ItemStack stack) {
		IGenome genome = stack.get(LepidopterologyDataComponents.BUTTERFLY_GENOME.type());
		if (genome == null) {
			return super.getName(stack);
		}
		IButterflySpecies species = genome.getActiveAllele(ButterflyChromosomes.SPECIES).value();
		Component speciesName = species.getDisplayName();
		String grammar = lifeStage.getSerializedName();
		Component typeName = Component.translatable("for.butterflies.grammar." + grammar + ".type");
		return Component.translatable("for.butterflies.grammar." + grammar, speciesName, typeName);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		GeneticsTooltips.appendGeneticsTooltip(stack, tooltip, genome -> GeneticsTooltips.addHybridTooltip(
				tooltip, genome, ButterflyChromosomes.SPECIES, "for.butterflies.hybrid"));
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		IGenome genome = stack.get(LepidopterologyDataComponents.BUTTERFLY_GENOME.type());
		if (genome == null) {
			return false;
		}
		return genome.getActiveAllele(ButterflyChromosomes.SPECIES).value().hasGlint();
	}

	public static void onEntityItemUpdate(ItemEntity entityItem) {
		ItemStack stack = entityItem.getItem();
		if (!(stack.getItem() instanceof ItemButterflyGE item) || item.lifeStage != ButterflyLifeStage.BUTTERFLY) {
			return;
		}
		Level level = entityItem.level();
		if (level.isClientSide() || entityItem.tickCount < 80) {
			return;
		}
		if (level.getRandom().nextInt(24) != 0) {
			return;
		}
		IButterfly butterfly = Butterfly.fromStack(stack);
		if (butterfly == null) {
			return;
		}
		if (!butterfly.canTakeFlight(level, entityItem.getX(), entityItem.getY(), entityItem.getZ())) {
			return;
		}
		EntityButterfly spawned = EntityButterfly.create(
				LepidopterologyEntities.BUTTERFLY.entityType(),
				level,
				butterfly,
				entityItem.blockPosition());
		spawned.setPos(entityItem.getX(), entityItem.getY(), entityItem.getZ());
		level.addFreshEntity(spawned);
		stack.shrink(1);
		if (stack.isEmpty()) {
			entityItem.remove(Entity.RemovalReason.DISCARDED);
		}
	}
}
