# Parallel A — CE bee-effect brief (2026-08-17)

Source: CE 1.21.1 clone `MarkDown_Maker/Finished_github_clone/2026-08-17/thedarkcolour-ForestryCE`. Keep Java plugin registration (do not port 1.21.1 datapack codecs).

## Dummy → CE class

| Id | Class | Throttle ctor (dominant, ticks, requiresQueen, combinable) |
|---|---|---|
| radioactive | RadioactiveBeeEffect | true, 40, false, true |
| creeper | CreeperBeeEffect | **true**, 20, false, true |
| ignition | IgnitionBeeEffect | false, 20, false, true |
| reanimation | ResurrectionBeeEffect (bones/flesh/blaze) | true, 40, true, true |
| resurrection | ResurrectionBeeEffect (gunpowder/pearl/string/ghast/dragon) | true, 40, true, true |
| repulsion | RepulsionBeeEffect + AIAvoidPlayers | false, 100, true, true |
| fertile | FertileBeeEffect | **false**, 6, true, false |
| mycophilic | FungificationBeeEffect | **true**, 20, false, false |
| sifter | SifterBeeEffect (dedicated) | **true**, 550, true, true |
| glow_berry_grow | GlowBerryGrowEffect (cave_vines tag) | false, 200, true, true |
| rejuvenation | AgingBeeEffect aging=false | NonStacking |
| chronophage | AgingBeeEffect aging=true | NonStacking |
| guardian | GuardianBeeEffect | **true**, 1200, true, true |
| phasing | PhasingBeeEffect | **true**, 40, true, true |
| ascension | AscensionBeeEffect (levitation) | **true**, potion throttle 200 |
| sculk | SculkSpreadBeeEffect | false, 200, true, true |

EASTER/NONE stay dummy. Skip patriotic.

Bold dominant flags differ from local DummyBeeEffect.

## Jubilance

Specialties only if **both** active and inactive species are jubilant; then **primary** specialties × speed. Default = exact temp+humidity. Hermit = no `Mob` in territory (monastic/secluded/hermitic).

## Tracker

Analyze + offspring `registerBirth` exist. `tryMate` does not register the new queen — add that.

## 26.2

`level.isLoaded`, `hurtServer`, no NeoForge, `igniteForSeconds` is seconds, map aging to `BEE_LIFE_USED`.
