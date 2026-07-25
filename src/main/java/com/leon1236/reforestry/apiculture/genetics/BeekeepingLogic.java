package com.leon1236.reforestry.apiculture.genetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.pollen.IPollenType;
import com.leon1236.reforestry.apiculture.HasFlowersCache;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;
import com.leon1236.reforestry.core.genetics.pollen.PollenTypes;

public final class BeekeepingLogic implements IBeekeepingLogic {
    private static final int WORK_THROTTLE = 550;
    private static final float SECOND_PRINCESS_CHANCE = 0.1f;

    private final IBeeHousing housing;
    private final HasFlowersCache hasFlowersCache = new HasFlowersCache();
    private final QueenCanWorkCache queenCanWorkCache = new QueenCanWorkCache();
    private int throttleCounter;
    private ItemStack trackedQueenStack = ItemStack.EMPTY;
    private IEffectData[] effectData = new IEffectData[2];
    private List<BlockPos> clientFlowerPositions;

    public BeekeepingLogic(IBeeHousing housing) {
        this.housing = housing;
    }

    public void setClientFlowerPositions(List<BlockPos> flowerPositions) {
        this.clientFlowerPositions = flowerPositions;
    }

    @Override
    public boolean canWork() {
        IErrorLogic errorLogic = housing.getErrorLogic();
        errorLogic.clearErrors();

        ItemStack queenStack = housing.beeInventory().getQueen();
        if (!(queenStack.getItem() instanceof ItemBeeGE beeItem)) {
            errorLogic.setCondition(true, ForestryError.NO_QUEEN);
            trackedQueenStack = ItemStack.EMPTY;
            queenCanWorkCache.clear();
            return false;
        }

        if ("princess".equals(beeItem.lifeStage())) {
            boolean hasDrone = housing.beeInventory().getDrone().getItem() instanceof ItemBeeGE droneItem
                    && "drone".equals(droneItem.lifeStage());
            errorLogic.setCondition(!hasDrone, ForestryError.NO_DRONE);
            return !errorLogic.hasErrors();
        }

        if (!"queen".equals(beeItem.lifeStage())) {
            errorLogic.setCondition(true, ForestryError.NO_QUEEN);
            trackedQueenStack = ItemStack.EMPTY;
            queenCanWorkCache.clear();
            return false;
        }

        IGenome genome = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (genome == null) {
            errorLogic.setCondition(true, ForestryError.NO_QUEEN);
            trackedQueenStack = ItemStack.EMPTY;
            queenCanWorkCache.clear();
            return false;
        }

        if (!ItemStack.isSameItemSameComponents(trackedQueenStack, queenStack)) {
            hasFlowersCache.onNewQueen(genome, housing);
            trackedQueenStack = queenStack.copy();
            queenCanWorkCache.clear();
        }

        Set<IError> queenErrors = queenCanWorkCache.queenCanWork(genome, housing);
        for (IError error : queenErrors) {
            errorLogic.setCondition(true, error);
        }

        hasFlowersCache.update(genome, housing);
        errorLogic.setCondition(!hasFlowersCache.hasFlowers(), ForestryError.NO_FLOWER);

        return !errorLogic.hasErrors();
    }

    @Override
    public void doWork() {
        ItemStack queenStack = housing.beeInventory().getQueen();
        if (!(queenStack.getItem() instanceof ItemBeeGE beeItem)) {
            return;
        }

        if ("princess".equals(beeItem.lifeStage())) {
            throttleCounter++;
            if (throttleCounter < WORK_THROTTLE) {
                return;
            }
            throttleCounter = 0;
            tryMate(queenStack);
            return;
        }
        if (!"queen".equals(beeItem.lifeStage())) {
            return;
        }

        IGenome genome = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (genome == null) {
            return;
        }

        effectData = applyEffects(genome, effectData, true);

        throttleCounter++;
        if (throttleCounter < WORK_THROTTLE) {
            return;
        }
        throttleCounter = 0;

        tryPollinate(genome, housing.level().getRandom());

        IBeeSpecies primary = genome.getActiveAllele(BeeChromosomes.SPECIES).value();
        IBeeSpecies secondary = genome.getInactiveAllele(BeeChromosomes.SPECIES).value();
        float speed = genome.getActiveAllele(BeeChromosomes.SPEED).value();
        for (IBeeModifier modifier : housing.getBeeModifiers()) {
            speed = modifier.modifyProductionSpeed(genome, speed);
        }
        RandomSource random = housing.level().getRandom();
        for (IBeeSpecies.Product product : primary.products()) {
            if (random.nextFloat() <= product.chance() * speed) {
                housing.beeInventory().addProduct(new ItemStack(product.item(), product.count()));
            }
        }
        if (secondary != primary) {
            for (IBeeSpecies.Product product : secondary.products()) {
                if (random.nextFloat() <= (product.chance() / 2f) * speed) {
                    housing.beeInventory().addProduct(new ItemStack(product.item(), product.count()));
                }
            }
        }

        for (IBeeListener listener : housing.getBeeListeners()) {
            listener.wearOutEquipment(1);
        }

        ageQueen(queenStack, genome, random);
    }

    @Override
    public void doBeeFX() {
        ItemStack queenStack = housing.beeInventory().getQueen();
        if (!(queenStack.getItem() instanceof ItemBeeGE beeItem) || !"queen".equals(beeItem.lifeStage())) {
            return;
        }
        IGenome genome = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (genome == null) {
            return;
        }
        effectData = applyEffects(genome, effectData, false);
    }

    private IEffectData[] applyEffects(IGenome genome, IEffectData[] storedData, boolean serverEffect) {
        IBeeEffect effect = genome.getActiveAllele(BeeChromosomes.EFFECT).value();
        storedData[0] = applyEffect(effect, genome, storedData[0], serverEffect);

        if (!effect.isCombinable()) {
            return storedData;
        }

        IBeeEffect secondary = genome.getInactiveAllele(BeeChromosomes.EFFECT).value();
        if (!secondary.isCombinable()) {
            return storedData;
        }

        storedData[1] = applyEffect(secondary, genome, storedData[1], serverEffect);
        return storedData;
    }

    private IEffectData applyEffect(IBeeEffect effect, IGenome genome, IEffectData storedData, boolean serverEffect) {
        storedData = effect.validateStorage(storedData);
        if (serverEffect) {
            return effect.doEffect(genome, storedData, housing);
        }
        return effect.doFX(genome, storedData, housing);
    }

    @Override
    public void onGuiOpened() {
        if (housing.level().isClientSide()) {
            return;
        }
        queenCanWorkCache.clear();
        canWork();
        ItemStack queenStack = housing.beeInventory().getQueen();
        IGenome genome = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (genome != null && queenStack.getItem() instanceof ItemBeeGE beeItem && "queen".equals(beeItem.lifeStage())) {
            hasFlowersCache.forceLookForFlowers(genome, housing);
            canWork();
        }
    }

    @Override
    public int getWorkProgressPercent() {
        ItemStack queenStack = housing.beeInventory().getQueen();
        if (queenStack.isEmpty() || !(queenStack.getItem() instanceof ItemBeeGE beeItem)) {
            return 0;
        }

        if ("princess".equals(beeItem.lifeStage())) {
            if (housing.beeInventory().getDrone().isEmpty()) {
                return 0;
            }
            return Math.round(100f * throttleCounter / WORK_THROTTLE);
        }

        if (!"queen".equals(beeItem.lifeStage())) {
            return 0;
        }
        IGenome genome = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (genome == null) {
            return 0;
        }
        int lifespan = genome.getActiveAllele(BeeChromosomes.LIFESPAN).value();
        if (lifespan <= 0) {
            return 0;
        }
        int lifeUsed = queenStack.getOrDefault(ApicultureDataComponents.BEE_LIFE_USED.type(), 0);
        return Math.round(100f * lifeUsed / lifespan);
    }

    @Override
    public List<BlockPos> getFlowerPositions() {
        if (clientFlowerPositions != null) {
            return clientFlowerPositions;
        }
        return hasFlowersCache.getFlowerCoords();
    }

    private void tryPollinate(IGenome genome, RandomSource random) {
        List<IPollenType> pollenTypes = PollenTypes.all();
        if (pollenTypes.isEmpty()) {
            return;
        }
        int pollination = genome.getActiveAllele(BeeChromosomes.POLLINATION).value();
        float pollinationChance = pollination;
        for (IBeeModifier modifier : housing.getBeeModifiers()) {
            pollinationChance = modifier.modifyPollination(genome, pollinationChance);
        }
        if (random.nextInt(100) >= pollinationChance) {
            return;
        }

        Level level = housing.level();
        BlockPos center = housing.position();
        Vec3i territory = BeeCanWork.getAdjustedTerritory(genome, housing);
        int radiusXZ = Math.max(1, territory.getX() / 2);
        int radiusY = Math.max(1, territory.getY() / 2);

        List<PollenCandidate> candidates = new ArrayList<>();
        for (BlockPos candidate : BlockPos.betweenClosed(
                center.offset(-radiusXZ, -radiusY, -radiusXZ),
                center.offset(radiusXZ, radiusY, radiusXZ))) {
            for (IPollenType type : pollenTypes) {
                if (type.canPollinate(level, candidate)) {
                    candidates.add(new PollenCandidate(candidate.immutable(), type));
                    break;
                }
            }
        }
        if (candidates.isEmpty()) {
            return;
        }

        PollenCandidate source = candidates.get(random.nextInt(candidates.size()));
        PollenCandidate target = candidates.get(random.nextInt(candidates.size()));
        source.type().tryCollectPollen(level, source.pos(), random).ifPresent(pollen -> {
            for (IBeeListener listener : housing.getBeeListeners()) {
                if (listener.onPollenRetrieved(pollen)) {
                    return;
                }
            }
            target.type().tryPollinate(level, target.pos(), pollen, random);
        });
    }

    private record PollenCandidate(BlockPos pos, IPollenType type) {
    }

    private void tryMate(ItemStack princessStack) {
        ItemStack droneStack = housing.beeInventory().getDrone();
        if (droneStack.isEmpty()) {
            return;
        }
        IGenome droneGenome = droneStack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (droneGenome == null) {
            return;
        }
        IGenome princessGenome = princessStack.get(ApicultureDataComponents.BEE_GENOME.type());

        ItemStack queenStack = new ItemStack(ApicultureItems.BEE_QUEEN.item());
        queenStack.set(ApicultureDataComponents.BEE_GENOME.type(), princessGenome);
        queenStack.set(ApicultureDataComponents.BEE_MATE_GENOME.type(), droneGenome);

        housing.beeInventory().setQueen(queenStack);
        housing.beeInventory().setDrone(ItemStack.EMPTY);
    }

    private void ageQueen(ItemStack queenStack, IGenome genome, RandomSource random) {
        int lifeUsed = queenStack.getOrDefault(ApicultureDataComponents.BEE_LIFE_USED.type(), 0) + 1;
        int lifespan = genome.getActiveAllele(BeeChromosomes.LIFESPAN).value();
        if (lifeUsed < lifespan) {
            queenStack.set(ApicultureDataComponents.BEE_LIFE_USED.type(), lifeUsed);
            housing.beeInventory().setQueen(queenStack);
            return;
        }

        IGenome mateGenome = queenStack.get(ApicultureDataComponents.BEE_MATE_GENOME.type());
        if (mateGenome != null) {
            spawnOffspring(genome, mateGenome, random);
        }
        housing.beeInventory().setQueen(ItemStack.EMPTY);
    }

    private void spawnOffspring(IGenome own, IGenome mate, RandomSource random) {
        int princessCount = 1 + (random.nextFloat() < SECOND_PRINCESS_CHANCE ? 1 : 0);
        for (int i = 0; i < princessCount; i++) {
            insertOrDrop(createBeeStack(ApicultureItems.BEE_PRINCESS.item(), rollOffspringGenome(own, mate, random)));
        }

        int droneCount = Math.max(1, own.getActiveAllele(BeeChromosomes.FERTILITY).value());
        for (int i = 0; i < droneCount; i++) {
            insertOrDrop(createBeeStack(ApicultureItems.BEE_DRONE.item(), rollOffspringGenome(own, mate, random)));
        }
    }

    private IGenome rollOffspringGenome(IGenome own, IGenome mate, RandomSource random) {
        BeeMating.MatingResult result = random.nextBoolean()
                ? BeeMating.resolveOffspringGenome(own, mate, housing, random)
                : BeeMating.resolveOffspringGenome(mate, own, housing, random);
        result.mutation().ifPresent(this::onMutationDiscovered);
        onSpeciesDiscovered(result.genome().getActiveAllele(BeeChromosomes.SPECIES).value());
        return result.genome();
    }

    private void onMutationDiscovered(Mutation mutation) {
    }

    private void onSpeciesDiscovered(IBeeSpecies species) {
    }

    private ItemStack createBeeStack(Item item, IGenome genome) {
        ItemStack stack = new ItemStack(item);
        stack.set(ApicultureDataComponents.BEE_GENOME.type(), genome);
        return stack;
    }

    private void insertOrDrop(ItemStack stack) {
        if (!housing.beeInventory().addProduct(stack)) {
            Level level = housing.level();
            BlockPos pos = housing.position();
            Block.popResource(level, pos, stack);
        }
    }

    private static final class QueenCanWorkCache {
        private static final int TICKS_PER_CHECK = 10;

        private Set<IError> queenCanWorkCached = Collections.emptySet();
        private int queenCanWorkCooldown;

        private Set<IError> queenCanWork(IGenome genome, IBeeHousing beeHousing) {
            if (queenCanWorkCooldown <= 0) {
                queenCanWorkCached = BeeCanWork.getCanWork(genome, beeHousing);
                queenCanWorkCooldown = TICKS_PER_CHECK;
            } else {
                queenCanWorkCooldown--;
            }
            return queenCanWorkCached;
        }

        private void clear() {
            queenCanWorkCached = Collections.emptySet();
            queenCanWorkCooldown = 0;
        }
    }
}
