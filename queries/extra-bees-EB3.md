# EB-FLOWERS + EB3 — Extra Bees flower types + bee effects

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `ExtraBeesFlowers` / `ExtraBeesEffect`  
**Extracts:** `queries/extra-bees-flowers.json`, `queries/extra-bees-effects.json`  
**Package:** `com.leon1236.reforestry.extra_bees.genetics` (+ `effects`)

## Player exit

- **11** Extra Bees flower types registered (`registerGenetics` → `FlowerTypeManager` + `BeeChromosomes.FLOWER_TYPE`)
- **25** bee effects registered in `registerApiculture` **before** any EB2 species
- RADIOACTIVE remapped to `reforestry:bee_effect_eb_radioactive` (CE keeps `bee_effect_radioactive`)

## Flower ids (`reforestry:`)

| Enum | Id |
|---|---|
| WATER | `flower_type_water` |
| SUGAR | `flower_type_sugar` |
| ROCK | `flower_type_rock` |
| BOOK | `flower_type_book` |
| DEAD | `flower_type_dead` |
| REDSTONE | `flower_type_redstone` |
| WOOD | `flower_type_wood` |
| LEAVES | `flower_type_leaves` |
| SAPLING | `flower_type_sapling` |
| FRUIT | `flower_type_fruit` |
| MYSTICAL | `flower_type_mystical` |

Accept lists live under `data/reforestry/tags/block/flowers/{water,sugar,rock,book,dead,redstone,fruit,sapling,mystical}.json`. WOOD/LEAVES use `#minecraft:logs` / `#minecraft:leaves`. ROCK also accepts base-stone tags. SAPLING uses vanilla saplings + `#reforestry:tree_saplings` (no `BlockTags.SAPLINGS` on 26.2). FRUIT accepts melon/pumpkin + `IFruitBearer` tiles.

### MYSTICAL / Botania

Soft: `FabricLoader.isModLoaded("botania")` plus optional tag entries (`botania:*_mystical_flower`, legacy `botania:flower`). Without Botania the type registers but accepts nothing (graceful degrade). `affectProducts` may add Botania petals when flowers are nearby and the petal item exists.

## Effect ids (`reforestry:`)

| Enum | Id | FX (vanilla particles when extract had `particles/*`) |
|---|---|---|
| ECTOPLASM | `bee_effect_ectoplasm` | — |
| ACID | `bee_effect_acid` | — |
| SPAWN_ZOMBIE | `bee_effect_spawn_zombie` | — |
| SPAWN_SKELETON | `bee_effect_spawn_skeleton` | — |
| SPAWN_CREEPER | `bee_effect_spawn_creeper` | — |
| LIGHTNING | `bee_effect_lightning` | electric spark / crit |
| RADIOACTIVE | `bee_effect_eb_radioactive` | scrape / smoke |
| METEOR | `bee_effect_meteor` | lava / flame |
| HUNGER | `bee_effect_hunger` | — |
| FOOD | `bee_effect_food` | happy villager |
| BLINDNESS | `bee_effect_blindness` | squid ink |
| CONFUSION | `bee_effect_confusion` | potion FX (local `PotionBeeEffect`) |
| FIREWORKS | `bee_effect_fireworks` | — |
| FESTIVAL | `bee_effect_festival` | — |
| BIRTHDAY | `bee_effect_birthday` | — |
| TELEPORT | `bee_effect_teleport` | portal |
| GRAVITY | `bee_effect_gravity` | portal |
| THIEF | `bee_effect_thief` | portal |
| WITHER | `bee_effect_wither` | smoke |
| WATER | `bee_effect_water` | dripping water |
| SLOW | `bee_effect_slow` | potion FX |
| BONEMEAL_SAPLING | `bee_effect_bonemeal_sapling` | — |
| BONEMEAL_FRUIT | `bee_effect_bonemeal_fruit` | — |
| BONEMEAL_MUSHROOM | `bee_effect_bonemeal_mushroom` | — |
| POWER | `bee_effect_power` | — |

Binnie stored `particles/*` texture paths but never spawned them from `doFX` (hive FX only). Re-Forestry maps those extract FX names to vanilla particles in `ExtraBeesEffectHelper`.

## Wiring

- `ExtraBeesForestryPlugin.registerGenetics` → 11 flower types  
- `ExtraBeesForestryPlugin.registerApiculture` → `ExtraBeesEffects.register` (25 effects, no species)  
- `PluginManager.runGeneticsRegistration` also `BeeChromosomes.FLOWER_TYPE.registerValue` for plugin types  
- `ThrottledBeeEffect.doEffectThrottled` made `protected` so Extra Bees can subclass from another package  

## 26.2 / Fabric notes

- Lightning: `EntityTypes.LIGHTNING_BOLT`  
- Meteor: `projectile.hurtingprojectile.SmallFireball`  
- WATER: Fabric `FluidStorage` insert (100 mB = 8100 droplets)  
- POWER: Team Reborn `EnergyStorage` insert 5 FE  
- Chunk checks: `level.isLoaded(pos)`  

## Gaps / next

- EB2 species not started (must use these flower/effect alleles)
- Custom Binnie particle textures under genetics `items/particles/` not shipped as particle types (vanilla FX used instead)
- Birthday fireworks: star + gold always; on April 11 also RGB + trail (Binnie `Birthday(3, 10)` → date+1)
- EB potion effects (blindness/confusion/wither/slow) and hunger use player-only Binnie armor rolls; CE `PotionBeeEffect` path not used
- FIREWORKS always trails (Binnie `setTrail`); FESTIVAL is plain white

**Next stage:** `EB2a` — **done** (see `queries/extra-bees-EB2a.md`)
