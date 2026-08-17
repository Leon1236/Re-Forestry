# Registration and content files

Registry namespace is always `reforestry`. Module id is `reforestry:core`, `reforestry:factory`, etc.

```java
private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("factory"));
```

`FeatureItem` / `FeatureBlock` already call `Item.Properties.setId` / block equivalent (`seededProperties`). Do not skip that on 26.2.

## Helpers on `IFeatureRegistry`

| Method | Use |
|---|---|
| `item(name)` / `item(name, ctor)` | One item |
| `itemGroup(ctor, Enum.values())` | Family of items |
| `block(name, ctor, itemCtor)` | One block ± BlockItem |
| `blockGroup(ctor, Enum.values()).item(...).create()` | Family of blocks |
| `blockEntityType` / `menuType` / `recipeType` / `dataComponent` / `creativeTab` / `entity` / `particleType` | Matching vanilla registry |

### `blockGroup` / `itemGroup` ids

`IdentifierType` (default `TYPE_ONLY` = subtype name only):

| Type | `identifier("log")` + subtype `oak` |
|---|---|
| `TYPE_ONLY` | `oak` |
| `PREFIX` (`.identifier("log")` default) | `log_oak` |
| `SUFFIX` | `oak_log` |

Match CE's actual registry path. Confirm with lookup — do not guess PREFIX vs SUFFIX.

## Module wiring

1. `features/{Module}Items.java` / `Blocks.java` / `Tiles.java` / `MenuTypes.java` — static `REGISTRY.*` fields.
2. `ModuleX.init()` constructs those classes (empty `init()` on the feature class is fine if static fields register on class load — match the module you are extending).
3. New module: implement `IForestryModule`, annotate `@ForestryModule`, add to `ReForestry.onInitialize()` load list, config-gate via existing `ModuleConfig` (non-core).
4. Client: `registerClientHandler` → `MenuScreens.register`, BER, tints.

## Assets

| File | Required for |
|---|---|
| `assets/reforestry/textures/item/{id}.png` (or block/) | Copied from `for textures only/` |
| `assets/reforestry/models/item/{id}.json` | Classic model |
| `assets/reforestry/items/{id}.json` | **MC 26.2 item definition** (missing = invisible item) |
| `assets/reforestry/blockstates/{id}.json` + `models/block/` | Blocks |
| `assets/reforestry/textures/gui/...` | Screens — copy CE GUI pngs |
| `assets/reforestry/lang/en_us.json` | Display names / tooltips / GUI |

Generate item definitions:

```bash
python3 tools/generate_item_model_definitions.py --root . --namespace reforestry --apply
```

Tinted items (combs, pollen): pass `--tints tints.json` as in the script header.

## Data

| Path | Role |
|---|---|
| `data/reforestry/recipe/...` | Crafting + machine recipes |
| `data/reforestry/tags/item/` and `block/` | Our tags |
| `data/reforestry/loot_table/` | Block drops |
| `c:` tags | Common convention (CE `forge:` → `c:`) |

Extract, do not retype. Reuse `tools/extract_*_recipes.py` / `tools/generate_*_species.py`. New repeatable extractors go in `tools/` with a one-line usage comment at the top of the file.

## Lang keys

Follow CE, swap namespace:

- `item.reforestry.{id}`
- `block.reforestry.{id}`
- `item.reforestry.{id}.tooltip`
- `gui.reforestry.*`
- `itemGroup.reforestry.{tab}`
- JEI: `for.jei.description.*` where factory already uses that pattern

Do not delete other locale files; do update `en_us`.

## Creative tab

Add items to the module tab in CE-like order (`CoreCreativeTabs`, `FactoryCreativeTabs`, …).
