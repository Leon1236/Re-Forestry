# ARB-11.8e — Feature shapes batch B (2026-07-25)

Ported remaining CE Feature classes (tropical / palm / conifer / giants) into `com.leon1236.reforestry.arboriculture.worldgen` and wired all 50 species in `DefaultTreeSpecies`. Deleted `SimpleTreeGenerator`; `TileSapling` now only uses species `FeatureBase` / vanilla Feature place (CE parity).

## Diff vs CE `worldgen/`

CE had 45 `Feature*` shape classes (+ helpers). Local after 11.8d: 14 temperate + `FeatureTreeVanilla`. Batch B added 33:

Acacia, Balsa, Baobab, Camelthorn, Cocobolo, Coconut, Date, Ebony, Fir, Giganteum, Ginkgo, Greenheart, Ipe, Jacaranda, Jungle, Kapok, Kauri, Larch, Lemon, Macrocarpa, Mahoe, Mahogany, Olive, Orange, Padauk, Papaya, Pewen, Pine, Sequoia, Spruce, Teak, Wenge, Zebrano.

## Species → Feature (batch B only; batch A unchanged)

| Species id | Feature |
|---|---|
| tree_jacaranda | FeatureJacaranda |
| tree_ipe | FeatureIpe |
| tree_ginkgo | FeatureGinkgo |
| tree_spruce | FeatureSpruce |
| tree_larch | FeatureLarch |
| tree_pine | FeaturePine |
| tree_fir | FeatureFir |
| tree_macrocarpa | FeatureMacrocarpa |
| tree_sequoia | FeatureSequoia |
| tree_giant_sequoia | FeatureGiganteum |
| tree_pewen | FeaturePewen |
| tree_kauri | FeatureKauri |
| tree_jungle | FeatureJungle |
| tree_teak | FeatureTeak |
| tree_kapok | FeatureKapok |
| tree_balsa | FeatureBalsa |
| tree_orange | FeatureOrange |
| tree_ebony | FeatureEbony |
| tree_sipiri | FeatureGreenheart |
| tree_lemon | FeatureLemon |
| tree_zebrawood | FeatureZebrano |
| tree_mahogany | FeatureMahogany |
| tree_coconut | FeatureCoconut |
| tree_papaya | FeaturePapaya |
| tree_acacia | FeatureAcacia |
| tree_desert_acacia | FeatureCamelthorn |
| tree_padauk | FeaturePadauk |
| tree_cocobolo | FeatureCocobolo |
| tree_wenge | FeatureWenge |
| tree_mahoe | FeatureMahoe |
| tree_baobab | FeatureBaobab |
| tree_date | FeatureDate |
| tree_olive | FeatureOlive |

`FeatureTreeVanilla` remains only for oak / birch / dark_oak (CE same).

## Notes

- Package remap: `forestry.*` → `com.leon1236.reforestry.*`.
- `FeatureAcacia`: CE `HorizontalDirection` not present locally → `FeatureHelper.DirectionHelper.getRandom`.
- Compile: `./gradlew compileJava` OK.
- In-game spot-check (sequoia/baobab/coconut) not run — minecraft-world MCP unavailable this session.
