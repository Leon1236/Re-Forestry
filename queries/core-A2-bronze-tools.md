# A2 — Survivalist bronze tools

## API
**No CE public API** for tool remnants (`HasRemnants` is internal `forestry.core.items`). No `api/items/IItemWithRemnants`. Remnant give is internal `ItemRemnants` + Fabric `CustomDamageHandler`.

## Fabric vs CE
| Topic | Choice |
|---|---|
| Tool constructors | MC 26.2: `Item.Properties.pickaxe/sword` + `ShovelItem`/`AxeItem`/`HoeItem` (no `PickaxeItem`/`SwordItem` classes) |
| Remnant on break | Fabric `customDamage` → give broken item (CE overrode Forge `damageItem`) |
| Durability / speed / attack bonus | CE `ToolTier.SURVIVALIST`: 200 / 7.0 / 2.5 / enchant 10 |
| Attack baselines | CE: pick 1 / shovel 1.5 / axe 5.5 / sword 3 / hoe -2; speeds match CE |
| Incorrect-for-drops | Immersive: `INCORRECT_FOR_IRON_TOOL` (CE old level 3 = diamond; iron is the modern bronze-ish middle) |
| Repair | `#c:ingots/bronze` |

## Remnant map
pickaxe→`broken_bronze_pickaxe`, shovel→`broken_bronze_shovel`, axe→`broken_axe`, sword→`broken_sword`, hoe→`broken_hoe`
