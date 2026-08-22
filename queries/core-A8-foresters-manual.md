# A8 — Forester's manual

## Decision: stub item (not Patchouli yet)

**Choice:** register `reforestry:foresters_manual` as a **stub**. Right-click sends a clear chat message; no custom book GUI.

**Why not Patchouli on this stack**

| Check | Result |
|---|---|
| Target | Minecraft `~26.2` (Fabric Loom) |
| Patchouli Maven (`maven.blamejared.com`) | Only `vazkii.patchouli:patchouli-fabric:26.1-94` (+ SNAPSHOT) |
| Fabric/CurseForge/Modrinth | No Patchouli build tagged for **26.2** (2026-07-26) |
| Hard-depend on 26.1 | Would fight `minecraft: ~26.2` and risk loader/API mismatch |

Plan rule: do **not** invent a full custom book UI silently. Stub is the allowed A8 fallback until Patchouli ships 26.2.

## CE behaviour (source of truth)

- `ForestersManualItem` → `PatchouliAPI.get().openBookGUI(serverPlayer, itemId)` + page-turn sound.
- Book datapack: `data/.../patchouli_books/foresters_manual/book.json` with `custom_book_item`, `dont_generate_book`, `use_resource_pack`.
- Content under `assets/.../patchouli_books/foresters_manual/...`.
- Recipes (shapeless book + one of): `honey_drop`, `#minecraft:saplings`, `butterfly_ge`.
- Advancement reward loot `grant_guide` gives the item.

## What we ship now

- Item class `ForestersManualItem` (CE name), registry id `foresters_manual`.
- Model/texture/lang/tab already present or wired; recipes for honey_drop + saplings.
- Butterfly recipe **skipped** until Track D (`butterfly_ge` unregistered).
- Patchouli book JSON + entries already under `assets/reforestry/patchouli_books/` and `data/reforestry/patchouli_books/` — inert without Patchouli; kept for a later soft/hard depend.

## Upgrade path (when Patchouli 26.2 exists)

1. Add BlameJared maven + `modImplementation` / optional `depends` or `suggests` in `fabric.mod.json`.
2. Change `use()` to call `PatchouliAPI.get().openBookGUI(...)` (Fabric item id via registry).
3. Remove stub chat key; smoke: open book pages for core/beekeeping entries.
