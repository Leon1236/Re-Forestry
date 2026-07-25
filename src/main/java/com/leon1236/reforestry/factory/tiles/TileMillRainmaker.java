package com.leon1236.reforestry.factory.tiles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.WeatherData;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.api.fuels.RainSubstrate;
import com.leon1236.reforestry.core.tiles.TileMill;
import com.leon1236.reforestry.factory.features.FactoryTiles;

public class TileMillRainmaker extends TileMill {
    private int duration;
    private boolean reverse;

    private int yParticle;
    private static final int MAX_PARTICLE_COUNT = 64;
    private int particleCount = MAX_PARTICLE_COUNT;

    public TileMillRainmaker(BlockPos pos, BlockState state) {
        super(FactoryTiles.RAINMAKER.type(), pos, state);
        this.speed = 0.01f;
    }

    public boolean tryCharge(Player player, ItemStack heldItem) {
        if (this.charge != 0 || this.progress != 0.0f || heldItem.isEmpty()) {
            return false;
        }

        RainSubstrate substrate = findRainSubstrate(heldItem);
        if (substrate == null || !ItemStack.isSameItem(substrate.item(), heldItem)) {
            return false;
        }

        Level level = getLevel();
        if (level == null) {
            return false;
        }

        if (substrate.reverse()) {
            if (!level.isRaining()) {
                return false;
            }
        } else if (level.isRaining()) {
            return false;
        }

        addCharge(substrate);
        if (!player.isCreative()) {
            heldItem.shrink(1);
        }
        setChanged();
        return true;
    }

    public void addCharge(RainSubstrate substrate) {
        this.charge = 1;
        this.speed = substrate.speed();
        this.duration = substrate.duration();
        this.reverse = substrate.reverse();
        setChanged();
    }

    @Override
    protected void update(Level level, BlockPos pos, boolean isSimulating) {
        super.update(level, pos, isSimulating);

        if (particleCount < MAX_PARTICLE_COUNT && level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.CLOUD,
                    this.getBlockPos().getX() + 0.5,
                    yParticle,
                    this.getBlockPos().getZ() + 0.5,
                    10,
                    0.025f,
                    1.0f,
                    0.025f,
                    0.01f
            );

            yParticle += 2;
            particleCount++;
        }
    }

    @Override
    protected void activate(Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            particleCount = 0;
            yParticle = this.getBlockPos().getY() + 2;

            serverLevel.sendParticles(
                    ParticleTypes.CLOUD,
                    this.getBlockPos().getX() + 0.5,
                    this.getBlockPos().getY(),
                    this.getBlockPos().getZ() + 0.5,
                    10,
                    0.025f,
                    0.025f,
                    0.025f,
                    0.1f
            );

            serverLevel.playSound(null, this.getBlockPos(), SoundEvents.CONDUIT_DEACTIVATE, SoundSource.BLOCKS, 1.0f, 0.8f);

            WeatherData weather = serverLevel.getWeatherData();
            if (this.reverse) {
                weather.setRaining(false);
            } else {
                weather.setRaining(true);
                weather.setRainTime(this.duration);
            }

            this.charge = 0;
            this.duration = 0;
            this.reverse = false;
            setChanged();
        }
    }

    @Nullable
    private static RainSubstrate findRainSubstrate(ItemStack stack) {
        if (FuelManager.rainSubstrate == null || stack.isEmpty()) {
            return null;
        }
        for (var entry : FuelManager.rainSubstrate.entrySet()) {
            if (ItemStack.isSameItem(entry.getKey(), stack)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Duration", this.duration);
        output.putBoolean("Reverse", this.reverse);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.duration = input.getIntOr("Duration", 0);
        this.reverse = input.getBooleanOr("Reverse", false);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return null;
    }
}
