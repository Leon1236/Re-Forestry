package com.leon1236.reforestry.arboriculture.loot;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.arboriculture.IToolGrafter;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.blocks.BlockDefaultLeavesFruit;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;

public final class GrafterLootHandler {
    private GrafterLootHandler() {
    }

    public static void init() {
        LootTableEvents.MODIFY_DROPS.register(GrafterLootHandler::modifyDrops);
    }

    private static void modifyDrops(Holder<LootTable> holder, LootContext context, List<ItemStack> drops) {
        BlockState state = context.getOptionalParameter(LootContextParams.BLOCK_STATE);
        if (state == null || !state.is(BlockTags.LEAVES)) {
            return;
        }
        ItemInstance toolInstance = context.getOptionalParameter(LootContextParams.TOOL);
        if (!(toolInstance instanceof ItemStack harvestingTool) || harvestingTool.isEmpty()) {
            return;
        }
        if (!(harvestingTool.getItem() instanceof IToolGrafter)) {
            return;
        }
        if (!(context.getOptionalParameter(LootContextParams.THIS_ENTITY) instanceof Player player)) {
            return;
        }

        if (drops.stream().noneMatch(stack -> stack.is(ItemTags.SAPLINGS))) {
            handleLoot(drops, player, harvestingTool, state, context);
        }

        harvestingTool.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    }

    private static void handleLoot(List<ItemStack> drops, Player player, ItemStack harvestingTool,
            BlockState state, LootContext context) {
        Level level = player.level();
        BlockEntity blockEntity = context.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        IGenome genome = getGenome(state, blockEntity);
        if (genome == null) {
            return;
        }
        Vec3 origin = context.getOptionalParameter(LootContextParams.ORIGIN);
        if (origin == null) {
            return;
        }
        BlockPos pos = BlockPos.containing(origin);

        float saplingModifier = 1.0f;
        Item item = harvestingTool.getItem();
        if (item instanceof IToolGrafter grafter) {
            saplingModifier = grafter.getSaplingModifier(harvestingTool, level, player, pos);
        }

        float chance = genome.getActiveAllele(TreeChromosomes.SAPLINGS).value() * saplingModifier;
        if (context.getRandom().nextFloat() <= chance) {
            IGenome saplingGenome = genome;
            if (blockEntity instanceof TileLeaves leaves) {
                saplingGenome = leaves.resolveSaplingGenome(context.getRandom());
            }
            if (saplingGenome != null) {
                ItemStack sapling = new ItemStack(ArboricultureItems.SAPLING.item());
                sapling.set(ArboricultureDataComponents.TREE_GENOME.type(), saplingGenome);
                drops.add(sapling);
            }
        }

        if (blockEntity instanceof TileLeaves leaves) {
            drops.addAll(leaves.pickFruit());
        }
        if (state.getBlock() instanceof BlockDefaultLeavesFruit) {
            IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
            if (fruit.isFruitLeaf()) {
                drops.addAll(fruit.getFruits(genome, level, Integer.MAX_VALUE));
            }
        }
    }

    @Nullable
    private static IGenome getGenome(BlockState state, @Nullable BlockEntity entity) {
        IGenome vanilla = ArboricultureGenetics.getVanillaIndividual(state);
        if (vanilla != null || entity == null) {
            return vanilla;
        }
        if (entity instanceof TileLeaves leaves) {
            return leaves.getGenome();
        }
        return null;
    }
}
