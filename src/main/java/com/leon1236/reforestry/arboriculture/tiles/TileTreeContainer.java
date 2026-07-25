package com.leon1236.reforestry.arboriculture.tiles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public abstract class TileTreeContainer extends BlockEntity {
    private static final String NBT_GENOME = "Genome";

    @Nullable
    private IGenome genome;

    protected TileTreeContainer(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Nullable
    public IGenome getGenome() {
        return genome;
    }

    public void setGenome(IGenome genome) {
        this.genome = genome;
        markUpdated();
    }

    protected void markUpdated() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public Object getRenderData() {
        return new SpeciesRenderData.Simple(genome == null ? null
                : genome.getActiveAllele(TreeChromosomes.SPECIES).value().id());
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (genome != null) {
            output.store(NBT_GENOME, TreeChromosomes.KARYOTYPE.genomeCodec(), genome);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        genome = input.read(NBT_GENOME, TreeChromosomes.KARYOTYPE.genomeCodec()).orElse(null);
    }

    public abstract void onBlockTick(Level level, BlockPos pos, BlockState state, RandomSource random);
}
