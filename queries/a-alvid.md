# A-ALVID — alveary registry ids

Pre-release rename. No `minecraft:old_id` alias.

CE 1.21.1 `ApicultureBlocks.ALVEARY` maps serialized `"plain"` / `"hygro"` / `"stabiliser"` to `alveary_block` / `alveary_hygroregulator` / `alveary_stabilizer`. Local `FeatureBlockGroup` still uses `identifier("alveary")` PREFIX, so those three enum serialized names are overridden to `"block"` / `"hygroregulator"` / `"stabilizer"`. Enum constants stay `PLAIN`, `HYGRO`, `STABILISER`.

Block-entity ids match CE: `alveary_hygroregulator`, `alveary_stabilizer`. Plain BE stays `alveary` (CE uses `alveary` for the tile while the block is `alveary_block`). Menu type was already `alveary_hygroregulator`.

| Old id | New id |
|---|---|
| `reforestry:alveary_plain` | `reforestry:alveary_block` |
| `reforestry:alveary_hygro` | `reforestry:alveary_hygroregulator` |
| `reforestry:alveary_stabiliser` | `reforestry:alveary_stabilizer` |

Visual models `alveary_plain_normal` / `alveary_plain_entrance*` are not registry ids and were not renamed. Texture files `alveary.plain` / `alveary.stabiliser` keep the old CE20 dotted names.

`minecraft:mineable/axe` lists worktable plus apiary, bee_house, and all seven alveary parts (CE 1.21.1 generated axe tag). FeatureBlock does not auto-tag. Forestry wood families are still missing from that tag (pre-existing 11.9e hole). Creative tab iterates `ALVEARY.getAll()` so it follows the new ids.
