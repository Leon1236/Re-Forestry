package com.leon1236.reforestry.arboriculture.genetics;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.VanillaWoodType;
import com.leon1236.reforestry.arboriculture.worldgen.FeatureSimpleTree;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public final class DefaultTreeSpecies {
    private DefaultTreeSpecies() {
    }

    public static void register(IArboricultureRegistration registration) {
        registration.registerSpecies(ReForestry.id("tree_oak"), "quercus", "robur", false, 0x48b518, VanillaWoodType.OAK)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.OAK))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.OAK).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.OAK).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(Blocks.OAK_LEAVES.getStateDefinition().getPossibleStates())
                .addVanillaSapling(Items.OAK_SAPLING)
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.APPLE, DefaultFruits.APPLE.isDominant()));
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
                });

        registration.registerSpecies(ReForestry.id("tree_birch"), "betula", "pendula", false, 0x80a755, VanillaWoodType.BIRCH)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.BIRCH))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.BIRCH).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.BIRCH).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(Blocks.BIRCH_LEAVES.getStateDefinition().getPossibleStates())
                .addVanillaSapling(Items.BIRCH_SAPLING)
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
                });

        registration.registerSpecies(ReForestry.id("tree_lime"), "tilia", "tomentosa", true, 0x5ea107, ForestryWoodType.LIME)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.LIME))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.LIME).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.LIME).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setRarity(0.005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOWER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_oak"), ReForestry.id("tree_birch"), 15f);
        });

        registration.registerSpecies(ReForestry.id("tree_hill_cherry"), "prunus", "cerasus", true, 0x84aa37, ForestryWoodType.HILL_CHERRY)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.SOUR_CHERRY))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.SOUR_CHERRY).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.SOUR_CHERRY).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setRarity(0.0015f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.CHERRY, DefaultFruits.CHERRY.isDominant()));
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_lime"), ReForestry.id("tree_oak"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_walnut"), "juglans", "regia", true, 0x798c55, ForestryWoodType.WALNUT)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.WALNUT))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.WALNUT).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.WALNUT).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.WALNUT, DefaultFruits.WALNUT.isDominant()));
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWER);
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_hill_cherry"), ReForestry.id("tree_dark_oak"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_chestnut"), "castanea", "sativa", true, 0x7e8e4d, ForestryWoodType.CHESTNUT)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.CHESTNUT))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.CHESTNUT).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.CHESTNUT).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.CHESTNUT, DefaultFruits.CHESTNUT.isDominant()));
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_walnut"), ReForestry.id("tree_lime"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_pear"), "pyrus", "communis", true, 0x448944, ForestryWoodType.PEAR)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.PEAR))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.PEAR).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.PEAR).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_AVERAGE);
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.PEAR, DefaultFruits.PEAR.isDominant()));
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_hill_cherry"), ReForestry.id("tree_oak"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_plum"), "prunus", "domestica", true, 0x589246, ForestryWoodType.PLUM)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.PLUM))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.PLUM).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.PLUM).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.PLUM, DefaultFruits.PLUM.isDominant()));
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_pear"), ReForestry.id("tree_hill_cherry"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_feijoa"), "feijoa", "sellowiana", true, 0x99baa4, ForestryWoodType.FEIJOA)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.FEIJOA))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.FEIJOA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.FEIJOA).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_AVERAGE);
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.FEIJOA, DefaultFruits.FEIJOA.isDominant()));
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWER);
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGHER);
                });

        registration.registerSpecies(ReForestry.id("tree_elm"), "ulmus", "glabra", true, 0xddfa52, ForestryWoodType.ELM)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.ELM))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.ELM).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.ELM).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_AVERAGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_lime"), ReForestry.id("tree_birch"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_maple"), "acer", "saccharum", true, 0xd4f425, ForestryWoodType.MAPLE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.MAPLE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.MAPLE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.MAPLE).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setRarity(0.0025f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_elm"), ReForestry.id("tree_oak"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_beech"), "fagus", "sylvatica", true, 0xad301a, ForestryWoodType.BEECH)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.BEECH))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.BEECH).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.BEECH).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_elm"), ReForestry.id("tree_lime"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_poplar"), "populus", "alba", true, 0xa3b8a5, ForestryWoodType.POPLAR)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.POPLAR))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.POPLAR).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.POPLAR).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALL);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_elm"), ReForestry.id("tree_birch"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_dark_oak"), "quercus", "velutina", false, 0x48b518, VanillaWoodType.DARK_OAK)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.DARK_OAK))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.DARK_OAK).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.DARK_OAK).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(Blocks.DARK_OAK_LEAVES.getStateDefinition().getPossibleStates())
                .addVanillaSapling(Items.DARK_OAK_SAPLING)
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Binnie")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
                });

        registration.registerSpecies(ReForestry.id("tree_willow"), "salix", "alba", true, 0xa3b8a5, ForestryWoodType.WILLOW)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.WILLOW))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.WILLOW).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.WILLOW).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.0025f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_poplar"), ReForestry.id("tree_dark_oak"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_cherry"), "prunus", "serrulata", false, 0xf7b9dc, VanillaWoodType.CHERRY)
                .setDecorativeLeaves(new ItemStack(Items.CHERRY_LEAVES))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.CHERRY_VANILLA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.CHERRY_VANILLA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(Blocks.CHERRY_LEAVES.getStateDefinition().getPossibleStates())
                .addVanillaSapling(Items.CHERRY_SAPLING)
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                    genome.set(TreeChromosomes.EFFECT, AlleleManager.INSTANCE.registryAllele(TreeEffect.BLOSSOMING, true));
                });

        registration.registerSpecies(ReForestry.id("tree_dogwood"), "cornus", "florida", true, 0xf4f4f4, ForestryWoodType.DOGWOOD)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.DOGWOOD))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.DOGWOOD).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.DOGWOOD).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALL);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_lime"), ReForestry.id("tree_cherry"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_jacaranda"), "jacaranda", "mimosofolia", true, 0xc18ffb, ForestryWoodType.JACARANDA)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.JACARANDA))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.JACARANDA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.JACARANDA).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALL);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_AVERAGE);
                });

        registration.registerSpecies(ReForestry.id("tree_ipe"), "handroanthus", "serratifolius", true, 0xfdd207, ForestryWoodType.IPE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.IPE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.IPE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.IPE).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_dogwood"), ReForestry.id("tree_teak"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_ginkgo"), "ginkgo", "bilboa", true, 0xfcd54a, ForestryWoodType.GINKGO)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.GINKGO))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.GINKGO).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.GINKGO).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_HIGHER);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
                });

        registration.registerSpecies(ReForestry.id("tree_spruce"), "picea", "mariana", false, 0x619961, VanillaWoodType.SPRUCE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.SPRUCE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.SPRUCE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.SPRUCE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(Blocks.SPRUCE_LEAVES.getStateDefinition().getPossibleStates())
                .addVanillaSapling(Items.SPRUCE_SAPLING)
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
                });

        registration.registerSpecies(ReForestry.id("tree_larch"), "larix", "decidua", true, 0x698f90, ForestryWoodType.LARCH)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.LARCH))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.LARCH).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.LARCH).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.COLD)
                .setRarity(0.0025f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_spruce"), ReForestry.id("tree_oak"), 15f);
        });

        registration.registerSpecies(ReForestry.id("tree_pine"), "picea", "ponderosa", true, 0xfeff8f, ForestryWoodType.PINE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.PINE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.PINE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.PINE).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.COLD)
                .setRarity(0.0025f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_larch"), ReForestry.id("tree_spruce"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_fir"), "abies", "balsamea", true, 0x395a39, ForestryWoodType.FIR)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.FIR))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.FIR).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.FIR).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_HIGH);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_AVERAGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_larch"), ReForestry.id("tree_oak"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_macrocarpa"), "hesperocyparis", "macrocarpa", true, 0x5d7121, ForestryWoodType.MACROCARPA)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.MACROCARPA))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.MACROCARPA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.MACROCARPA).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_pine"), ReForestry.id("tree_fir"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_sequoia"), "sequoia", "sempervirens", true, 0x418e71, ForestryWoodType.SEQUOIA)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.SEQUOIA))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.SEQUOIA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.SEQUOIA).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGEST);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_3);
                    genome.set(TreeChromosomes.FIREPROOF, AlleleManager.INSTANCE.booleanAllele(true, true));
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_pine"), ReForestry.id("tree_larch"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_giant_sequoia"), "sequoiadendron", "giganteum", true, 0x738434, ForestryWoodType.GIGANTEUM)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.GIANT_SEQUOIA))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.GIANT_SEQUOIA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.GIANT_SEQUOIA).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_GIGANTIC);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWEST);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWEST);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_4);
                    genome.set(TreeChromosomes.FIREPROOF, AlleleManager.INSTANCE.booleanAllele(true, true));
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_sequoia"), ReForestry.id("tree_ginkgo"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_pewen"), "araucaria", "araucana", true, 0x455419, ForestryWoodType.PEWEN)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.PEWEN))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.PEWEN).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.PEWEN).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGER);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_HIGH);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_macrocarpa"), ReForestry.id("tree_fir"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_kauri"), "agathis", "australis", true, 0x97af64, ForestryWoodType.KAURI)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.KAURI))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.KAURI).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.KAURI).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGEST);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_HIGH);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWEST);
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_3);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_macrocarpa"), ReForestry.id("tree_pine"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_jungle"), "theobroma", "cacao", false, 0x764952, VanillaWoodType.JUNGLE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.JUNGLE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.JUNGLE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.JUNGLE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(Blocks.JUNGLE_LEAVES.getStateDefinition().getPossibleStates())
                .addVanillaSapling(Items.JUNGLE_SAPLING)
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.COCOA, DefaultFruits.COCOA.isDominant()));
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGER);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
                });

        registration.registerSpecies(ReForestry.id("tree_teak"), "tectona", "grandis", true, 0xfeff8f, ForestryWoodType.TEAK)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.TEAK))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.TEAK).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.TEAK).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.0025f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_jungle"), ReForestry.id("tree_dark_oak"), 15f);
        });

        registration.registerSpecies(ReForestry.id("tree_kapok"), "ceiba", "pentandra", true, 0x89987b, ForestryWoodType.KAPOK)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.KAPOK))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.KAPOK).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.KAPOK).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_teak"), ReForestry.id("tree_jungle"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_balsa"), "ochroma", "pyramidale", true, 0x59ac00, ForestryWoodType.BALSA)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.BALSA))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.BALSA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.BALSA).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.0005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_HIGH);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_teak"), ReForestry.id("tree_birch"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_orange"), "citrus", "sinensis", true, 0x57ad3f, ForestryWoodType.ORANGE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.ORANGE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.ORANGE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.ORANGE).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_AVERAGE);
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.ORANGE, DefaultFruits.ORANGE.isDominant()));
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_lime"), ReForestry.id("tree_jungle"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_ebony"), "diospyros", "pentamera", true, 0xa2d24a, ForestryWoodType.EBONY)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.EBONY))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.EBONY).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.EBONY).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.0005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_3);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_balsa"), ReForestry.id("tree_teak"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_sipiri"), "chlorocardium", "rodiei", true, 0x678911, ForestryWoodType.GREENHEART)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.GREENHEART))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.GREENHEART).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.GREENHEART).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.0025f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_kapok"), ReForestry.id("tree_teak"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_lemon"), "citrus", "limon", true, 0x5c8429, ForestryWoodType.CITRUS)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.LEMON))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.LEMON).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.LEMON).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.LEMON, DefaultFruits.LEMON.isDominant()));
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLEST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_orange"), ReForestry.id("tree_lime"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_zebrawood"), "microberlinia", "brazzavillensis", true, 0xa2d24a, ForestryWoodType.ZEBRAWOOD)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.ZEBRANO))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.ZEBRANO).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.ZEBRANO).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.0005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWER);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_ebony"), ReForestry.id("tree_balsa"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_mahogany"), "swietenia", "macrophylla", true, 0x8ab154, ForestryWoodType.MAHOGANY)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.MAHOGANY))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.MAHOGANY).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.MAHOGANY).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.0005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_ebony"), ReForestry.id("tree_kapok"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_coconut"), "cocos", "nucifera", true, 0x4f750f, ForestryWoodType.COCONUT)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.COCONUT))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.COCONUT).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.COCONUT).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_AVERAGE);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.COCONUT, DefaultFruits.COCONUT.isDominant()));
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_HIGH);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_walnut"), ReForestry.id("tree_kapok"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_papaya"), "carica", "papaya", true, 0x74b225, ForestryWoodType.PAPAYA)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.PAPAYA))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.PAPAYA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.PAPAYA).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.PAPAYA, DefaultFruits.PAPAYA.isDominant()));
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWER);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_lemon"), ReForestry.id("tree_kapok"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_acacia"), "acacia", "aneura", false, 0x616101, VanillaWoodType.ACACIA)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.ACACIA_VANILLA))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.ACACIA_VANILLA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.ACACIA_VANILLA).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(Blocks.ACACIA_LEAVES.getStateDefinition().getPossibleStates())
                .addVanillaSapling(Items.ACACIA_SAPLING)
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Binnie");

        registration.registerSpecies(ReForestry.id("tree_desert_acacia"), "vachellia", "erioloba", true, 0x748c1c, ForestryWoodType.ACACIA_DESERT)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.CAMELTHORN))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.CAMELTHORN).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.CAMELTHORN).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.ARID)
                .setRarity(0.005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALL);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_acacia"), ReForestry.id("tree_jungle"), 15f);
        });

        registration.registerSpecies(ReForestry.id("tree_padauk"), "pterocarpus", "soyauxii", true, 0xd0df8c, ForestryWoodType.PADAUK)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.PADAUK))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.PADAUK).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.PADAUK).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setRarity(0.005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_desert_acacia"), ReForestry.id("tree_jungle"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_cocobolo"), "dalbergia", "retusa", true, 0x6aa17a, ForestryWoodType.COCOBOLO)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.COCOBOLO))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.COCOBOLO).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.COCOBOLO).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setRarity(0.0005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGEST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_desert_acacia"), ReForestry.id("tree_dark_oak"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_wenge"), "millettia", "laurentii", true, 0xada157, ForestryWoodType.WENGE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.WENGE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.WENGE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.WENGE).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWEST);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_2);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_desert_acacia"), ReForestry.id("tree_acacia"), 10f);
        });

        registration.registerSpecies(ReForestry.id("tree_mahoe"), "talipariti", "elatum", true, 0xa0ba1b, ForestryWoodType.MAHOE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.MAHOE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.MAHOE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.MAHOE).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setRarity(0.000005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALL);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_HIGH);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOWEST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_cocobolo"), ReForestry.id("tree_desert_acacia"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_baobab"), "adansonia", "grandidieri", true, 0xfeff8f, ForestryWoodType.BAOBAB)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.BAOBAB))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.BAOBAB).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.BAOBAB).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_LARGE);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOWER);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_SLOW);
                    genome.set(TreeChromosomes.GIRTH, ForestryAlleles.GIRTH_3);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_padauk"), ReForestry.id("tree_wenge"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_date"), "phoenix", "dactylifera", true, 0xcbcd79, ForestryWoodType.PALM)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.DATE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.DATE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.DATE).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setRarity(0.005f)
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.DATES, DefaultFruits.DATES.isDominant()));
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOW);
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_LOW);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_AVERAGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_cocobolo"), ReForestry.id("tree_hill_cherry"), 5f);
        });

        registration.registerSpecies(ReForestry.id("tree_olive"), "olea", "europaea", true, 0xb7b792, ForestryWoodType.OLIVE)
                .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.OLIVE))
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.OLIVE).block().getStateDefinition().getPossibleStates())
                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.OLIVE).block().getStateDefinition().getPossibleStates())
                .setTreeFeature(FeatureSimpleTree::new)
                .setAuthority("Spear")
                .setGenome(genome -> {
                    genome.set(TreeChromosomes.HEIGHT, ForestryAlleles.HEIGHT_SMALLER);
                    genome.set(TreeChromosomes.SAPPINESS, ForestryAlleles.SAPPINESS_LOW);
                    genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FAST);
                    genome.set(TreeChromosomes.FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.OLIVE, DefaultFruits.OLIVE.isDominant()));
                    genome.set(TreeChromosomes.YIELD, ForestryAlleles.YIELD_AVERAGE);
                    genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_LOWER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("tree_wenge"), ReForestry.id("tree_hill_cherry"), 5f);
        });
    }
}
