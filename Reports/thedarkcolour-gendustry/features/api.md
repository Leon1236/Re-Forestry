# thedarkcolour-gendustry — api

## Summary

The `thedarkcolour.gendustry.api` package is **minimal**: two source files, one public class, one item tag. It exists as a small **addon-facing surface** for Industrial Apiary upgrade slot validation and datagen, not as a full plugin or machine API. Upgrade *behavior* (climate modifiers, energy cost, fertility, etc.) lives entirely outside this package in `item/` and `blockentity/` with a hardcoded enum switch and an explicit comment that a real upgrade API is not offered yet.

| Metric | Value |
|---|---|
| Source files | 2 (`GendustryTags.java`, `package-info.java`) |
| Public types | 1 (`GendustryTags`) |
| Tag constants | 1 (`GendustryTags.Items.UPGRADES` → `gendustry:upgrades`) |
| In-repo consumers | 2 (`ModTags`, `IndustrialApiaryInventory`) |
| External consumers | None found in clone or MCP search |

Modern Gendustry (1.20.1 Forge) ships the entire mod as a single jar; there is no separate `gendustry-api` artifact in `build.gradle`.

---

## Player / API surface

### Players

Players never touch this package directly. They interact with **Industrial Apiary upgrade slots** (slots 2–5), which accept any item in the `gendustry:upgrades` tag. That tag covers 17 standard and 6 elite upgrade items (23 total).

### Addon / interop developers

The only published contract is:

```java
GendustryTags.Items.UPGRADES   // TagKey<Item> → gendustry:upgrades
```

**What this enables:** a third-party mod can register its own item into `#gendustry:upgrades` (via datapack or tag provider) and the Industrial Apiary inventory will **accept** it in upgrade slots.

**What this does *not* enable:** the item will not gain any bee-modifier behavior unless it is a `GendustryUpgradeItem` with a known `GendustryUpgradeType` or `EliteGendustryUpgradeType`. `IndustrialApiaryBeeModifier.recalculate()` explicitly switches on those enums and includes the note: *"Hardcoded for now. If you want an API, open an Issue on GitHub."*

There are **no** interfaces, events, registries, or Forestry-plugin hooks in this package.

---

## Architecture

```
thedarkcolour.gendustry.api/
├── package-info.java          # @MethodsReturnNonnullByDefault, @FieldsAreNonnullByDefault, @ParametersAreNonnullByDefault
└── GendustryTags.java
    └── Items (nested)
        └── UPGRADES : TagKey<Item>
            └── itemTag("upgrades") → ItemTags.create(Gendustry.loc("upgrades"))
```

### Call graph (in-repo)

```
GendustryTags.Items.UPGRADES
    │
    ├─► ModTags.addItemTags()          [datagen — populate tag values]
    └─► IndustrialApiaryInventory     [runtime — canSlotAccept() for slots 2–5]
            canSlotAccept()
                ├─ duplicate upgrade type check (same Item in another slot → reject)
                └─ stack.is(GendustryTags.Items.UPGRADES)
```

### Design intent

The package follows the common Forestry/NeoForge pattern of a top-level `*Tags` holder with nested `Items` / `Blocks` inner classes (compare Re-Forestry's `ReforestryBiomeTags`). Nullability defaults on `package-info.java` signal that the package is intended for stable external reference.

The actual upgrade **type contract** (`IGendustryUpgradeType`: `maxStackSize()`, `energyCost()`) and item class (`GendustryUpgradeItem`) remain in `thedarkcolour.gendustry.item` — **not** exported through `api/`.

---

## Data & assets

| Asset | Path | Role |
|---|---|---|
| Generated item tag | `src/generated/resources/data/gendustry/tags/items/upgrades.json` | Lists all 23 upgrade item ids |
| Datagen provider | `data/ModTags.java` | Adds `GItems.UPGRADE` + `GItems.ELITE_UPGRADE` enum groups to the tag at build time |

Tag contents (generated):

- **Standard upgrades (17):** `automation`, `heater`, `cooler`, `humidifier`, `dryer`, `pollination`, `scrubber`, `nether`, `lifespan`, `lighting`, `productivity`, `weatherproof`, `sieve`, `sky`, `stabilizer`, `territory`, `immutable` — each suffixed `_upgrade`.
- **Elite upgrades (6):** `mutation`, `activity_simulator`, `productivity`, `territory`, `youth`, `fertility` — each suffixed `_elite_upgrade`.

No block tags, fluid tags, or entity tags are defined in this package.

---

## Dependencies

### Direct (within `api/`)

| Dependency | Usage |
|---|---|
| `net.minecraft.tags.ItemTags` | Tag creation helper |
| `net.minecraft.tags.TagKey` | Tag key type |
| `net.minecraft.world.item.Item` | Tag generic |
| `thedarkcolour.gendustry.Gendustry` | `Gendustry.loc(path)` → `ResourceLocation("gendustry", path)` |

### Indirect (consumers of api)

| Consumer | Depends on |
|---|---|
| `ModTags` | `GendustryTags`, `GItems`, ModKit `MKTagsProvider` |
| `IndustrialApiaryInventory` | `GendustryTags`, Forestry `IBeeHousingInventory`, genetics capability filters |

The `api` package itself has **zero** Forestry API imports — it is the thinnest possible layer above vanilla tag types.

---

## Notable algorithms / contracts

### Tag id contract

- Registry id: **`gendustry:upgrades`**
- Java constant: **`GendustryTags.Items.UPGRADES`**
- Must remain stable for datapack/addon tag extensions.

### Industrial Apiary slot acceptance (`IndustrialApiaryInventory.canSlotAccept`)

For slots `UPGRADE_SLOT_START` (2) through `UPGRADE_SLOT_START + UPGRADE_SLOT_COUNT - 1` (5):

1. Iterate all four upgrade slots; if another slot already holds the same `Item`, reject (one slot per upgrade **type**, stacks allowed up to enum `maxStackSize`).
2. Accept iff `stack.is(GendustryTags.Items.UPGRADES)`.

Queen/drone/output slot rules are unrelated to this package.

### Upgrade behavior (outside api — reference only)

`IndustrialApiaryBeeModifier.recalculate()` walks upgrade slots, sums `energyCost * count` from `IGendustryUpgradeType`, and applies hardcoded modifier effects per enum case. This is the **real** gameplay contract but is intentionally **not** in `api/`.

Key related types (not in this package):

| Type | Package | Role |
|---|---|---|
| `IGendustryUpgradeType` | `item` | `maxStackSize()`, `energyCost()` |
| `GendustryUpgradeType` | `item` | 17 standard upgrade enums |
| `EliteGendustryUpgradeType` | `item` | 6 elite upgrade enums |
| `GendustryUpgradeItem` | `item` | Item wrapper; tooltip shows energy cost + stack limit |

---

## Port relevance to Re-Forestry

### Current Re-Forestry state

- **No gendustry module** exists yet in `src/` (no `GendustryTags`, no `reforestry:gendustry` content).
- Core tag pattern already established in `com.leon1236.reforestry.api.core.ReforestryBiomeTags` — nested `Items` / `Blocks`, `TagKey.create(Registries.ITEM, ReForestry.id(...))`.
- `files/addon-integration-mapping.md` records the plan: Gendustry becomes a **built-in toggleable module** (proposed id `reforestry:gendustry`), porting thedarkcolour's 1.20.1 structure.

### Recommended port mapping

| Gendustry (Forge) | Re-Forestry (Fabric) |
|---|---|
| `thedarkcolour.gendustry.api.GendustryTags` | `com.leon1236.reforestry.gendustry.api.GendustryTags` (or `api.gendustry.GendustryTags` under module) |
| `gendustry:upgrades` | `reforestry:gendustry/upgrades` (keep path discoverable; namespace follows built-in module decision) |
| `ItemTags.create(Gendustry.loc(...))` | `TagKey.create(Registries.ITEM, ReForestry.id("gendustry/upgrades"))` |
| `ModTags` datagen via ModKit | Fabric datagen tag provider or JSON under `data/reforestry/tags/item/` |
| `stack.is(GendustryTags.Items.UPGRADES)` | Same call site pattern in Industrial Apiary inventory port |

### Effort assessment

**Trivial.** The entire `api` package ports in minutes. The meaningful work is elsewhere (machines, upgrades, fluids, recipes).

### API-first decision for Re-Forestry

When implementing the gendustry module, consider whether to:

1. **Mirror as-is** — tag-only public surface; keep upgrade types internal.
2. **Expand** — promote `IGendustryUpgradeType` (or a registry + `IBeeModifier` factory) into `api/` so addon upgrades can actually *do* something, not just fit in slots.

Option 2 aligns better with Re-Forestry's `api-first` convention (`IForestryPlugin`, public interfaces before impl) but is **scope beyond** what the donor mod ships today.

---

## Source map

| File | Lines | Description |
|---|---:|---|
| `src/main/java/thedarkcolour/gendustry/api/package-info.java` | 4 | Package-level nullability annotations |
| `src/main/java/thedarkcolour/gendustry/api/GendustryTags.java` | 17 | Item tag constants holder |

### In-repo references to `thedarkcolour.gendustry.api`

| File | Usage |
|---|---|
| `data/ModTags.java` | Datagen: populate `UPGRADES` tag from item groups |
| `blockentity/IndustrialApiaryInventory.java` | Runtime: upgrade slot filter |

### Related files (outside package, behavior-critical)

| File | Relevance |
|---|---|
| `item/IGendustryUpgradeType.java` | Upgrade stats contract (internal) |
| `item/GendustryUpgradeType.java` | Standard upgrade enum + stack/energy |
| `item/EliteGendustryUpgradeType.java` | Elite upgrade enum |
| `item/GendustryUpgradeItem.java` | Upgrade item implementation |
| `registry/GItems.java` | Registers upgrade item groups |
| `blockentity/IndustrialApiaryBeeModifier.java` | Hardcoded upgrade effect aggregation |
| `src/generated/resources/data/gendustry/tags/items/upgrades.json` | Generated tag data |

---

## Open questions / gaps

1. **No upgrade registration API.** Addons can extend the tag but cannot register new upgrade *effects* without patching `IndustrialApiaryBeeModifier`. The source explicitly defers this to a GitHub issue.

2. **`IGendustryUpgradeType` not in `api/`.** If Re-Forestry wants third-party industrial apiary upgrades, this interface (or a richer registry) should move to the public API layer during port — the donor mod does not.

3. **No block/fluid/recipe tags in api.** All other gendustry tagging (e.g. `mineable/pickaxe` for machines) is inline in `ModTags` using vanilla tags, not gendustry api constants.

4. **No separate api artifact.** Unlike some Forestry-era addons, modern gendustry does not publish an api jar; the two-class package is bundled in the main mod. Re-Forestry can keep the same shape as a built-in module.

5. **Historical api richness.** Original 1.7/1.12 Gendustry (TeamBD) had a larger public API for machines and genetics; thedarkcolour's CE rewrite collapsed that to this tag holder. Do not assume old wiki/API docs apply to the 1.20.1 source.

6. **Namespace when built-in.** `files/addon-integration-mapping.md` proposes `reforestry:gendustry` module ids under a single mod namespace. Final tag id (`reforestry:gendustry/upgrades` vs `reforestry:upgrades`) should be decided before datagen to avoid breaking datapack references.

7. **No tests or validation** that tag membership and enum coverage stay in sync — correctness relies on `ModTags` adding exactly the items from both upgrade enum groups. A port should keep datagen as the single source of truth.
