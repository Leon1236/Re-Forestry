# MC 26.2 deprecated API replacements (chunk / BoundingBox)

Verified against MCP `Minecraft-26.2` (`LevelReader`, `Level`, `LevelAccessor`, `BoundingBox`).

| Deprecated | Replacement |
|---|---|
| `LevelReader.hasChunkAt(BlockPos)` | `Level.isLoaded(pos)` when you have `Level`; else `getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z))` |
| `BoundingBox.encapsulate(BlockPos\|BoundingBox)` | `BoundingBox.encapsulating(a, b)` (immutable; assign result) |
| `BoundingBox.move(...)` | `BoundingBox.moved(...)` |

Fixed call sites: `FeatureHelper`, `TreeGrowthHelper`, `TreeContour`. Rule: `.cursor/rules/reforestry-mc26-apis.mdc`.
