package com.leon1236.reforestry.apiculture.items;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.hives.IHiveFrame;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;

public class ItemCreativeHiveFrame extends Item implements IHiveFrame {
    public static final String NBT_FORCE_MUTATIONS = "force_mutations";
    public static final String NBT_FORCED_MUTATION = "forced_mutation";

    public ItemCreativeHiveFrame(Properties properties) {
        super(properties.rarity(Rarity.EPIC).stacksTo(1));
    }

    @Override
    public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IGenome queen, int wear) {
        return frame;
    }

    @Override
    public IBeeModifier getBeeModifier(ItemStack frame) {
        Identifier forced = getForcedMutation(frame);
        if (forced != null) {
            return new Modifier(true, forced);
        }
        return hasForceMutations(frame) ? Modifier.FORCE_ANY : Modifier.BASE;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
            Consumer<Component> tooltip, TooltipFlag flag) {
        tooltip.accept(Component.translatable("item.reforestry.bee.modifier.production", Modifier.PRODUCTION));
        tooltip.accept(Component.translatable("item.reforestry.bee.modifier.genetic.decay", Modifier.GENETIC_DECAY));
        Identifier forced = getForcedMutation(stack);
        if (forced != null) {
            tooltip.accept(Component.literal("Forces mutation: " + forced).withStyle(ChatFormatting.LIGHT_PURPLE));
        } else if (hasForceMutations(stack)) {
            tooltip.accept(Component.literal("Maximum mutation chances").withStyle(ChatFormatting.LIGHT_PURPLE));
        } else {
            tooltip.accept(Component.literal("Base mutation chances").withStyle(ChatFormatting.GRAY));
        }
    }

    public static boolean hasForceMutations(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        return customData != null && customData.copyTag().contains(NBT_FORCE_MUTATIONS);
    }

    @Nullable
    public static Identifier getForcedMutation(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return null;
        }
        CompoundTag tag = customData.copyTag();
        if (!tag.contains(NBT_FORCED_MUTATION)) {
            return null;
        }
        String raw = tag.getString(NBT_FORCED_MUTATION).orElse("");
        if (raw.isEmpty()) {
            return null;
        }
        try {
            return Identifier.parse(raw);
        } catch (RuntimeException ignored) {
            return null;
        }
    }

    public static void setForceMutations(ItemStack stack, boolean force) {
        CompoundTag tag = readTag(stack);
        if (force) {
            tag.putBoolean(NBT_FORCE_MUTATIONS, true);
        } else {
            tag.remove(NBT_FORCE_MUTATIONS);
        }
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    public static void setForcedMutation(ItemStack stack, Identifier result) {
        CompoundTag tag = readTag(stack);
        tag.putString(NBT_FORCED_MUTATION, result.toString());
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    public static ItemStack forceMutationsStack(Item item) {
        ItemStack stack = new ItemStack(item);
        setForceMutations(stack, true);
        return stack;
    }

    private static CompoundTag readTag(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        return customData != null ? customData.copyTag() : new CompoundTag();
    }

    private record Modifier(boolean forceAny, @Nullable Identifier forcedResult) implements IBeeModifier {
        static final float PRODUCTION = 10000f;
        static final float POLLINATION = 100f;
        static final float MUTATION = 100f;
        static final float GENETIC_DECAY = 0f;

        static final Modifier BASE = new Modifier(false, null);
        static final Modifier FORCE_ANY = new Modifier(true, null);

        @Override
        public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
            if (this.forcedResult != null) {
                return mutation.result().equals(this.forcedResult) ? MUTATION : 0f;
            }
            return this.forceAny ? MUTATION : currentChance;
        }

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

        @Override
        public boolean providesFlowers() {
            return true;
        }

        @Override
        public boolean isClimateFullyTolerant() {
            return true;
        }
    }
}
