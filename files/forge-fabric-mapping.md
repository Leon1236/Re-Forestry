# Forge/NeoForge → Fabric API mapping — foundation layer (core, api, apiculture, arboriculture)

Built from actual import statements in `thedarkcolour-ForestryCE` (Forge) and `thedarkcolour-Immersive-Forestry` (NeoForge), cross-checked against the real module list in `FabricMC-fabric-api`. Not from memory — every Fabric module name below exists as a top-level subproject in that repo.

| Concern | Forge (ForestryCE) | NeoForge (Immersive-Forestry) | Fabric equivalent |
|---|---|---|---|
| Registries | `net.minecraftforge.registries` — DeferredRegister/RegistryObject | `net.neoforged.neoforge.registries`, same pattern | Direct `Registry.register()` at mod-init — no deferred wrapper needed, Fabric's registries are open earlier. `fabric-registry-sync-v0` for sync edge cases. |
| Item/fluid/energy storage (Forge "Capabilities") | `net.minecraftforge.items` (IItemHandler), fluid capability via `net.minecraftforge.fluids` | consolidated under `net.neoforged.neoforge.capabilities` | `fabric-transfer-api-v1` — `ItemStorage`/`FluidStorage`/generic `Storage<T>` via `BlockApiLookup`. No first-party Fabric energy API; Team Reborn's `EnergyStorage` (already in mods.db) is the community-standard `Storage<T>`-shaped equivalent. |
| Block/Item property builders | vanilla `Block.Properties`/`Item.Properties`, lightly extended | same, lightly extended | `fabric-object-builder-api-v1` — adds the extension points Fabric needs for the same custom-property patterns. |
| Event bus | `net.minecraftforge.eventbus`, `@SubscribeEvent`, `net.minecraftforge.event.*` | `net.neoforged.bus`, same annotation pattern, `net.neoforged.neoforge.event.*` | No generic bus — each event is its own typed callback: `fabric-lifecycle-events-v1`, `fabric-entity-events-v1`, `fabric-events-interaction-v0`. Anything without a Fabric API hook needs a Mixin. |
| Mod lifecycle | `net.minecraftforge.fml` (`@Mod`, `FMLCommonSetupEvent`) | `net.neoforged.fml`, same shape | `ModInitializer` / `ClientModInitializer` / `DedicatedServerModInitializer` entrypoints declared in `fabric.mod.json`. |
| Networking | `net.minecraftforge.network` (SimpleChannel) | NeoForge networking, same vanilla `CustomPacketPayload` base as 1.20.5+ | `fabric-networking-api-v1`, built on the same vanilla `CustomPacketPayload` records — closer to a direct swap than the others since both loaders now sit on vanilla's own payload system. |
| Client rendering hooks | `net.minecraftforge.client` (color handlers, render layers) | `net.neoforged.neoforge.client` | `fabric-rendering-v1`, `fabric-renderer-api-v1`, `fabric-rendering-fluids-v1`. |
| Recipe/inventory display | `mezz.jei.api` (JEI) | same | JEI has a Fabric build; REI (Roughly Enough Items) is the Fabric-native alternative if you'd rather not lean on JEI's Fabric layer. |
| Datagen helper | `thedarkcolour.modkit.data.DataHelper` — the author's own small utility, build-time only | same | Low risk: it's a datagen convenience wrapper, not a runtime API. Since recipes/assets are being reused directly rather than regenerated, this dependency likely doesn't need porting at all. |

## Notable finding
CE and Immersive-Forestry are structurally near-identical at this layer — same file counts, same non-Forge dependencies (JEI, fastutil, Mojang libs) down to almost the same call counts. The difference is almost entirely the Forge→NeoForge package rename (`net.minecraftforge.*` → `net.neoforged.neoforge.*`/`net.neoforged.api`, `net.minecraftforge.eventbus` → `net.neoforged.bus`). So "compare per module, pick the better fit" will mostly come down to code recency/quality per module rather than architectural differences — worth flagging as each module comes up, not a blocking concern now.

## Open item
`thedarkcolour.modkit` isn't in mods.db and isn't a standard Forge/NeoForge library — it's the author's personal datagen helper (13-14 references in core+api, all build-time). Flagged as low-risk per above; no action needed unless a specific usage turns out to matter at runtime.
