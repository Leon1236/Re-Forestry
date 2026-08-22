# Apiculture effects P0 — remaining effects + jubilance (2026-08-17)

Source: CE 1.21.1 `thedarkcolour-ForestryCE`. Java plugin registration only (no datapack `bee_effect` codecs / `ApicultureBeeEffectTypes`).

## Dummy → class

| Id | Class | Dominant / throttle (CE) | Notes |
|---|---|---|---|
| none | DummyBeeEffect.NONE | true | leftover stub (intentional) |
| easter | DummyBeeEffect | true | leftover stub (easter egg) |
| radioactive | RadioactiveBeeEffect | true, 40, false, true | `hurtServer` + `level.isLoaded`; `CoreDamageTypes.RADIOACTIVE` |
| creeper | CreeperBeeEffect | **true**, 20, false, true | dummy flag was false |
| ignition | IgnitionBeeEffect | false, 20, false, true | `igniteForSeconds` kept as seconds (500) |
| reanimation | ResurrectionBeeEffect | true, 40, true, true | bones/arrow/flesh/blaze rod |
| resurrection | ResurrectionBeeEffect | true, 40, true, true | gunpowder/pearl/string/eye/ghast/dragon egg |
| repulsion | RepulsionBeeEffect + AIAvoidPlayers | false, 100, true, true | |
| fertile | FertileBeeEffect | **false**, 6, true, false | dummy flag was true |
| mycophilic | FungificationBeeEffect | **true**, 20, false, false | dummy flag was false |
| sifter | SifterBeeEffect | **true**, 550, true, true | dedicated class, not TransformBlock codec; `#minecraft:dirt` → coarse dirt |
| glow_berry_grow | GlowBerryGrowEffect | false, 200, true, true | `#minecraft:cave_vines` `berries=false` → true |
| rejuvenation | AgingBeeEffect(aging=false) | NonStacking, dominant false | decreases `BEE_LIFE_USED` |
| chronophage | AgingBeeEffect(aging=true) | NonStacking, dominant false | increases `BEE_LIFE_USED` |
| guardian | GuardianBeeEffect | **true**, 1200, true, true | dummy flag was false; `MobEffects.MINING_FATIGUE` (26.2 rename of DIG_SLOWDOWN) |
| phasing | PhasingBeeEffect | **true**, 40, true, true | dummy flag was false; no NeoForge chorus-teleport event |
| ascension | AscensionBeeEffect | **true**, potion 200 | dummy flag was false |
| sculk | SculkSpreadBeeEffect | false, 200, true, true | `level.isLoaded` |
| patriotic | — | — | skipped (CE id exists, not registered locally) |

## Jubilance

- Default = exact housing temp **and** humidity.
- Hermit = no `Mob` in territory. Applied only to `bee_monastic`, `bee_secluded`, `bee_hermitic`.
- Specialties only if **both** active and inactive species are jubilant; then **primary** specialties × production speed (`< chance * speed`).
- `bee_boggy` peat specialty 0.08 added (CE gap).
- API: `IBeeJubilance`, `IJubilanceFactory`, `ForestryBeeJubilances`; factory on `BeeManager.jubilanceFactory`.

## Tracker

Offspring `registerBirth` of active species already existed. `tryMate` now calls `registerBirth` for the new queen’s active species (CE `registerQueen`). Queen/princess/drone counts not ported (book display).

## 26.2 swaps

- `level.isLoaded(pos)` not `hasChunkAt`
- `hurtServer` not `hurt`
- `housing.level()` / `position()` / `beeInventory()`
- `EntityTypes.*` not `EntityType.*` constants
- Cow → mooshroom: scan `AbstractCow`, skip `MushroomCow` (sibling, does not extend `Cow`)
- `ServerTickEvents.START_LEVEL_TICK` for NonStacking (not NeoForge `LevelTickEvent`)
- No `EventHooks.onChorusFruitTeleport`
- `igniteForSeconds` is seconds
- `getMinY` / `getMaxY` instead of `getMinBuildHeight` / `getLogicalHeight`
- `snapTo` instead of `moveTo`; `Mob.getGoalSelector()` instead of protected `goalSelector`
- No `BlockTags.SAPLINGS` on 26.2; fertile ticks `BonemealableBlock` (covers vanilla saplings)

## Leftover stubs

- `DummyBeeEffect.NONE`
- `EASTER` DummyBeeEffect
- Patriotic not registered
