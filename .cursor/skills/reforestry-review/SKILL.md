---
name: reforestry-review
description: >-
  Reviews Re-Forestry code changes for Forestry CE parity, Fabric 26.2 APIs,
  Feature* registration, invented registry ids, missing assets/lang/recipes,
  foreign-mod dependencies, and project conventions. Use when reviewing a
  diff or PR, after implementing a feature, or when the user asks if the
  code is correct, complete, or ready.
---

# Review Re-Forestry changes

Review the actual diff (git or the files just edited). Verify claims against CE / Minecraft 26.2 via [reforestry-lookup](../reforestry-lookup/SKILL.md) — do not trust names that were typed from memory.

Format findings as:

- **Must fix** — wrong id, missing `items/*.json`, deprecated API, donor-mod dependency, compile break
- **Should fix** — missing recipe/tag/lang/tab, Mixin that has a Fabric hook, skipped DoD row with no note
- **Nice** — smaller diff, closer CE naming, extra smoke coverage

## Checklist

### Truth and ids

- [ ] Every new `reforestry:` id / texture / lang key exists in CE (as `forestry:`) or is a documented Re-Forestry-only fixture (`debug_creative_energy`)
- [ ] Packages are `com.leon1236.reforestry.{module}.*`, not leftover `forestry.*` / donor packages
- [ ] No invented species, mutations, or recipe tables

### Port quality

- [ ] CE `api/` types were ported first when they exist; impl uses them
- [ ] Registration uses `IFeatureRegistry` helpers / groups, not one-off registers for a family
- [ ] Fabric API hook used where one exists; Mixins are minimal and justified
- [ ] No `@Deprecated` 26.2 calls copied from CE (`hasChunkAt`, mutating `BoundingBox`, …)
- [ ] Forge capabilities / event bus / DeferredRegister were not copied
- [ ] Energy is Team Reborn `EnergyStorage`; items/fluids are `fabric-transfer-api-v1`
- [ ] Adopted donor code lives in our packages; `fabric.mod.json` was not given a new `depends` on that donor

### Content completeness

- [ ] `assets/reforestry/items/{id}.json` **and** `models/item/{id}.json` for new items
- [ ] Textures copied from `for textures only/`, not placeholder art
- [ ] `en_us.json` updated
- [ ] Recipes/tags/loot/tab for a playable item
- [ ] Client screens/BER registered on the module client handler

### Hygiene

- [ ] No Java comments
- [ ] Diff is scoped to the stage
- [ ] `files/implemented-features.md` only updated if the stage actually landed
- [ ] Fabric-vs-CE choices recorded in `queries/` when they will be needed again

DoD table: [definition-of-done.md](../reforestry-port/definition-of-done.md).

## After the review

If this was post-implement: list remaining Must-fix items and offer to fix them.  
If the user asked “is it done?”, answer against the DoD, not against “it compiles”.
