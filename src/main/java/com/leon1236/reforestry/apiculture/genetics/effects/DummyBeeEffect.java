package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.render.ParticleRender;

public class DummyBeeEffect implements IBeeEffect {
    public static final DummyBeeEffect NONE = new DummyBeeEffect(ForestryBeeEffects.NONE, true);

    private final Identifier id;
    private final boolean dominant;

    public DummyBeeEffect(Identifier id, boolean dominant) {
        this.id = id;
        this.dominant = dominant;
    }

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public boolean isDominant() {
        return dominant;
    }

    @Override
    public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        IBeekeepingLogic beekeepingLogic = housing.getBeekeepingLogic();
        List<BlockPos> flowerPositions = beekeepingLogic.getFlowerPositions();
        ParticleRender.addBeeHiveFX(housing, genome, flowerPositions);
        return storedData;
    }
}
