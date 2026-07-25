package com.leon1236.reforestry.apiculture.items;

import java.util.function.Consumer;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.hives.IHiveFrame;
import com.leon1236.reforestry.api.genetics.IGenome;

public class ItemHiveFrame extends Item implements IHiveFrame {
    private final HiveFrameBeeModifier beeModifier;

    public ItemHiveFrame(Properties properties, int maxDamage, float geneticDecay) {
        super(properties.durability(maxDamage));
        this.beeModifier = new HiveFrameBeeModifier(geneticDecay);
    }

    @Override
    public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IGenome queen, int wear) {
        Level level = housing.level();
        if (level instanceof ServerLevel serverLevel) {
            frame.hurtAndBreak(wear, serverLevel, null, item -> {
            });
        }
        return frame.isEmpty() ? ItemStack.EMPTY : frame;
    }

    @Override
    public IBeeModifier getBeeModifier(ItemStack frame) {
        return beeModifier;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
            Consumer<Component> tooltip, TooltipFlag flag) {
        beeModifier.addInformation(tooltip);
        if (!stack.isDamaged()) {
            tooltip.accept(Component.translatable("item.reforestry.durability", stack.getMaxDamage()));
        }
    }

    private static final class HiveFrameBeeModifier implements IBeeModifier {
        private static final float PRODUCTION = 2f;
        private final float geneticDecay;

        private HiveFrameBeeModifier(float geneticDecay) {
            this.geneticDecay = geneticDecay;
        }

        @Override
        public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
            return currentSpeed < 10f ? currentSpeed * PRODUCTION : 1f;
        }

        @Override
        public float modifyGeneticDecay(IGenome genome, float currentDecay) {
            return geneticDecay;
        }

        private void addInformation(Consumer<Component> tooltip) {
            tooltip.accept(Component.translatable("item.reforestry.bee.modifier.production", PRODUCTION));
            tooltip.accept(Component.translatable("item.reforestry.bee.modifier.genetic.decay", geneticDecay));
        }
    }
}
