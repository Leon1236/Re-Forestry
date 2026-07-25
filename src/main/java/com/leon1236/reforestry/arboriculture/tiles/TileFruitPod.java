package com.leon1236.reforestry.arboriculture.tiles;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.arboriculture.blocks.BlockFruitPod;
import com.leon1236.reforestry.arboriculture.features.ArboricultureTiles;
import com.leon1236.reforestry.arboriculture.genetics.DefaultFruits;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.core.utils.BlockUtil;

public class TileFruitPod extends BlockEntity {
    private static final short MAX_MATURITY = 2;

    private static final String NBT_MATURITY = "MT";
    private static final String NBT_YIELD = "SP";
    private static final String NBT_FRUIT = "UID";
    private static final String NBT_GENOME = "Genome";

    @Nullable
    private IGenome genome;
    @Nullable
    private IFruit fruit;
    private short maturity;
    private float yield;

    public TileFruitPod(BlockPos pos, BlockState state) {
        super(ArboricultureTiles.PODS.type(), pos, state);
    }

    public void setProperties(IGenome genome, IFruit allele, float yield) {
        this.genome = genome;
        this.fruit = allele;
        this.yield = yield;
        setChanged();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (this.fruit != null) {
            output.putString(NBT_FRUIT, this.fruit.id().toString());
        }
        if (this.genome != null) {
            output.store(NBT_GENOME, TreeChromosomes.KARYOTYPE.genomeCodec(), this.genome);
        }
        output.putShort(NBT_MATURITY, this.maturity);
        output.putFloat(NBT_YIELD, this.yield);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.genome = input.read(NBT_GENOME, TreeChromosomes.KARYOTYPE.genomeCodec()).orElse(null);
        String fruitNbt = input.getStringOr(NBT_FRUIT, "");
        if (!fruitNbt.isEmpty()) {
            this.fruit = resolveFruit(Identifier.parse(fruitNbt));
        }
        if (this.fruit == null) {
            this.fruit = DefaultFruits.COCOA;
        }
        this.maturity = (short) input.getShortOr(NBT_MATURITY, (short) 0);
        this.yield = input.getFloatOr(NBT_YIELD, 0.0f);
    }

    @Nullable
    private static IFruit resolveFruit(Identifier id) {
        IAllele allele = AlleleManager.INSTANCE.getAllele(id);
        if (allele instanceof IValueAllele<?> valueAllele && valueAllele.value() instanceof IFruit resolved) {
            return resolved;
        }
        for (IFruit candidate : DefaultFruits.ALL) {
            if (candidate.id().equals(id)) {
                return candidate;
            }
        }
        return null;
    }

    public void onBlockTick(RandomSource rand) {
        if (canMature() && rand.nextFloat() <= this.yield) {
            addRipeness(0.5f);
        }
    }

    public boolean canMature() {
        return this.maturity < MAX_MATURITY;
    }

    public short getMaturity() {
        return this.maturity;
    }

    public ItemStack getPickBlock() {
        if (this.fruit == null) {
            return ItemStack.EMPTY;
        }
        ItemStack pickBlock = ItemStack.EMPTY;
        float maxChance = 0.0f;
        for (IFruit.Product product : this.fruit.getProducts()) {
            if (maxChance < product.chance()) {
                maxChance = product.chance();
                pickBlock = new ItemStack(product.item());
            }
        }
        if (!pickBlock.isEmpty()) {
            pickBlock.setCount(1);
        }
        return pickBlock;
    }

    public List<ItemStack> getDrops() {
        if (this.fruit == null || this.genome == null || this.level == null) {
            return List.of();
        }
        return this.fruit.getFruits(this.genome, this.level, this.maturity);
    }

    public List<ItemStack> pickFruit() {
        List<ItemStack> fruits = getDrops();
        this.maturity = 0;
        if (this.level != null) {
            BlockState oldState = getBlockState();
            BlockState newState = oldState.setValue(BlockFruitPod.AGE, 0);
            BlockUtil.setBlockWithBreakSound(this.level, getBlockPos(), newState, oldState);
        }
        return fruits;
    }

    public float getRipeness() {
        return (float) this.maturity / MAX_MATURITY;
    }

    public void addRipeness(float add) {
        int previousAge = this.maturity;
        this.maturity += (short) (MAX_MATURITY * add);
        if (this.maturity > MAX_MATURITY) {
            this.maturity = MAX_MATURITY;
        }
        int age = this.maturity;
        if (age - previousAge > 0 && this.level != null) {
            BlockState state = getBlockState().setValue(BlockFruitPod.AGE, age);
            this.level.setBlockAndUpdate(getBlockPos(), state);
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
    }
}
