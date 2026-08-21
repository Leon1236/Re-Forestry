# Lepidopterology D2 — entity / item models

## Item re-flutter

Forestry CE uses Forge `ItemButterflyGE.onEntityItemUpdate`. Fabric item API has no item-entity tick event (`fabric-item-api-v1`). Re-Forestry already has `ItemEntityMixin` for backpack stow, so D2 extends that mixin with a `tick` inject that calls `ItemButterflyGE.onEntityItemUpdate`. Choice: Mixin on `ItemEntity`, not a new Fabric event.

## Other Fabric-vs-CE notes

- Entity attributes: `FabricDefaultAttributeRegistry.register(..., Mob.createMobAttributes())`. Our `FeatureEntityType` has no attribute argument like CE.
- Dimension cancel: CE listens to `EntityTravelToDimensionEvent`. Fabric `ServerEntityLevelChangeEvents` fires after the change, so the entity overrides `canTeleport` and `canUsePortal` instead.
- Item model: CE loader id `forestry:butterfly_ge` is a Forge geometry loader. Re-Forestry uses `reforestry:butterfly_species` select (same pattern as saplings), not a Forge loader.
- Home APIs: `setHomeTo` / `getHomePosition` / `getHomeRadius` (26.2), not `restrictTo`.
- Nursery helper `ButterflyNurseryHelper.getOrCreateNursery` only returns an existing `IButterflyNursery` tile. D3 wires `TileLeaves`.
