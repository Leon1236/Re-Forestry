# Factory server/client log review (2026-07-25)

## Crash fixed (startup)

`NullPointerException: Components not bound yet` in `ReforestryPlugin.registerCircuits` during `ModuleCore.init`.

**Fix:** defer `ItemStack` creation until `CommonLifecycleEvents.TAGS_LOADED`:
- circuit registration in `ModuleCore`
- `FuelManager` seeding in `ModuleFactory`

## Follow-up fixes (same session)

After restart with fixes: **0** `Couldn't parse data file 'reforestry:…'` on server.

Also fixed:
- `LegacyIngredientCodec` (registry-aware) + optional empty fabricator `plan` via `Optional<Ingredient>`
- Moistener product via `RecipeItemAmount` (avoids “components not bound yet”)
- Fruit / glass / raw tin tags; wheat fermenter uses item not fake tag
- Coconut squeezes `reforestry:milk` (added fluid); external-alloy smelter recipes parked under `data/reforestry/_disabled_smelter_external/`
- Fabricator blockstate no longer uses `facing`; basic fluid blockstates/models added

### Remaining polish (non-fatal)

- Missing textures: smelter, debug energy blocks
- Auth noise (offline / Realms 401) — ignore
- Fluid cube models are placeholders (not true liquid rendering)
