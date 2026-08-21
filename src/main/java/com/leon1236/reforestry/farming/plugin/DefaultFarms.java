package com.leon1236.reforestry.farming.plugin;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.agriculture.ForestryFarmTypes;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.plugin.IFarmTypeBuilder;
import com.leon1236.reforestry.api.plugin.IFarmingRegistration;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.DefaultFruits;
import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.features.CoreBlocks;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.items.EnumFruit;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicArboreal;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicCocoa;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicCrops;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicEnder;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicGourd;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicInfernal;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicMushroom;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicOrchard;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicPeat;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicReeds;
import com.leon1236.reforestry.farming.farmlogic.FarmLogicSucculent;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableAgingCrop;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableChorus;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableGE;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableGourd;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableMangroveTree;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableMushroom;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableSapling;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableStacked;

public final class DefaultFarms {
	private DefaultFarms() {
	}

	public static void registerFarmTypes(IFarmingRegistration farming) {
		boolean arboriculture = IForestryApi.INSTANCE.getModuleManager().isModuleLoaded(ReForestry.id("arboriculture"));

		IFarmTypeBuilder arboreal = farming.createFarmType(ForestryFarmTypes.ARBOREAL, FarmLogicArboreal::new, new ItemStack(Blocks.OAK_SAPLING))
				.setFertilizerConsumption(10)
				.setWaterConsumption(hydrationModifier -> (int) (10 * hydrationModifier))
				.addSoil(new ItemStack(Blocks.DIRT), CoreBlocks.HUMUS.block().defaultBlockState())
				.addSoil(new ItemStack(CoreBlocks.HUMUS.block()), CoreBlocks.HUMUS.block().defaultBlockState());
		addTreeFarmables(arboreal, arboriculture);

		IFarmTypeBuilder crops = farming.createFarmType(ForestryFarmTypes.CROPS, FarmLogicCrops::new, new ItemStack(Items.WHEAT))
				.setWaterConsumption(hydrationModifier -> (int) (20 * hydrationModifier))
				.setFertilizerConsumption(5)
				.addSoil(new ItemStack(Blocks.DIRT), Blocks.FARMLAND.defaultBlockState());
		addCropFarmables(crops);

		IFarmTypeBuilder gourd = farming.createFarmType(ForestryFarmTypes.GOURD, FarmLogicGourd::new, new ItemStack(Items.MELON))
				.setFertilizerConsumption(10)
				.setWaterConsumption(hydrationModifier -> (int) (40 * hydrationModifier))
				.addSoil(new ItemStack(Blocks.DIRT), Blocks.FARMLAND.defaultBlockState());
		addGourdFarmables(gourd);

		IFarmTypeBuilder shroom = farming.createFarmType(ForestryFarmTypes.SHROOM, FarmLogicMushroom::new, new ItemStack(Blocks.RED_MUSHROOM))
				.setFertilizerConsumption(20)
				.setWaterConsumption(hydrationModifier -> (int) (80 * hydrationModifier))
				.addSoil(Blocks.MYCELIUM)
				.addSoil(Blocks.PODZOL);
		shroom.addFarmable(new FarmableMushroom(new ItemStack(Items.BROWN_MUSHROOM), Blocks.BROWN_MUSHROOM.defaultBlockState()));
		shroom.addFarmable(new FarmableMushroom(new ItemStack(Items.RED_MUSHROOM), Blocks.RED_MUSHROOM.defaultBlockState()));

		IFarmTypeBuilder infernal = farming.createFarmType(ForestryFarmTypes.INFERNAL, FarmLogicInfernal::new, new ItemStack(Items.NETHER_WART))
				.setFertilizerConsumption(20)
				.setWaterConsumption(0)
				.addSoil(Blocks.SOUL_SAND);
		infernal.addFarmable(new FarmableAgingCrop(Items.NETHER_WART, Blocks.NETHER_WART, NetherWartBlock.AGE, 3));

		IFarmTypeBuilder poales = farming.createFarmType(ForestryFarmTypes.POALES, FarmLogicReeds::new, new ItemStack(Items.SUGAR_CANE))
				.setFertilizerConsumption(10)
				.setWaterConsumption(hydrationModifier -> (int) (20 * hydrationModifier))
				.addSoil(Blocks.SAND)
				.addSoil(Blocks.DIRT);
		poales.addFarmable(new FarmableStacked(new ItemStack(Items.SUGAR_CANE), Blocks.SUGAR_CANE, 3));

		IFarmTypeBuilder cactus = farming.createFarmType(ForestryFarmTypes.SUCCULENTES, FarmLogicSucculent::new, new ItemStack(Items.DYE.green()))
				.setFertilizerConsumption(10)
				.setWaterConsumption(1)
				.addSoil(Blocks.SAND);
		cactus.addFarmable(new FarmableStacked(new ItemStack(Blocks.CACTUS), Blocks.CACTUS, 3));

		IFarmTypeBuilder ender = farming.createFarmType(ForestryFarmTypes.ENDER, FarmLogicEnder::new, new ItemStack(Items.ENDER_EYE))
				.setFertilizerConsumption(20)
				.setWaterConsumption(0)
				.addSoil(Blocks.END_STONE);
		ender.addFarmable(FarmableChorus.INSTANCE);

		IFarmTypeBuilder peat = farming.createFarmType(ForestryFarmTypes.PEAT, FarmLogicPeat::new, new ItemStack(CoreItems.PEAT.item()))
				.setWaterConsumption(hydrationModifier -> (int) (20 * hydrationModifier))
				.setFertilizerConsumption(2)
				.addSoil(new ItemStack(CoreBlocks.BOG_EARTH.block()), CoreBlocks.BOG_EARTH.block().defaultBlockState())
				.addProducts(List.of(new ItemStack(CoreItems.PEAT.item()), new ItemStack(Blocks.DIRT)));

		IFarmTypeBuilder orchard = farming.createFarmType(ForestryFarmTypes.ORCHARD, FarmLogicOrchard::new, new ItemStack(CoreItems.FRUITS.item(EnumFruit.CHERRY)))
				.setFertilizerConsumption(10)
				.setWaterConsumption(hydrationModifier -> (int) (40 * hydrationModifier));
		if (arboriculture) {
			for (ITreeSpecies species : ArboricultureGenetics.getAllSpecies()) {
				IGenome genome = ArboricultureGenetics.getDefaultGenome(species.id());
				IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
				if (fruit != DefaultFruits.NONE) {
					orchard.addGermling(createSaplingStack(genome))
							.addProducts(fruit.getProducts().stream().map(product -> new ItemStack(product.item())).toList())
							.addProducts(fruit.getSpecialties().stream().map(product -> new ItemStack(product.item())).toList());
				}
			}
		}

		IFarmTypeBuilder cocoa = farming.createFarmType(ForestryFarmTypes.COCOA, FarmLogicCocoa::new, new ItemStack(Items.COCOA_BEANS))
				.setFertilizerConsumption(120)
				.setWaterConsumption(hydrationModifier -> (int) (20 * hydrationModifier))
				.addGermling(new ItemStack(Items.COCOA_BEANS))
				.addProduct(new ItemStack(Items.COCOA_BEANS));
	}

	private static void addGourdFarmables(IFarmTypeBuilder gourd) {
		gourd.addFarmable(new FarmableGourd(new ItemStack(Items.PUMPKIN_SEEDS), Blocks.PUMPKIN_STEM, Blocks.PUMPKIN));
		gourd.addFarmable(new FarmableGourd(new ItemStack(Items.MELON_SEEDS), Blocks.MELON_STEM, Blocks.MELON));
	}

	private static void addTreeFarmables(IFarmTypeBuilder arboreal, boolean arboriculture) {
		arboreal.addWindfallFarmable(Items.OAK_SAPLING, FarmableSapling::new, builder -> builder.addWindfall(List.of(Items.APPLE, Items.STICK)));
		arboreal.addWindfallFarmable(Items.BIRCH_SAPLING, FarmableSapling::new, builder -> builder.addWindfall(Items.STICK));
		arboreal.addWindfallFarmable(Items.SPRUCE_SAPLING, FarmableSapling::new, builder -> builder.addWindfall(Items.STICK));
		arboreal.addWindfallFarmable(Items.JUNGLE_SAPLING, FarmableSapling::new, builder -> builder.addWindfall(List.of(Items.STICK, Items.COCOA_BEANS)));
		arboreal.addWindfallFarmable(Items.DARK_OAK_SAPLING, FarmableSapling::new, builder -> builder.addWindfall(Items.STICK));
		arboreal.addWindfallFarmable(Items.ACACIA_SAPLING, FarmableSapling::new, builder -> builder.addWindfall(Items.STICK));
		arboreal.addWindfallFarmable(Items.MANGROVE_PROPAGULE, FarmableMangroveTree::new, builder -> builder.addWindfall(List.of(Items.STICK, Items.MOSS_CARPET)));
		arboreal.addWindfallFarmable(Items.CHERRY_SAPLING, FarmableSapling::new, builder -> builder.addWindfall(Items.STICK));
		if (arboriculture) {
			arboreal.addFarmable(new FarmableGE());
		}
	}

	private static void addCropFarmables(IFarmTypeBuilder crops) {
		crops.addFarmable(new FarmableAgingCrop(Items.WHEAT_SEEDS, Blocks.WHEAT, new ItemStack(Items.WHEAT), CropBlock.AGE, 7, 0));
		crops.addFarmable(new FarmableAgingCrop(Items.POTATO, Blocks.POTATOES, new ItemStack(Items.POTATO), CropBlock.AGE, 7, 0));
		crops.addFarmable(new FarmableAgingCrop(Items.CARROT, Blocks.CARROTS, new ItemStack(Items.CARROT), CropBlock.AGE, 7, 0));
		crops.addFarmable(new FarmableAgingCrop(Items.BEETROOT_SEEDS, Blocks.BEETROOTS, new ItemStack(Items.BEETROOT), BeetrootBlock.AGE, 3, 0));
	}

	private static ItemStack createSaplingStack(IGenome genome) {
		ItemStack stack = new ItemStack(ArboricultureItems.SAPLING.item());
		stack.set(ArboricultureDataComponents.TREE_GENOME.type(), genome);
		return stack;
	}
}
