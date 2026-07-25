# Wild hive spawn: only forest hives

## Symptom
New worlds only had `reforestry:beehive_forest` (and similar forest hives under trees). Desert/jungle/snow/swamp/meadows hives were missing or extremely rare.

## Cause
Hive placement filters by bee climate (`HiveDecorator` → `isGoodTemperature` / `isGoodHumidity`). Biome→climate was filled only on `SERVER_STARTED`, **after** spawn chunks already generated.

While the climate cache was empty, every biome defaulted to **NORMAL / NORMAL**. Only forest (and meadows) bees match that. Forest also has genChance `6.0` and places under trees, so wooded spawn areas looked like “forest hives only.” Jungle/desert species never passed the climate check.

CE fills climate on Forge `TagsUpdatedEvent` (tags ready before worldgen). We had no Fabric equivalent wired.

## Fix
1. `ModuleCore`: also reload climate on `CommonLifecycleEvents.TAGS_LOADED` (server side, `!client`) — same timing as CE’s tags event.
2. `ForestryClimateManager`: if a biome isn’t in the cache yet, resolve temperature/humidity live from tags + `getBaseTemperature` / downfall (so early worldgen still works).
3. Use `HashMap` for biome keys (and tree biome cache) instead of `IdentityHashMap`.

## How to verify
Create a **new** world (old chunks keep wrong hives). Check:
- Plains → meadows hive on dirt
- Desert → modest hive on sand
- Jungle → tropical hive in trees
- Swamp → marshy on dirt
- Snowy → wintry
