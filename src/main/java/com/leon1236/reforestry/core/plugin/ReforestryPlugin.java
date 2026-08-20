package com.leon1236.reforestry.core.plugin;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.circuits.ForestryCircuitLayouts;
import com.leon1236.reforestry.api.circuits.ForestryCircuitSocketTypes;
import com.leon1236.reforestry.api.plugin.ICircuitRegistration;
import com.leon1236.reforestry.api.plugin.IApicultureRegistration;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.apiculture.features.ApicultureEffects;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.core.circuits.EnumElectronTube;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.factory.circuits.CircuitMachineUpgrade;
import com.leon1236.reforestry.apiculture.genetics.DefaultBeeSpecies;
import com.leon1236.reforestry.apiculture.genetics.JubilanceFactory;
import com.leon1236.reforestry.apiculture.genetics.effects.AggressiveBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.AgingBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.AscensionBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.CreeperBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.DummyBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.ExplorationBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.FertileBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.FungificationBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.GlacialBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.GlowBerryGrowEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.GuardianBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.HeroicBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.IgnitionBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.MisanthropeBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.PhasingBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.PotionBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.PotionBeeEffectExclusive;
import com.leon1236.reforestry.apiculture.genetics.effects.RadioactiveBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.RepulsionBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.ResurrectionBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.SculkSpreadBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.SifterBeeEffect;
import com.leon1236.reforestry.apiculture.genetics.effects.SnowingBeeEffect;
import com.leon1236.reforestry.apiculture.hives.HiveDefinition;
import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;
import com.leon1236.reforestry.arboriculture.genetics.DefaultTreeSpecies;

import net.minecraft.world.effect.MobEffects;

public final class ReforestryPlugin implements IForestryPlugin {
    @Override
    public Identifier id() {
        return ReForestry.id("base");
    }

    @Override
    public void registerApiculture(IApicultureRegistration registration) {
        BeeManager.jubilanceFactory = new JubilanceFactory();
        registerBeeEffects(registration);
        DefaultBeeSpecies.register(registration);

        Supplier<List<ItemStack>> honeyComb = comb(EnumHoneyComb.HONEY);
        Supplier<List<ItemStack>> parchedComb = comb(EnumHoneyComb.PARCHED);
        Supplier<List<ItemStack>> silkyComb = comb(EnumHoneyComb.SILKY);
        Supplier<List<ItemStack>> mysteriousComb = comb(EnumHoneyComb.MYSTERIOUS);
        Supplier<List<ItemStack>> frozenComb = comb(EnumHoneyComb.FROZEN);
        Supplier<List<ItemStack>> mossyComb = comb(EnumHoneyComb.MOSSY);
        Supplier<List<ItemStack>> spongeComb = comb(EnumHoneyComb.SPONGE);
        Supplier<List<ItemStack>> simmerComb = comb(EnumHoneyComb.SIMMERING);

        registration.registerHive(ReForestry.id("bee_forest"), HiveDefinition.FOREST)
                .addDrop(0.80, ReForestry.id("bee_forest"), honeyComb, 0.7f)
                .addDrop(0.08, ReForestry.id("bee_valiant"), honeyComb);

        registration.registerHive(ReForestry.id("bee_meadows"), HiveDefinition.MEADOWS)
                .addDrop(0.80, ReForestry.id("bee_meadows"), honeyComb, 0.7f)
                .addDrop(0.03, ReForestry.id("bee_valiant"), honeyComb);

        registration.registerHive(ReForestry.id("bee_modest"), HiveDefinition.DESERT)
                .addDrop(0.80, ReForestry.id("bee_modest"), parchedComb, 0.7f)
                .addDrop(0.03, ReForestry.id("bee_valiant"), parchedComb);

        registration.registerHive(ReForestry.id("bee_tropical"), HiveDefinition.JUNGLE)
                .addDrop(0.80, ReForestry.id("bee_tropical"), silkyComb, 0.7f)
                .addDrop(0.03, ReForestry.id("bee_valiant"), silkyComb);

        registration.registerHive(ReForestry.id("bee_ended"), HiveDefinition.END)
                .addDrop(0.90, ReForestry.id("bee_ended"), mysteriousComb, 0.7f);

        registration.registerHive(ReForestry.id("bee_wintry"), HiveDefinition.SNOW)
                .addDrop(0.80, ReForestry.id("bee_wintry"), frozenComb, 0.5f)
                .addDrop(0.03, ReForestry.id("bee_valiant"), frozenComb);

        registration.registerHive(ReForestry.id("bee_marshy"), HiveDefinition.SWAMP)
                .addDrop(0.80, ReForestry.id("bee_marshy"), mossyComb, 0.7f)
                .addDrop(0.03, ReForestry.id("bee_valiant"), mossyComb);

        registration.registerHive(ReForestry.id("bee_savanna"), HiveDefinition.SAVANNA)
                .addDrop(0.80, ReForestry.id("bee_savanna"), parchedComb, 0.7f)
                .addDrop(0.03, ReForestry.id("bee_valiant"), parchedComb);

        registration.registerHive(ReForestry.id("bee_lush"), HiveDefinition.LUSH)
                .addDrop(0.80, ReForestry.id("bee_lush"), honeyComb, 0.5f)
                .addDrop(0.08, ReForestry.id("bee_valiant"), honeyComb);

        registration.registerHive(ReForestry.id("bee_aquatic"), HiveDefinition.AQUATIC)
                .addDrop(0.80, ReForestry.id("bee_aquatic"), spongeComb, 0.4f)
                .addDrop(0.03, ReForestry.id("bee_valiant"), spongeComb);

        registration.registerHive(ReForestry.id("bee_embittered"), HiveDefinition.NETHER)
                .addDrop(0.80, ReForestry.id("bee_embittered"), simmerComb, 0.7f);

        registration.addVillageBee(ReForestry.id("bee_forest"), false);
        registration.addVillageBee(ReForestry.id("bee_meadows"), false);
        registration.addVillageBee(ReForestry.id("bee_modest"), false);
        registration.addVillageBee(ReForestry.id("bee_marshy"), false);
        registration.addVillageBee(ReForestry.id("bee_wintry"), false);
        registration.addVillageBee(ReForestry.id("bee_tropical"), false);
        registration.addVillageBee(ReForestry.id("bee_savanna"), false);

        registration.addVillageBee(ReForestry.id("bee_forest"), true, Map.of(
                BeeChromosomes.TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(true, true)));
        registration.addVillageBee(ReForestry.id("bee_common"), true, Map.of(
                BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1,
                BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1));
        registration.addVillageBee(ReForestry.id("bee_valiant"), true);
    }

    private static void registerBeeEffects(IApicultureRegistration apiculture) {
        apiculture.registerBeeEffect(ForestryBeeEffects.NONE, DummyBeeEffect.NONE);
        apiculture.registerBeeEffect(ForestryBeeEffects.AGGRESSIVE, new AggressiveBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.HEROIC, new HeroicBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.BEATIFIC, new PotionBeeEffect(ForestryBeeEffects.BEATIFIC, false, MobEffects.REGENERATION, 100));
        apiculture.registerBeeEffect(ForestryBeeEffects.MIASMIC, new PotionBeeEffect(ForestryBeeEffects.MIASMIC, false, MobEffects.POISON, 600, 100, 0.1f));
        apiculture.registerBeeEffect(ForestryBeeEffects.MISANTHROPE, new MisanthropeBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.GLACIAL, new GlacialBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.RADIOACTIVE, new RadioactiveBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.CREEPER, new CreeperBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.IGNITION, new IgnitionBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.EXPLORATION, new ExplorationBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.EASTER, new DummyBeeEffect(ForestryBeeEffects.EASTER, true));
        apiculture.registerBeeEffect(ForestryBeeEffects.SNOWING, new SnowingBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.DRUNKARD, new PotionBeeEffect(ForestryBeeEffects.DRUNKARD, false, MobEffects.NAUSEA, 100));
        apiculture.registerBeeEffect(ForestryBeeEffects.REANIMATION, new ResurrectionBeeEffect(ForestryBeeEffects.REANIMATION, ResurrectionBeeEffect.getReanimationList()));
        apiculture.registerBeeEffect(ForestryBeeEffects.RESURRECTION, new ResurrectionBeeEffect(ForestryBeeEffects.RESURRECTION, ResurrectionBeeEffect.getResurrectionList()));
        apiculture.registerBeeEffect(ForestryBeeEffects.REPULSION, new RepulsionBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.FERTILE, new FertileBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.MYCOPHILIC, new FungificationBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.SIFTER, new SifterBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.HAKUNA_MATATA, new PotionBeeEffectExclusive(
                ForestryBeeEffects.HAKUNA_MATATA, false, ApicultureEffects.HAKUNA_MATATA, 20 * 60 * 3, 100, 1.0f, ApicultureEffects.MATATA));
        apiculture.registerBeeEffect(ForestryBeeEffects.GLOW_BERRY_GROW, new GlowBerryGrowEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.REJUVENATION, new AgingBeeEffect(ForestryBeeEffects.REJUVENATION, false, false));
        apiculture.registerBeeEffect(ForestryBeeEffects.CHRONOPHAGE, new AgingBeeEffect(ForestryBeeEffects.CHRONOPHAGE, false, true));
        apiculture.registerBeeEffect(ForestryBeeEffects.GUARDIAN, new GuardianBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.PHASING, new PhasingBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.ASCENSION, new AscensionBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.SCULK, new SculkSpreadBeeEffect());
        apiculture.registerBeeEffect(ForestryBeeEffects.DARKNESS, new PotionBeeEffect(ForestryBeeEffects.DARKNESS, false, MobEffects.DARKNESS, 150));
    }

    private static Supplier<List<ItemStack>> comb(EnumHoneyComb type) {
        return () -> List.of(new ItemStack(ApicultureItems.BEE_COMBS.get(type).item()));
    }

    @Override
    public void registerArboriculture(IArboricultureRegistration registration) {
        DefaultTreeSpecies.register(registration);
        registration.registerCharcoalPitWall(Blocks.CLAY, 3);
        registration.registerCharcoalPitWall(Blocks.END_STONE, 6);
        registration.registerCharcoalPitWall(Blocks.END_STONE_BRICKS, 6);
        registration.registerCharcoalPitWall(Blocks.DIRT, 2);
        registration.registerCharcoalPitWall(Blocks.GRAVEL, 1);
        registration.registerCharcoalPitWall(Blocks.NETHERRACK, 3);
    }

    @Override
    public void registerCircuits(ICircuitRegistration circuits) {
        circuits.registerLayout(ForestryCircuitLayouts.MACHINE_UPGRADE, ForestryCircuitSocketTypes.MACHINE);
        circuits.registerCircuit(ForestryCircuitLayouts.MACHINE_UPGRADE,
                new ItemStack(CoreItems.ELECTRON_TUBES.get(EnumElectronTube.BLAZE).item()),
                new CircuitMachineUpgrade("machine.speed.boost.1", 0.125, 0.05f, 1.0f));
        circuits.registerCircuit(ForestryCircuitLayouts.MACHINE_UPGRADE,
                new ItemStack(CoreItems.ELECTRON_TUBES.get(EnumElectronTube.GOLD).item()),
                new CircuitMachineUpgrade("machine.efficiency.1", 0, -0.10f, 1.0f));
        circuits.registerCircuit(ForestryCircuitLayouts.MACHINE_UPGRADE,
                new ItemStack(CoreItems.ELECTRON_TUBES.get(EnumElectronTube.AMBER).item()),
                new CircuitMachineUpgrade("machine.fortune.1", 0, 0.05f, 1.25f));
    }
}
