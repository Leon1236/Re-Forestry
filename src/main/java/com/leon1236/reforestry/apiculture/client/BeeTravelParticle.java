package com.leon1236.reforestry.apiculture.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public class BeeTravelParticle extends SingleQuadParticle {
    public enum Mode {
        ROUND_TRIP,
        EXPLORE
    }

    private final Vec3 origin;
    private final BlockPos destination;
    private final Mode mode;

    private BeeTravelParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz,
            int color, TextureAtlasSprite sprite, Mode mode) {
        super(level, x, y, z, sprite);
        this.origin = new Vec3(x, y, z);
        this.destination = BlockPos.containing(x + dx, y + dy, z + dz);
        this.mode = mode;
        this.hasPhysics = false;
        this.quadSize *= 0.2F;
        this.lifetime = (int) (80.0D / (Math.random() * 0.8D + 0.2D));
        setColor(ARGB.redFloat(color), ARGB.greenFloat(color), ARGB.blueFloat(color));

        if (mode == Mode.ROUND_TRIP) {
            this.xd = (destination.getX() + 0.5 - this.x) * 0.02 + 0.1 * this.random.nextFloat();
            this.yd = (destination.getY() + 0.5 - this.y) * 0.015 + 0.1 * this.random.nextFloat();
            this.zd = (destination.getZ() + 0.5 - this.z) * 0.02 + 0.1 * this.random.nextFloat();
            this.xd *= 0.9D;
            this.yd *= 0.015D;
            this.zd *= 0.9D;
        } else {
            this.xd = (destination.getX() + 0.5 - this.x) * 0.015;
            this.yd = (destination.getY() + 0.5 - this.y) * 0.015;
            this.zd = (destination.getZ() + 0.5 - this.z) * 0.015;
            this.xd *= 0.9D;
            this.yd *= 0.015D;
            this.zd *= 0.9D;
        }
    }

    @Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;
        move(xd, yd, zd);

        if (age == lifetime / 2) {
            if (mode == Mode.ROUND_TRIP) {
                xd = (origin.x - x) * 0.03 + 0.1 * random.nextFloat();
                yd = (origin.y - y) * 0.03 + 0.1 * random.nextFloat();
                zd = (origin.z - z) * 0.03 + 0.1 * random.nextFloat();
            } else {
                xd = (origin.x - x) * 0.03;
                yd = (origin.y - y) * 0.03;
                zd = (origin.z - z) * 0.03;
            }
        }

        if (age < lifetime * 0.25) {
            if (mode == Mode.ROUND_TRIP) {
                xd *= 0.92 + 0.2D * random.nextFloat();
                yd = (yd + 0.3 * (-0.5 + random.nextFloat())) / 2;
                zd *= 0.92 + 0.2D * random.nextFloat();
            } else {
                xd *= 0.92 + 0.3D * random.nextFloat();
                yd = (yd + 0.3 * (-0.5 + random.nextFloat())) / 2;
                zd *= 0.92 + 0.3D * random.nextFloat();
            }
        } else if (age < lifetime * 0.5) {
            if (mode == Mode.ROUND_TRIP) {
                xd = (destination.getX() + 0.5 - x) * 0.03;
                yd = (destination.getY() + 0.5 - y) * 0.1;
                yd = (yd + 0.2 * (-0.5 + random.nextFloat())) / 2;
                zd = (destination.getZ() + 0.5 - z) * 0.03;
            } else {
                xd *= 0.75 + 0.3D * random.nextFloat();
                yd = (yd + 0.3 * (-0.5 + random.nextFloat())) / 2;
                zd *= 0.75 + 0.3D * random.nextFloat();
            }
        } else if (age < lifetime * 0.75) {
            xd *= 0.95;
            yd = (origin.y - y) * 0.03;
            yd = (yd + 0.2 * (-0.5 + random.nextFloat())) / 2;
            zd *= 0.95;
        } else {
            xd = (origin.x - x) * 0.03;
            yd = (origin.y - y) * 0.03;
            yd = (yd + 0.2 * (-0.5 + random.nextFloat())) / 2;
            zd = (origin.z - z) * 0.03;
        }

        if (age++ >= lifetime) {
            remove();
        }
    }

    @Override
    public void move(double xa, double ya, double za) {
        setBoundingBox(getBoundingBox().move(xa, ya, za));
        setLocationFromBoundingbox();
    }

    @Override
    protected int getLightCoords(float partialTick) {
        return 15728880;
    }

    @Override
    protected Layer getLayer() {
        return mode == Mode.EXPLORE ? Layer.TRANSLUCENT : Layer.bySprite(this.sprite);
    }

    public static class RoundTripProvider implements ParticleProvider<BeeColorParticleOptions> {
        private final SpriteSet sprites;

        public RoundTripProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(BeeColorParticleOptions type, ClientLevel level, double x, double y, double z,
                double dx, double dy, double dz, RandomSource random) {
            return new BeeTravelParticle(level, x, y, z, dx, dy, dz, type.color(), sprites.get(random), Mode.ROUND_TRIP);
        }
    }

    public static class ExploreProvider implements ParticleProvider<BeeColorParticleOptions> {
        private final SpriteSet sprites;

        public ExploreProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(BeeColorParticleOptions type, ClientLevel level, double x, double y, double z,
                double dx, double dy, double dz, RandomSource random) {
            return new BeeTravelParticle(level, x, y, z, dx, dy, dz, type.color(), sprites.get(random), Mode.EXPLORE);
        }
    }
}
