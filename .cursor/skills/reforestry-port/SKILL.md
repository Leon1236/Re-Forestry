---
name: reforestry-port
description: >-
  Ports Forestry CE / Immersive Forestry features into Re-Forestry on Fabric
  26.2: API-first, Feature* registration, assets, recipes, lang, compile, and
  status docs. Use when implementing a roadmap item, adding a block/item/
  machine/module/GUI, translating CE or IF Java, adopting another mod's code
  into our packages, closing an item gap, or wiring a new IForestryPlugin
  registration.
---

# Port a Re-Forestry feature

Copy this checklist and track it:

```
Port progress:
- [ ] 1. Status + existing notes
- [ ] 2. Lookup (never invent ids)
- [ ] 3. API first (if CE has api/)
- [ ] 4. Register + implement
- [ ] 5. Assets, lang, recipes, tags
- [ ] 6. Compile
- [ ] 7. Smoke (world test if server is up)
- [ ] 8. Docs
```

Explain changes to the user in plain language. Keep the diff small and reviewable.

## 1. Status

Read `files/implemented-features.md` (**Next up** + the matching phase table).  
If a `queries/{id}.md` exists for this stage, reuse it.  
Stage checklists: `queries/item-gap-implementation-plan.md`.

Do not treat `CLAUDE.md` as done/not-done.

## 2. Lookup

Follow [reforestry-lookup](../reforestry-lookup/SKILL.md). Minimum:

- CE class names, registry ids, texture paths, lang keys
- Whether CE has `forestry.api.*` types for this feature
- Minecraft 26.2 replacements for any copied vanilla call

## 3. API first

If CE exposes `forestry.api.*` for the feature, port those interfaces under `com.leon1236.reforestry.api.*` **before** the impl. Implementation dogfoods the API.

Plugin hooks go on `IForestryPlugin` and run via the `reforestry:plugin` entrypoint (`ReforestryPlugin` / `PluginManager`).

New playable systems belong in a module (`IForestryModule` + `@ForestryModule`). Wire it in `ReForestry.onInitialize()` `ModuleManager.INSTANCE.load(...)`. Client: `registerClientHandler`.

## 4. Register + implement

See [register.md](register.md). Machines: [machine-pattern.md](machine-pattern.md).

Rules of thumb:

- Mirror CE registry ids (`forestry:` → `reforestry:`). Primary CE is **1.21.1**; keep our existing `com.leon1236.reforestry.{module}` layout (do not mass-rename to CE 1.21.1 packages).
- Use `REGISTRY.item` / `block` / `blockGroup` / `itemGroup` — not one-off `Registry.register` when a Feature helper fits.
- Prefer Fabric API events over Mixins. Mixin only when no hook exists; keep it tiny; note the choice in `queries/` if non-obvious.
- No Java comments.
- Forge → Fabric: `files/forge-fabric-mapping.md` (capabilities → `fabric-transfer-api-v1`, energy → Team Reborn `EnergyStorage`, no event bus).
- Adopting another mod: copy into `com.leon1236.reforestry.*`. Do **not** add the donor to `fabric.mod.json` `depends` unless this change *is* optional interop (`compat/` + `FabricLoader.isModLoaded`).

## 5. Assets, lang, recipes, tags

Definition of done: [definition-of-done.md](definition-of-done.md).

- Textures: copy from `for textures only/...` (CE relative path `assets/forestry/` → `assets/reforestry/`). Never invent art.
- Every item needs **both** `assets/reforestry/models/item/{id}.json` **and** `assets/reforestry/items/{id}.json` (MC 26.2 item definition). Prefer `python3 tools/generate_item_model_definitions.py`.
- Lang: `en_us.json` is required. Keys follow CE with our namespace (`item.reforestry.{id}`, `gui.reforestry.*`, `itemGroup.reforestry.{tab}`).
- Bulk recipes/species: extend a script in `tools/` — never type tables from memory.
- Namespace rewrites: `python3 tools/rename_namespace.py`, not hand edits.

## 6. Compile

```bash
./gradlew compileJava
```

Fix errors before calling the feature done. If you touched client-only types, also run `./gradlew compileClientJava` when that task exists, or compile the full `classes` task.

## 7. Smoke

If a dedicated server is running, use the `minecraft-world-test` skill (`give`, `place_block`, `run_command_and_capture_log`).  
If it is not running, say so and list the exact in-game checks the user should do. Do not claim play-tested.

## 8. Docs

When the stage actually landed:

1. Move it from **Next up** into the phase table in `files/implemented-features.md`.
2. Bump **Last updated**.
3. Write `queries/{id}.md` only if there is a Fabric-vs-CE choice worth reusing (tag mapping, skipped recipes, deprecated API swap).

## Nearby gold paths (copy these, do not invent new shapes)

| Kind | Start here |
|---|---|
| Simple item | `core/features/CoreItems.java` |
| Item group / enum subtypes | `FeatureItemGroup` + `IItemSubtype` |
| Machine block family | `factory/features/FactoryBlocks.java` |
| Tile + energy/fluids | `factory/tiles/TileCentrifuge.java` / `TileStill.java` |
| Menu + screen | `factory/gui/ContainerCentrifuge.java` + `factory/client/ScreenCentrifuge.java` |
| Recipe JSON + type | `factory/features/FactoryRecipeTypes.java` + `data/reforestry/recipe/` |
| Backpack / storage | `storage/` (already adopted TB into our packages) |
| Module shell | `storage/ModuleStorage.java` or `factory/ModuleFactory.java` |
