package com.leon1236.reforestry.apiculture.genetics;

import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.plugin.IApicultureRegistration;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;
import com.leon1236.reforestry.apiculture.items.EnumPollenCluster;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.core.items.EnumCraftingMaterial;

public final class DefaultBeeSpecies {
    private DefaultBeeSpecies() {
    }

    public static void register(IApicultureRegistration registration) {
        registration.registerSpecies(ReForestry.id("bee_forest"), "apis", "nigrocincta", true, 0x19d0ec)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWER);
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_3);
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
                });

        registration.registerSpecies(ReForestry.id("bee_meadows"), "apis", "florea", true, 0xef131e)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWER);
                    genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
                });

        registration.registerSpecies(ReForestry.id("bee_common"), "apis", "cerana", true, 0xb2b2b2)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.35f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_marshy"), 15f);
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_meadows"), 15f);
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_modest"), 15f);
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_savanna"), 15f);
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_tropical"), 15f);
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_valiant"), 15f);
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_wintry"), 15f);
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_lush"), 15f);
            mutations.add(ReForestry.id("bee_forest"), ReForestry.id("bee_aquatic"), 15f);
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_meadows"), 15f);
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_modest"), 15f);
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_savanna"), 15f);
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_tropical"), 15f);
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_valiant"), 15f);
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_wintry"), 15f);
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_lush"), 15f);
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_aquatic"), 15f);
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_modest"), 15f);
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_savanna"), 15f);
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_tropical"), 15f);
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_valiant"), 15f);
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_wintry"), 15f);
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_lush"), 15f);
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_aquatic"), 15f);
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_savanna"), 15f);
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_tropical"), 15f);
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_valiant"), 15f);
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_wintry"), 15f);
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_lush"), 15f);
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_aquatic"), 15f);
            mutations.add(ReForestry.id("bee_savanna"), ReForestry.id("bee_tropical"), 15f);
            mutations.add(ReForestry.id("bee_savanna"), ReForestry.id("bee_valiant"), 15f);
            mutations.add(ReForestry.id("bee_savanna"), ReForestry.id("bee_wintry"), 15f);
            mutations.add(ReForestry.id("bee_savanna"), ReForestry.id("bee_lush"), 15f);
            mutations.add(ReForestry.id("bee_savanna"), ReForestry.id("bee_aquatic"), 15f);
            mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_valiant"), 15f);
            mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_wintry"), 15f);
            mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_lush"), 15f);
            mutations.add(ReForestry.id("bee_tropical"), ReForestry.id("bee_aquatic"), 15f);
            mutations.add(ReForestry.id("bee_valiant"), ReForestry.id("bee_wintry"), 15f);
            mutations.add(ReForestry.id("bee_valiant"), ReForestry.id("bee_lush"), 15f);
            mutations.add(ReForestry.id("bee_valiant"), ReForestry.id("bee_aquatic"), 15f);
            mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_lush"), 15f);
            mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_aquatic"), 15f);
            mutations.add(ReForestry.id("bee_lush"), ReForestry.id("bee_aquatic"), 15f);
        });

        registration.registerSpecies(ReForestry.id("bee_cultivated"), "apis", "mellifera", true, 0x5734ec)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.4f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_FAST);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_forest"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_marshy"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_meadows"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_modest"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_savanna"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_tropical"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_valiant"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_wintry"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_lush"), 12f);
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_aquatic"), 12f);
        });

        registration.registerSpecies(ReForestry.id("bee_noble"), "probapis", "nobilis", false, 0xec9a19)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.DRIPPING).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_cultivated"), 10f);
        });

        registration.registerSpecies(ReForestry.id("bee_majestic"), "probapis", "regalis", true, 0x7f0000)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.DRIPPING).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTENED);
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_4);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_noble"), ReForestry.id("bee_cultivated"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_imperial"), "probapis", "imperatorius", false, 0xa3e02f)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.DRIPPING).item(), 0.2f)
                .addProduct(ApicultureItems.ROYAL_JELLY.item(), 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_BEATIFIC);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_noble"), ReForestry.id("bee_majestic"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_diligent"), "industrapis", "sedulus", false, 0xc219ec)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.STRINGY).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_common"), ReForestry.id("bee_cultivated"), 10f);
        });

        registration.registerSpecies(ReForestry.id("bee_unweary"), "industrapis", "assiduus", true, 0x19ec5a)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.STRINGY).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTENED);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_diligent"), ReForestry.id("bee_cultivated"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_industrious"), "industrapis", "industria", false, 0xffffff)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.STRINGY).item(), 0.2f)
                .addProduct(ApicultureItems.POLLEN_CLUSTER.get(EnumPollenCluster.NORMAL).item(), 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_FAST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_diligent"), ReForestry.id("bee_unweary"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_sinister"), "diapis", "caecus", false, 0xb3d5e4)
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0x9a2323)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.45f)
                .addProduct(CoreItems.CRAFTING_MATERIALS.get(EnumCraftingMaterial.PHOSPHOR).item(), 2, 0.30f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_AGGRESSIVE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_cultivated"), ReForestry.id("bee_modest"), 60f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
            mutations.add(ReForestry.id("bee_cultivated"), ReForestry.id("bee_tropical"), 60f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
        });

        registration.registerSpecies(ReForestry.id("bee_fiendish"), "diapis", "diabolus", true, 0xd7bee5)
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0x9a2323)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.55f)
                .addProduct(CoreItems.ASH.item(), 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_AGGRESSIVE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_sinister"), ReForestry.id("bee_cultivated"), 40f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
            mutations.add(ReForestry.id("bee_sinister"), ReForestry.id("bee_modest"), 40f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
            mutations.add(ReForestry.id("bee_sinister"), ReForestry.id("bee_tropical"), 40f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
        });

        registration.registerSpecies(ReForestry.id("bee_demonic"), "diapis", "draco", false, 0xf4e400)
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0x9a2323)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.45f)
                .addProduct(Items.GLOWSTONE_DUST, 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_IGNITION);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_sinister"), ReForestry.id("bee_fiendish"), 25f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER);
        });

        registration.registerSpecies(ReForestry.id("bee_modest"), "modapis", "modicus", false, 0xc5be86)
                .setTemperature(TemperatureType.HOT)
                .setHumidity(HumidityType.ARID)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.PARCHED).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                });

        registration.registerSpecies(ReForestry.id("bee_frugal"), "modapis", "permodestus", true, 0xe8dcb1)
                .setTemperature(TemperatureType.HOT)
                .setHumidity(HumidityType.ARID)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.PARCHED).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_sinister"), 16f);
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_fiendish"), 10f);
        });

        registration.registerSpecies(ReForestry.id("bee_austere"), "modapis", "correpere", false, 0xfffac2)
                .setTemperature(TemperatureType.HOT)
                .setHumidity(HumidityType.ARID)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.PARCHED).item(), 0.2f)
                .addSpecialty(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.POWDERY).item(), 0.5f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_2);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_CREEPER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_modest"), ReForestry.id("bee_frugal"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_tropical"), "caldapis", "mendelia", false, 0x378020)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                });

        registration.registerSpecies(ReForestry.id("bee_exotic"), "caldapis", "darwini", true, 0x304903)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_austere"), ReForestry.id("bee_tropical"), 12f);
        });

        registration.registerSpecies(ReForestry.id("bee_edenic"), "caldapis", "humboldti", false, 0x393d0d)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_EXPLORATION);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_exotic"), ReForestry.id("bee_tropical"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_monastic"), "monapis", "monachus", false, 0x42371c)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.WHEATEN).item(), 0.3f)
                .addSpecialty(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MELLOW).item(), 0.1f);

        registration.registerSpecies(ReForestry.id("bee_secluded"), "monapis", "contractus", true, 0x7b6634)
                .addSpecialty(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MELLOW).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_FASTEST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_monastic"), ReForestry.id("bee_austere"), 12f);
        });

        registration.registerSpecies(ReForestry.id("bee_hermitic"), "monapis", "anachoreta", false, 0xffd46c)
                .setGlint(true)
                .addSpecialty(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MELLOW).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_FASTEST);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_REPULSION);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_monastic"), ReForestry.id("bee_secluded"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_shulking"), "finapis", "shurukui", false, 0x896d74)
                .setTemperature(TemperatureType.COLD)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0xd9de9e)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MYSTERIOUS).item(), 0.2f)
                .addSpecialty(Items.SHULKER_SHELL, 0.015f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_ASCENSION);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                });

        registration.registerSpecies(ReForestry.id("bee_ended"), "finapis", "mikui", false, 0xe079fa)
                .setTemperature(TemperatureType.COLD)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0xd9de9e)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MYSTERIOUS).item(), 0.3f);

        registration.registerSpecies(ReForestry.id("bee_spectral"), "finapis", "idolum", true, 0xa98bed)
                .setTemperature(TemperatureType.COLD)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0xd9de9e)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MYSTERIOUS).item(), 0.5f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_REANIMATION);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_hermitic"), ReForestry.id("bee_ended"), 4f);
        });

        registration.registerSpecies(ReForestry.id("bee_phantasmal"), "finapis", "lemur", false, 0xcc00fa)
                .setTemperature(TemperatureType.COLD)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0xd9de9e)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MYSTERIOUS).item(), 0.4f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGEST);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_RESURRECTION);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_spectral"), ReForestry.id("bee_ended"), 2f);
        });

        registration.registerSpecies(ReForestry.id("bee_wintry"), "coagapis", "brumalis", false, 0xa0ffc8)
                .setTemperature(TemperatureType.ICY)
                .setBodyColor(0xdaf5f3)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.FROZEN).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_4);
                });

        registration.registerSpecies(ReForestry.id("bee_icy"), "coagapis", "coagulis", true, 0xa0ffff)
                .setTemperature(TemperatureType.ICY)
                .setBodyColor(0xdaf5f3)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.FROZEN).item(), 0.2f)
                .addProduct(CoreItems.CRAFTING_MATERIALS.get(EnumCraftingMaterial.ICE_SHARD).item(), 0.20f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_industrious"), ReForestry.id("bee_wintry"), 12f);
        });

        registration.registerSpecies(ReForestry.id("bee_glacial"), "coagapis", "glacialis", false, 0xefffff)
                .setTemperature(TemperatureType.ICY)
                .setBodyColor(0xdaf5f3)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.FROZEN).item(), 0.2f)
                .addProduct(CoreItems.CRAFTING_MATERIALS.get(EnumCraftingMaterial.ICE_SHARD).item(), 0.40f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_icy"), ReForestry.id("bee_wintry"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_marshy"), "paludapis", "adorasti", true, 0x546626)
                .setHumidity(HumidityType.DAMP)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MOSSY).item(), 0.3f);

        registration.registerSpecies(ReForestry.id("bee_miry"), "paludapis", "humidium", true, 0x92af42)
                .setHumidity(HumidityType.DAMP)
                .setAuthority("MysteriousAges")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MOSSY).item(), 0.36f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_4);
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
                    genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_noble"), 15f);
        });

        registration.registerSpecies(ReForestry.id("bee_boggy"), "paludapis", "paluster", true, 0x698948)
                .setHumidity(HumidityType.DAMP)
                .setAuthority("MysteriousAges")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MOSSY).item(), 0.39f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_MYCOPHILIC);
                    genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGER);
                    genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_marshy"), ReForestry.id("bee_miry"), 9f);
        });

        registration.registerSpecies(ReForestry.id("bee_savanna"), "afrapis", "scutellata", true, 0xb04e0f)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.ARID)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.PARCHED).item(), 0.2f)
                .addSpecialty(Items.RED_SAND, 0.1f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGE);
                });

        registration.registerSpecies(ReForestry.id("bee_argil"), "afrapis", "argillata", true, 0x96afd2)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.ARID)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.KAOLIN).item(), 0.3f)
                .addSpecialty(Items.RED_SAND, 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGE);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_SIFTER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_savanna"), ReForestry.id("bee_diligent"), 15f);
        });

        registration.registerSpecies(ReForestry.id("bee_pride"), "afrapis", "rafikii", true, 0x650021)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.ARID)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.KAOLIN).item(), 0.2f)
                .addSpecialty(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MELLOW).item(), 0.1f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTENED);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGER);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_HAKUNA_MATATA);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_savanna"), ReForestry.id("bee_argil"), 9f).restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.SHATTERED_SAVANNA);
        });

        registration.registerSpecies(ReForestry.id("bee_vindictive"), "punapis", "ultio", true, 0xeafff3)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.ARID)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.PARCHED).item(), 0.25f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_savanna"), ReForestry.id("bee_common"), 12f);
        });

        registration.registerSpecies(ReForestry.id("bee_vengeful"), "punapis", "punire", true, 0xc2de00)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.ARID)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.PARCHED).item(), 0.4f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_vindictive"), ReForestry.id("bee_cultivated"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_avenging"), "punapis", "hostimentum", true, 0xddff00)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.ARID)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.PARCHED).item(), 0.4f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGEST);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_RADIOACTIVE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_vindictive"), ReForestry.id("bee_vengeful"), 4f);
        });

        registration.registerSpecies(ReForestry.id("bee_steadfast"), "herapis", "legio", false, 0x4d2b15)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.COCOA).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
                });

        registration.registerSpecies(ReForestry.id("bee_valiant"), "herapis", "centurio", true, 0x626bdd)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.COCOA).item(), 0.3f)
                .addSpecialty(Items.SUGAR, 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
                    genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
                });

        registration.registerSpecies(ReForestry.id("bee_heroic"), "herapis", "kraphti", false, 0xb3d5e4)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.COCOA).item(), 0.4f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_HEROIC);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_steadfast"), ReForestry.id("bee_valiant"), 6f).restrictBiomeType(net.minecraft.tags.BiomeTags.IS_FOREST);
        });

        registration.registerSpecies(ReForestry.id("bee_lush"), "troglobites", "atvatabari", true, 0x70922d)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.35f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_NORMAL);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWER);
                });

        registration.registerSpecies(ReForestry.id("bee_verdant"), "troglobites", "lidenbrocki", true, 0x1c5b3a)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.45f)
                .addSpecialty(Items.SMALL_DRIPLEAF, 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_lush"), ReForestry.id("bee_valiant"), 10f).addMutationCondition(new com.leon1236.reforestry.core.genetics.mutations.MutationConditionCaveDwelling());
        });

        registration.registerSpecies(ReForestry.id("bee_luxuriant"), "troglobites", "verni", false, 0xeb8931)
                .setTemperature(TemperatureType.WARM)
                .setHumidity(HumidityType.DAMP)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.55f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_FAST);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_GLOW_BERRY_GROW);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_lush"), ReForestry.id("bee_verdant"), 8f).addMutationCondition(new com.leon1236.reforestry.core.genetics.mutations.MutationConditionCaveDwelling());
        });

        registration.registerSpecies(ReForestry.id("bee_kleptoplastic"), "phytapis", "vitaraptor", false, 0xffc987)
                .setBodyColor(0x64e986)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_luxuriant"), ReForestry.id("bee_monastic"), 12f);
        });

        registration.registerSpecies(ReForestry.id("bee_photosynthetic"), "phytapis", "phytomimus", true, 0xb6c9ff)
                .setBodyColor(0x64e986)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.4f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_FAST);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_kleptoplastic"), ReForestry.id("bee_luxuriant"), 8f);
            mutations.add(ReForestry.id("bee_kleptoplastic"), ReForestry.id("bee_monastic"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_autotrophic"), "phytapis", "solaris", false, 0xfff5ec)
                .setBodyColor(0x64e986)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGEST);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_FASTER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_kleptoplastic"), ReForestry.id("bee_photosynthetic"), 4f);
        });

        registration.registerSpecies(ReForestry.id("bee_aquatic"), "spongiforma", "squarepantsii", true, 0x3f76e4)
                .setTemperature(TemperatureType.WARM)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SPONGE).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTEST);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.CORAL, false));
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_4);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_MIASMIC);
                });

        registration.registerSpecies(ReForestry.id("bee_pirate"), "spongiforma", "pirata", true, 0x3f605b)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SPONGE).item(), 0.2f)
                .addSpecialty(Items.GOLD_NUGGET, 0.15f)
                .addSpecialty(Items.LAPIS_LAZULI, 0.02f)
                .addSpecialty(Items.EMERALD, 0.005f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTER);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.SEA, false));
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.CATHEMERAL, false));
                });

        registration.registerSpecies(ReForestry.id("bee_prismatic"), "spongiforma", "orichalcus", false, 0x539882)
                .setTemperature(TemperatureType.WARM)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SPONGE).item(), 0.2f)
                .addSpecialty(Items.PRISMARINE_SHARD, 0.4f)
                .addSpecialty(Items.PRISMARINE_CRYSTALS, 0.05f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORT);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER);
                    genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.CORAL, false));
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_GUARDIAN);
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_aquatic"), ReForestry.id("bee_pirate"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_abyssal"), "spongiforma", "stygii", false, 0x050533)
                .setTemperature(TemperatureType.COLD)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SPONGE).item(), 0.2f)
                .addSpecialty(Items.GLOW_INK_SAC, 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.SEA, false));
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_DARKNESS);
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.NOCTURNAL, false));
                    genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_pirate"), ReForestry.id("bee_ended"), 40f).addMutationCondition(new com.leon1236.reforestry.core.genetics.mutations.MutationConditionCaveDwelling());
            mutations.add(ReForestry.id("bee_aquatic"), ReForestry.id("bee_ended"), 40f).addMutationCondition(new com.leon1236.reforestry.core.genetics.mutations.MutationConditionCaveDwelling());
            mutations.add(ReForestry.id("bee_pirate"), ReForestry.id("bee_shulking"), 60f).addMutationCondition(new com.leon1236.reforestry.core.genetics.mutations.MutationConditionCaveDwelling());
            mutations.add(ReForestry.id("bee_aquatic"), ReForestry.id("bee_shulking"), 60f).addMutationCondition(new com.leon1236.reforestry.core.genetics.mutations.MutationConditionCaveDwelling());
        });

        registration.registerSpecies(ReForestry.id("bee_embittered"), "irata", "irata", true, 0x894344)
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0x9a2323)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.45f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_AGGRESSIVE);
                });

        registration.registerSpecies(ReForestry.id("bee_spiteful"), "irata", "invida", false, 0xfeac6d)
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0x9a2323)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.55f)
                .addSpecialty(ApicultureItems.POLLEN_CLUSTER.get(EnumPollenCluster.NORMAL).item(), 0.05f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_FAST);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_AGGRESSIVE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_embittered"), ReForestry.id("bee_fiendish"), 12f);
        });

        registration.registerSpecies(ReForestry.id("bee_seething"), "irata", "sulphuri", false, 0xff8f00)
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0x9a2323)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.45f)
                .addProduct(Items.BLAZE_POWDER, 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_IGNITION);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_spiteful"), ReForestry.id("bee_embittered"), 8f);
        });

        registration.registerSpecies(ReForestry.id("bee_warped"), "irata", "corrumpata", true, 0x14b485)
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0x9a2323)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.15f)
                .addSpecialty(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.MYSTERIOUS).item(), 0.35f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_ELONGATED);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_PHASING);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_embittered"), ReForestry.id("bee_ended"), 40f).restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.WARPED_FOREST);
            mutations.add(ReForestry.id("bee_spiteful"), ReForestry.id("bee_ended"), 40f).restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.WARPED_FOREST);
            mutations.add(ReForestry.id("bee_embittered"), ReForestry.id("bee_shulking"), 40f).restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.WARPED_FOREST);
            mutations.add(ReForestry.id("bee_spiteful"), ReForestry.id("bee_shulking"), 40f).restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.WARPED_FOREST);
        });

        registration.registerSpecies(ReForestry.id("bee_zombified"), "abominatio", "inmortui", true, 0x698e45)
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setBodyColor(0xe4686a)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SIMMERING).item(), 0.2f)
                .addProduct(Items.GOLD_NUGGET, 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_IMMORTAL);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWEST);
                    genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.NETHER, false));
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_3);
                    genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
                    genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
                });

        registration.registerSpecies(ReForestry.id("bee_sculk"), "abominatio", "alieni", true, 0xd1d6b6)
                .setBodyColor(0x05625d)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SCULKEN).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGER);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_NORMAL);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWEST);
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
                    genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.SCULK, false));
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_SCULK);
                    genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGER);
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
                    genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
                    genome.set(BeeChromosomes.CAVE_DWELLING, AlleleManager.INSTANCE.booleanAllele(true, false));
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_abyssal"), ReForestry.id("bee_hermitic"), 6f).restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.DEEP_DARK);
        });

        registration.registerSpecies(ReForestry.id("bee_rural"), "rustapis", "rustico", false, 0xfeff8f)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.WHEATEN).item(), 0.2f)
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_diligent"), 12f).restrictBiomeType(net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags.IS_PLAINS);
        });

        registration.registerSpecies(ReForestry.id("bee_farmerly"), "rustapis", "arator", true, 0xd39728)
                .setAuthority("MysteriousAges")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.WHEATEN).item(), 0.27f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_rural"), ReForestry.id("bee_unweary"), 10f).restrictBiomeType(net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags.IS_PLAINS);
        });

        registration.registerSpecies(ReForestry.id("bee_agrarian"), "rustapis", "agrarius", true, 0xffca75)
                .setBodyColor(0xffe047)
                .setGlint(true)
                .setAuthority("MysteriousAges")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.WHEATEN).item(), 0.35f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_2);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_FERTILE);
                    genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_farmerly"), ReForestry.id("bee_industrious"), 6f).restrictBiomeType(net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags.IS_PLAINS);
        });

        registration.registerSpecies(ReForestry.id("bee_primeval"), "reliquia", "antiqua", true, 0x653f33)
                .setTemperature(TemperatureType.WARM)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.VINTAGE).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONG);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOW);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_2);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_anachrone"), ReForestry.id("bee_steadfast"), 15f);
        });

        registration.registerSpecies(ReForestry.id("bee_anachrone"), "reliquia", "tempuraptor", false, 0x55ffff)
                .setTemperature(TemperatureType.WARM)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.VINTAGE).item(), 0.2f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_LONGEST);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWEST);
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_CHRONOPHAGE);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_relic"), ReForestry.id("bee_steadfast"), 10f);
        });

        registration.registerSpecies(ReForestry.id("bee_relic"), "reliquia", "elizabethii", false, 0xff55ff)
                .setTemperature(TemperatureType.WARM)
                .setGlint(true)
                .setAuthority("EnderiumSmith")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.VINTAGE).item(), 0.2f)
                .addSpecialty(ApicultureItems.ROYAL_JELLY.item(), 0.15f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_IMMORTAL);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWEST);
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_REJUVENATION);
                });

        registration.registerSpecies(ReForestry.id("bee_vanilla"), "bombus", "dinnerbonei", false, 0xedc343)
                .setAuthority("EnderiumSmith")
                .addProduct(Items.HONEYCOMB, 0.65f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.LIFESPAN, ForestryAlleles.LIFESPAN_SHORTENED);
                    genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWEST);
                    genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_AVERAGE);
                    genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_0);
                });

        registration.registerSpecies(ReForestry.id("bee_leporine"), "festapis", "lepus", false, 0xfeff8f)
                .setBodyColor(0x3cd757)
                .setSecret(true)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.SILKY).item(), 0.3f)
                .addProduct(Items.EGG, 0.1f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_EASTER);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_meadows"), ReForestry.id("bee_forest"), 10f).restrictDateRange(3, 29, 4, 15);
        });

        registration.registerSpecies(ReForestry.id("bee_merry"), "festapis", "feliciter", false, 0xffffff)
                .setTemperature(TemperatureType.ICY)
                .setBodyColor(0xd40000)
                .setSecret(true)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.FROZEN).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_SNOWING);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_forest"), 10f).restrictDateRange(12, 21, 12, 27);
        });

        registration.registerSpecies(ReForestry.id("bee_tipsy"), "festapis", "ebrius", false, 0xffffff)
                .setTemperature(TemperatureType.ICY)
                .setBodyColor(0xc219ec)
                .setSecret(true)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.FROZEN).item(), 0.3f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
                    genome.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_DRUNKARD);
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_wintry"), ReForestry.id("bee_meadows"), 10f).restrictDateRange(12, 27, 1, 2);
        });

        registration.registerSpecies(ReForestry.id("bee_tricky"), "festapis", "libita", false, 0x49413b)
                .setBodyColor(0xff6a00)
                .setSecret(true)
                .setGlint(true)
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item(), 0.4f)
                .addProduct(Items.COOKIE, 0.15f)
                .addSpecialty(Items.SKELETON_SKULL, 0.02f)
                .addSpecialty(Items.ZOMBIE_HEAD, 0.02f)
                .addSpecialty(Items.CREEPER_HEAD, 0.02f)
                .addSpecialty(Items.PLAYER_HEAD, 0.02f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
                    genome.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.GOURD, false));
                    genome.set(BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, false));
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_sinister"), ReForestry.id("bee_common"), 10f).restrictDateRange(10, 15, 11, 3);
        });

        registration.registerSpecies(ReForestry.id("bee_patriotic"), "festapis", "americanus", true, 0x0a3161)
                .setBodyColor(0xb31942)
                .setStripesColor(0xffffff)
                .setSecret(true)
                .setAuthority("TheDarkColour")
                .addProduct(ApicultureItems.BEE_COMBS.get(EnumHoneyComb.POWDERY).item(), 0.45f)
                .setGenome(genome -> {
                    genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_2);
                    genome.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_UP_1);
                    genome.set(BeeChromosomes.TERRITORY, ForestryAlleles.TERRITORY_LARGEST);
                    genome.set(BeeChromosomes.ACTIVITY, AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false));
                })
        .addMutations(mutations -> {
            mutations.add(ReForestry.id("bee_rural"), ReForestry.id("bee_noble"), 15f).restrictDateRange(7, 1, 7, 17);
        });
    }
}
