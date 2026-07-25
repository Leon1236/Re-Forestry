package com.leon1236.reforestry.apiculture.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;

public class BeeTravelParticle extends SingleQuadParticle {
    private final double startX;
    private final double startY;
    private final double startZ;

    private BeeTravelParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz,
                               int color, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.startX = x;
        this.startY = y;
        this.startZ = z;
        this.lifetime = 60;
        this.quadSize = 0.075f;
        this.gravity = 0.0f;
        this.hasPhysics = false;
        this.xd = dx / (lifetime / 2.0);
        this.yd = dy / (lifetime / 2.0);
        this.zd = dz / (lifetime / 2.0);
        setColor(ARGB.redFloat(color), ARGB.greenFloat(color), ARGB.blueFloat(color));
    }

    @Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;
        if (age++ >= lifetime) {
            remove();
            return;
        }
        int remaining = lifetime - age;
        if (age > lifetime / 2 && remaining > 0) {
            xd = (startX - x) / remaining;
            yd = (startY - y) / remaining;
            zd = (startZ - z) / remaining;
        }
        move(xd, yd, zd);
    }

    @Override
    protected Layer getLayer() {
        return Layer.bySprite(this.sprite);
    }

    public static class Provider implements ParticleProvider<BeeColorParticleOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(BeeColorParticleOptions type, ClientLevel level, double x, double y, double z,
                                        double dx, double dy, double dz, RandomSource random) {
            return new BeeTravelParticle(level, x, y, z, dx, dy, dz, type.color(), sprites.get(random));
        }
    }
}
