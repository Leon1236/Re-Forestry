package com.leon1236.reforestry.core.fluids;

public record ForestryFluidProperties(int density, int viscosity, int temperature, int particleColor,
                                       int flammability, boolean spreadsFire) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int density = 1000;
        private int viscosity = 1000;
        private int temperature = 295;
        private int particleColor = 0xFFFFFF;
        private int flammability = 0;
        private boolean spreadsFire = false;

        public Builder density(int density) {
            this.density = density;
            return this;
        }

        public Builder viscosity(int viscosity) {
            this.viscosity = viscosity;
            return this;
        }

        public Builder temperature(int temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder particleColor(int particleColor) {
            this.particleColor = particleColor;
            return this;
        }

        public Builder flammability(int flammability) {
            this.flammability = flammability;
            return this;
        }

        public Builder spreadsFire() {
            this.spreadsFire = true;
            return this;
        }

        public ForestryFluidProperties build() {
            return new ForestryFluidProperties(density, viscosity, temperature, particleColor, flammability, spreadsFire);
        }
    }
}
