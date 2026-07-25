package com.leon1236.reforestry.api.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.ReForestry;

public final class ReforestryBiomeTags {
    public static final TagKey<Biome> ARID_HUMIDITY = create("humidity/arid");
    public static final TagKey<Biome> NORMAL_HUMIDITY = create("humidity/normal");
    public static final TagKey<Biome> DAMP_HUMIDITY = create("humidity/damp");

    public static final TagKey<Biome> ICY_TEMPERATURE = create("temperature/icy");
    public static final TagKey<Biome> COLD_TEMPERATURE = create("temperature/cold");
    public static final TagKey<Biome> NORMAL_TEMPERATURE = create("temperature/normal");
    public static final TagKey<Biome> WARM_TEMPERATURE = create("temperature/warm");
    public static final TagKey<Biome> HOT_TEMPERATURE = create("temperature/hot");
    public static final TagKey<Biome> HELLISH_TEMPERATURE = create("temperature/hellish");

    public static final TagKey<Biome> SHATTERED_SAVANNA = create("special/shattered_savanna");
    public static final TagKey<Biome> WARPED_FOREST = create("special/warped_forest");
    public static final TagKey<Biome> DEEP_DARK = create("special/deep_dark");

    private ReforestryBiomeTags() {
    }

    private static TagKey<Biome> create(String path) {
        return TagKey.create(Registries.BIOME, ReForestry.id(path));
    }

    public static final class Blocks {
        public static final TagKey<Block> MINEABLE_SCOOP = TagKey.create(Registries.BLOCK, ReForestry.id("mineable/scoop"));

        public static final TagKey<Block> VANILLA_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/vanilla"));
        public static final TagKey<Block> NETHER_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/nether"));
        public static final TagKey<Block> CACTI_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/cacti"));
        public static final TagKey<Block> MUSHROOMS_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/mushrooms"));
        public static final TagKey<Block> END_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/end"));
        public static final TagKey<Block> JUNGLE_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/jungle"));
        public static final TagKey<Block> SNOW_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/snow"));
        public static final TagKey<Block> WHEAT_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/wheat"));
        public static final TagKey<Block> GOURD_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/gourd"));
        public static final TagKey<Block> CAVE_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/cave"));
        public static final TagKey<Block> ANCIENT_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/ancient"));
        public static final TagKey<Block> SEA_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/sea"));
        public static final TagKey<Block> CORAL_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/coral"));
        public static final TagKey<Block> SCULK_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/sculk"));
        public static final TagKey<Block> PLANTABLE_FLOWERS =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/plantable"));
        public static final TagKey<Block> PLANTABLE_FLOWERS_GROUND =
                TagKey.create(Registries.BLOCK, ReForestry.id("flowers/plantable_ground"));
        public static final TagKey<Block> MODEST_BEE_GROUND =
                TagKey.create(Registries.BLOCK, ReForestry.id("hive_grounds/modest"));
        public static final TagKey<Block> ENDED_BEE_GROUND =
                TagKey.create(Registries.BLOCK, ReForestry.id("hive_grounds/ended"));
        public static final TagKey<Block> WINTRY_BEE_GROUND =
                TagKey.create(Registries.BLOCK, ReForestry.id("hive_grounds/wintry"));
        public static final TagKey<Block> LUSH_BEE_CEILING =
                TagKey.create(Registries.BLOCK, ReForestry.id("hive_grounds/lush"));
        public static final TagKey<Block> CAVE_EXTRA_REPLACEABLES =
                TagKey.create(Registries.BLOCK, ReForestry.id("hive_grounds/cave_extra_replaceable"));
        public static final TagKey<Block> NETHER_EXTRA_REPLACEABLES =
                TagKey.create(Registries.BLOCK, ReForestry.id("hive_grounds/nether_extra_replaceable"));
        public static final TagKey<Block> SWARM_BEE_GROUND =
                TagKey.create(Registries.BLOCK, ReForestry.id("hive_grounds/swarm"));

        private Blocks() {
        }
    }

    public static final class Items {
        public static final TagKey<Item> SCOOPS = TagKey.create(Registries.ITEM, ReForestry.id("scoops"));
        public static final TagKey<Item> REPAIRS_APIARIST_ARMOR =
                TagKey.create(Registries.ITEM, ReForestry.id("repairs_apiarist_armor"));

        private Items() {
        }
    }
}
