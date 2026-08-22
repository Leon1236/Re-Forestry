package com.leon1236.reforestry.apiculture.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;
import com.leon1236.reforestry.api.apiculture.hives.VillageHive;
import com.leon1236.reforestry.api.climate.ClimateState;
import com.leon1236.reforestry.api.genetics.AllelePair;
import com.leon1236.reforestry.api.genetics.ClimateHelper;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureBlocks;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureFeatures;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.apiculture.tiles.TileBeeHousing;
import com.leon1236.reforestry.core.tiles.TileUtil;

public class ApiaristPoolElement extends SinglePoolElement {
    public static final MapCodec<ApiaristPoolElement> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(templateCodec(), processorsCodec(), projectionCodec(), overrideLiquidSettingsCodec())
                    .apply(instance, ApiaristPoolElement::new));

    public ApiaristPoolElement(
            Either<Identifier, StructureTemplate> template,
            Holder<StructureProcessorList> processors,
            StructureTemplatePool.Projection projection,
            Optional<LiquidSettings> overrideLiquidSettings) {
        super(template, processors, projection, overrideLiquidSettings);
    }

    @Override
    public void handleDataMarker(
            LevelAccessor level,
            StructureTemplate.StructureBlockInfo info,
            BlockPos pos,
            Rotation rotation,
            RandomSource random,
            BoundingBox box) {
        if (info.nbt() == null) {
            return;
        }
        StructureMode mode = info.nbt().read("mode", StructureMode.LEGACY_CODEC).orElse(null);
        if (mode != StructureMode.DATA) {
            return;
        }
        String marker = info.nbt().getStringOr("metadata", "");
        if ("apiary".equals(marker)) {
            replaceWithApiary(level, info, random);
        }
    }

    @Override
    protected StructurePlaceSettings getSettings(
            Rotation rotation,
            BoundingBox bounds,
            LiquidSettings liquidSettings,
            boolean keepJigsaws) {
        return super.getSettings(rotation, bounds, liquidSettings, keepJigsaws)
                .popProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
    }

    private static void replaceWithApiary(
            LevelAccessor level,
            StructureTemplate.StructureBlockInfo info,
            RandomSource random) {
        BlockPos markerPos = info.pos();
        level.removeBlock(markerPos, false);
        level.setBlock(markerPos, ApicultureBlocks.APIARY.block().defaultBlockState(), Block.UPDATE_ALL);

        TileUtil.actOnTile(level, markerPos, TileBeeHousing.class, apiary -> {
            ItemStack queen = chooseRandomVillageQueen(level, markerPos, random);
            apiary.setItem(TileBeeHousing.SLOT_QUEEN, queen);

            for (int i = 0; i < TileBeeHousing.SLOT_FRAME_COUNT; ++i) {
                ItemStack frame = new ItemStack(ApicultureItems.FRAME_PROVEN.item());
                int maxDamage = frame.getMaxDamage();
                frame.set(DataComponents.DAMAGE, random.nextIntBetweenInclusive(maxDamage / 4, maxDamage - maxDamage / 4));
                apiary.setItem(TileBeeHousing.SLOT_FRAME_1 + i, frame);
            }
        });
    }

    private static ItemStack chooseRandomVillageQueen(LevelAccessor level, BlockPos markerPos, RandomSource random) {
        IHiveManager manager = IForestryApi.get().getHiveManager();
        boolean rarePool = random.nextInt(4) == 0;
        List<VillageHive> pool = rarePool ? manager.getRareVillageHives() : manager.getCommonVillageHives();
        ClimateState biomeState = IForestryApi.get().getClimateManager().getBiomeState(level, markerPos);
        ArrayList<Pair<IBeeSpecies, Map<IChromosome<?>, IAllele>>> candidates = getCandidates(pool, biomeState);
        if (rarePool && candidates.isEmpty()) {
            candidates = getCandidates(manager.getCommonVillageHives(), biomeState);
        }

        if (candidates.isEmpty()) {
            ImmutableList<VillageHive> commons = manager.getCommonVillageHives();
            if (commons.isEmpty()) {
                return createQueen(ReForestry.id("bee_forest"), Map.of());
            }
            VillageHive hive = commons.get(random.nextInt(commons.size()));
            return createQueen(hive.speciesId(), hive.alleles());
        }
        Pair<IBeeSpecies, Map<IChromosome<?>, IAllele>> candidate = candidates.get(random.nextInt(candidates.size()));
        return createQueen(candidate.getFirst().id(), candidate.getSecond());
    }

    private static ArrayList<Pair<IBeeSpecies, Map<IChromosome<?>, IAllele>>> getCandidates(
            List<VillageHive> pool,
            ClimateState biomeState) {
        ArrayList<Pair<IBeeSpecies, Map<IChromosome<?>, IAllele>>> candidates = new ArrayList<>();
        for (VillageHive hive : pool) {
            IBeeSpecies species = ApicultureGenetics.getSpeciesSafe(hive.speciesId());
            if (species == null) {
                continue;
            }
            IGenome defaultGenome = createGenome(hive.speciesId(), hive.alleles());
            if (ClimateHelper.isWithinLimits(
                    biomeState.temperature(),
                    species.getTemperature(),
                    defaultGenome.getActiveAllele(BeeChromosomes.TEMPERATURE_TOLERANCE).value())
                    && ClimateHelper.isWithinLimits(
                            biomeState.humidity(),
                            species.getHumidity(),
                            defaultGenome.getActiveAllele(BeeChromosomes.HUMIDITY_TOLERANCE).value())) {
                candidates.add(Pair.of(species, hive.alleles()));
            }
        }
        return candidates;
    }

    private static ItemStack createQueen(Identifier speciesId, Map<IChromosome<?>, IAllele> extraAlleles) {
        IGenome genome = createGenome(speciesId, extraAlleles);
        ItemStack queen = BeeStackHelper.createBeeStack(ApicultureItems.BEE_QUEEN.item(), genome, true, 0);
        queen.set(ApicultureDataComponents.BEE_MATE_GENOME.type(), genome);
        return queen;
    }

    private static IGenome createGenome(Identifier speciesId, Map<IChromosome<?>, IAllele> extraAlleles) {
        IGenome defaults = ApicultureGenetics.getDefaultGenome(speciesId);
        IGenomeBuilder builder = BeeChromosomes.KARYOTYPE.genomeBuilder();
        for (Map.Entry<IChromosome<?>, AllelePair<?>> entry : defaults.chromosomes().entrySet()) {
            setPairUnchecked(builder, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<IChromosome<?>, IAllele> entry : extraAlleles.entrySet()) {
            setAlleleUnchecked(builder, entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void setPairUnchecked(IGenomeBuilder builder, IChromosome<?> chromosome, AllelePair<?> pair) {
        builder.setPair((IChromosome) chromosome, pair);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void setAlleleUnchecked(IGenomeBuilder builder, IChromosome<?> chromosome, IAllele allele) {
        builder.set((IChromosome) chromosome, allele);
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return ApicultureFeatures.APIARIST_POOL_ELEMENT_TYPE;
    }

    @Override
    public String toString() {
        return "ApiaristPoolElement[" + this.template + "]";
    }
}
