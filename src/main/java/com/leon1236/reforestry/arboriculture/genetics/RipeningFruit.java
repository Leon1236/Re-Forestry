package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.BlockGetter;

import com.leon1236.reforestry.api.genetics.IGenome;

public class RipeningFruit extends Fruit {
    private final int colourCallow;
    private final int diffR;
    private final int diffG;
    private final int diffB;
    private final Identifier sprite;

    public RipeningFruit(Identifier id, boolean dominant, int ripeningPeriod, Identifier sprite, int ripe, int callow, List<Product> products) {
        super(id, dominant, ripeningPeriod, products);
        this.sprite = sprite;
        this.colourCallow = callow;
        this.diffR = (ripe >> 16 & 255) - (callow >> 16 & 255);
        this.diffG = (ripe >> 8 & 255) - (callow >> 8 & 255);
        this.diffB = (ripe & 255) - (callow & 255);
    }

    private float getRipeningStage(int ripeningTime) {
        if (ripeningTime >= ripeningPeriod) {
            return 1.0f;
        }
        return (float) ripeningTime / ripeningPeriod;
    }

    @Override
    public boolean isFruitLeaf() {
        return true;
    }

    @Override
    public int getColour(IGenome genome, BlockGetter world, BlockPos pos, int ripeningTime) {
        return getColour(getRipeningStage(ripeningTime));
    }

    private int getColour(float stage) {
        int r = (colourCallow >> 16 & 255) + (int) (diffR * stage);
        int g = (colourCallow >> 8 & 255) + (int) (diffG * stage);
        int b = (colourCallow & 255) + (int) (diffB * stage);
        return (r & 255) << 16 | (g & 255) << 8 | b & 255;
    }

    @Override
    public int getDecorativeColor() {
        return getColour(1.0f);
    }

    @Override
    public Identifier getSprite(IGenome genome, BlockGetter world, BlockPos pos, int ripeningTime) {
        return sprite;
    }

    @Override
    public Identifier getDecorativeSprite() {
        return sprite;
    }

    @Override
    public int getRipeningPeriod() {
        return ripeningPeriod;
    }
}
