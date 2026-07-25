package com.leon1236.reforestry.apiculture.blocks;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDrop;
import com.leon1236.reforestry.api.apiculture.hives.IHiveTile;
import com.leon1236.reforestry.api.core.ReforestryBiomeTags;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.features.ApicultureTiles;
import com.leon1236.reforestry.apiculture.tiles.TileHive;

public class BlockBeeHive extends BaseEntityBlock {
    private final Identifier speciesId;
    private final MapCodec<BlockBeeHive> codec;

    public BlockBeeHive(BlockHiveType type, BlockBehaviour.Properties properties) {
        this(type.getSpeciesId(), properties);
    }

    public BlockBeeHive(Identifier speciesId, BlockBehaviour.Properties properties) {
        super(properties);
        this.speciesId = speciesId;
        this.codec = simpleCodec(props -> new BlockBeeHive(speciesId, props));
    }

    @Override
    protected MapCodec<? extends BlockBeeHive> codec() {
        return codec;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileHive(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> type) {
        return createTickerHelper(type, ApicultureTiles.HIVE.type(), TileHive::tick);
    }

    @Override
    protected void attack(BlockState state, Level world, BlockPos pos, Player player) {
        if (world.getBlockEntity(pos) instanceof IHiveTile tile) {
            tile.onAttack(world, pos, player);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        boolean canHarvest = player.getMainHandItem().is(ReforestryBiomeTags.Items.SCOOPS);
        if (world.getBlockEntity(pos) instanceof IHiveTile tile) {
            tile.onBroken(world, pos, player, canHarvest);
        }
        return super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        ItemStack tool = ItemStack.EMPTY;
        if (builder.getOptionalParameter(LootContextParams.TOOL) instanceof ItemStack stack) {
            tool = stack;
        }

        if (!tool.is(ReforestryBiomeTags.Items.SCOOPS)) {
            return List.of();
        }

        ServerLevel level = builder.getLevel();
        Holder.Reference<Enchantment> silkTouch =
                level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH);
        if (EnchantmentHelper.getItemEnchantmentLevel(silkTouch, tool) > 0) {
            return List.of(new ItemStack(this));
        }

        Holder.Reference<Enchantment> fortune =
                level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE);
        int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(fortune, tool);
        BlockPos pos = BlockPos.containing(builder.getParameter(LootContextParams.ORIGIN));
        return getHiveDrops(level, pos, fortuneLevel);
    }

    private List<ItemStack> getHiveDrops(ServerLevel level, BlockPos pos, int fortune) {
        List<ItemStack> drops = new ArrayList<>();
        RandomSource random = level.getRandom();
        List<IHiveDrop> hiveDrops = IForestryApi.INSTANCE.getHiveManager().getDrops(speciesId);
        if (hiveDrops.isEmpty()) {
            return drops;
        }

        List<IHiveDrop> shuffled = new ArrayList<>(hiveDrops);
        Util.shuffle(shuffled, random);

        boolean hasPrincess = false;
        for (int tries = 0; tries <= 10 && !hasPrincess; tries++) {
            for (IHiveDrop drop : shuffled) {
                if (random.nextDouble() < drop.getChance(level, pos, fortune)) {
                    IGenome genome = drop.createGenome(level, pos);
                    ItemStack princess = new ItemStack(ApicultureItems.BEE_PRINCESS.item());
                    princess.set(ApicultureDataComponents.BEE_GENOME.type(), genome);
                    drops.add(princess);
                    hasPrincess = true;
                    break;
                }
            }
        }

        for (int i = 0; i <= fortune; i++) {
            for (IHiveDrop drop : shuffled) {
                if (random.nextDouble() < drop.getChance(level, pos, fortune)) {
                    IGenome genome = drop.createGenome(level, pos);
                    ItemStack drone = new ItemStack(ApicultureItems.BEE_DRONE.item());
                    drone.set(ApicultureDataComponents.BEE_GENOME.type(), genome);
                    drops.add(drone);
                    break;
                }
            }
            for (IHiveDrop drop : shuffled) {
                if (random.nextDouble() < drop.getChance(level, pos, fortune)) {
                    drops.addAll(drop.getExtraItems(level, pos, fortune));
                    break;
                }
            }
        }
        return drops;
    }

    public Identifier getSpeciesId() {
        return speciesId;
    }
}
