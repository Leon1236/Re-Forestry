package com.leon1236.reforestry.core.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class TileMill extends TileBase {
    public float speed;
    public int stage;
    public int charge;
    public float progress;

    private static final DustParticleOptions PARTICLES = new DustParticleOptions(0xFFFFFF, 1.0f);

    protected TileMill(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.speed = 0.01f;
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, TileMill tile) {
        tile.update(level, pos, false);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileMill tile) {
        tile.update(level, pos, true);
    }

    protected void update(Level level, BlockPos pos, boolean isSimulating) {
        if (this.charge <= 0) {
            if (this.stage > 0) {
                this.progress += this.speed;
            }
            if (this.progress > 0.5f) {
                this.stage = 2;
            }
            if (this.progress > 1.0f) {
                this.progress = 0.0f;
                this.stage = 0;
            }
            return;
        }

        this.progress += this.speed;
        if (this.stage <= 0) {
            this.stage = 1;
        }

        if (this.progress > 0.5f && this.stage == 1) {
            this.stage = 2;
            if (this.charge < 7 && isSimulating) {
                this.charge++;
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            PARTICLES,
                            this.getBlockPos().getX() + 0.5,
                            this.getBlockPos().getY() + 0.5,
                            this.getBlockPos().getZ() + 0.5,
                            10,
                            0.125f,
                            0.25f,
                            0.125f,
                            0.01f
                    );
                }
                setChanged();
            }
        }
        if (this.progress > 1.0f) {
            this.progress = 0.0f;
            this.stage = 0;

            if (this.charge >= 7) {
                activate(level, pos);
            }
        }
    }

    protected abstract void activate(Level level, BlockPos pos);

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Charge", this.charge);
        output.putFloat("Progress", this.progress);
        output.putInt("Stage", this.stage);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.charge = input.getIntOr("Charge", 0);
        this.progress = input.getFloatOr("Progress", 0.0f);
        this.stage = input.getIntOr("Stage", 0);
    }

    @Override
    protected boolean hasGui() {
        return false;
    }
}
