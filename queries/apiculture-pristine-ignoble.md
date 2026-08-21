# Pristine / ignoble bee stock (2026-08-18)

Primary: Forestry CE 1.21.1 `Bee.java`, `ItemBeeGE.java`, `BlockBeeHive.getDrops`, `BeeAnalyzerPlugin`.
Same formula as classic 1.12 Forestry (`isNatural` / ignoble decay). Not a genetic chromosome.

## What it is

- **Pristine** = wild-caught original line. Princesses always produce a princess.
- **Ignoble** = hive-caught with a chance (usually 0.7 on common hives). After generation ~96–108, each cycle has a 2% × genetic-decay chance to produce **no princess** (line dies).
- Stored on the item (`bee_pristine`, `bee_generation`), not in the genome.
- Default missing component = pristine (creative bees, `/give` bees). Tooltip and analyzer skip stock if the item has no genome.
- Bee houses set genetic decay to 0, so ignoble lines never die there.
- Swarm-hive princesses from the alveary swarmer are always ignoble.

## Local gaps that were closed

Wild hive scoop loot never rolled `getIgnobleChance`, so every wild princess was pristine. Tooltip and analyzer also omitted stock / captivity generations.

## Still not this module

- Extra Bees (Binnie) is a later addon (`reforestry:extra_bees`), not core Forestry bees.
- Beekeeper villager in CE uses the escritoire as its job site; escritoire is still deferred.
- CE `bee_effect_patriotic` is commented out in CE 1.21.1; patriotic bees produce fireworks instead.
