package com.leon1236.reforestry.apiculture.items;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.hives.IHiveFrame;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;

public class ItemCreativeHiveFrame extends Item implements IHiveFrame {
    public ItemCreativeHiveFrame(Properties properties) {
        super(properties.rarity(Rarity.EPIC).stacksTo(1));
    }

    @Override
    public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IGenome queen, int wear) {
        return frame;
    }

    @Override
    public IBeeModifier getBeeModifier(ItemStack frame) {
        return Modifier.INSTANCE;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
            Consumer<Component> tooltip, TooltipFlag flag) {
        tooltip.accept(Component.translatable("item.reforestry.bee.modifier.production", Modifier.PRODUCTION));
        tooltip.accept(Component.translatable("item.reforestry.bee.modifier.genetic.decay", Modifier.GENETIC_DECAY));
    }

    private enum Modifier implements IBeeModifier {
        INSTANCE;

        private static final float PRODUCTION = 10000f;
        private static final float POLLINATION = 100f;
        private static final float GENETIC_DECAY = 0f;

        @Override
        public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
            return -1f;
        }

        @Override
        public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
            return PRODUCTION;
        }

        @Override
        public float modifyPollination(IGenome genome, float currentPollination) {
            return POLLINATION;
        }

        @Override
        public float modifyGeneticDecay(IGenome genome, float currentDecay) {
            return GENETIC_DECAY;
        }

        @Override
        public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
            return currentChance;
        }

        @Override
        public boolean isSealed() {
            return true;
        }

        @Override
        public boolean isAlwaysActive(IGenome genome) {
            return true;
        }

        @Override
        public boolean isSunlightSimulated() {
            return true;
        }

        @Override
        public boolean isHellish() {
            return true;
        }
    }
}
