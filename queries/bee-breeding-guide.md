# Bee breeding guide (Re-Forestry)

Extracted from `src/main/java/com/leon1236/reforestry/apiculture/genetics/DefaultBeeSpecies.java` — **69** species, **114** mutations.

## Source / hive bees

These species have no inbound mutations (found in hives, special sources, or endgame):

- `bee_aquatic` (Aquatic)
- `bee_embittered` (Embittered)
- `bee_ended` (Ended)
- `bee_forest` (Forest)
- `bee_lush` (Lush)
- `bee_marshy` (Marshy)
- `bee_meadows` (Meadows)
- `bee_modest` (Modest)
- `bee_monastic` (Monastic)
- `bee_pirate` (Pirate)
- `bee_relic` (Relic)
- `bee_savanna` (Savanna)
- `bee_shulking` (Shulking)
- `bee_steadfast` (Steadfast)
- `bee_tropical` (Tropical)
- `bee_valiant` (Valiant)
- `bee_vanilla` (Vanilla)
- `bee_wintry` (Wintry)
- `bee_zombified` (Zombified)

## All mutations (by result)

### Abyssal (`bee_abyssal`)

- `bee_pirate` × `bee_ended` → `bee_abyssal` (40%) — _cave dwelling_
- `bee_aquatic` × `bee_ended` → `bee_abyssal` (40%) — _cave dwelling_
- `bee_pirate` × `bee_shulking` → `bee_abyssal` (60%) — _cave dwelling_
- `bee_aquatic` × `bee_shulking` → `bee_abyssal` (60%) — _cave dwelling_

### Agrarian (`bee_agrarian`)

- `bee_farmerly` × `bee_industrious` → `bee_agrarian` (6%) — _biome:ConventionalBiomeTags.IS_PLAINS_

### Anachrone (`bee_anachrone`)

- `bee_relic` × `bee_steadfast` → `bee_anachrone` (10%)

### Argil (`bee_argil`)

- `bee_savanna` × `bee_diligent` → `bee_argil` (15%)

### Austere (`bee_austere`)

- `bee_modest` × `bee_frugal` → `bee_austere` (8%)

### Autotrophic (`bee_autotrophic`)

- `bee_kleptoplastic` × `bee_photosynthetic` → `bee_autotrophic` (4%)

### Avenging (`bee_avenging`)

- `bee_vindictive` × `bee_vengeful` → `bee_avenging` (4%)

### Boggy (`bee_boggy`)

- `bee_marshy` × `bee_miry` → `bee_boggy` (9%)

### Common (`bee_common`)

- `bee_forest` × `bee_marshy` → `bee_common` (15%)
- `bee_forest` × `bee_meadows` → `bee_common` (15%)
- `bee_forest` × `bee_modest` → `bee_common` (15%)
- `bee_forest` × `bee_savanna` → `bee_common` (15%)
- `bee_forest` × `bee_tropical` → `bee_common` (15%)
- `bee_forest` × `bee_valiant` → `bee_common` (15%)
- `bee_forest` × `bee_wintry` → `bee_common` (15%)
- `bee_forest` × `bee_lush` → `bee_common` (15%)
- `bee_forest` × `bee_aquatic` → `bee_common` (15%)
- `bee_marshy` × `bee_meadows` → `bee_common` (15%)
- `bee_marshy` × `bee_modest` → `bee_common` (15%)
- `bee_marshy` × `bee_savanna` → `bee_common` (15%)
- `bee_marshy` × `bee_tropical` → `bee_common` (15%)
- `bee_marshy` × `bee_valiant` → `bee_common` (15%)
- `bee_marshy` × `bee_wintry` → `bee_common` (15%)
- `bee_marshy` × `bee_lush` → `bee_common` (15%)
- `bee_marshy` × `bee_aquatic` → `bee_common` (15%)
- `bee_meadows` × `bee_modest` → `bee_common` (15%)
- `bee_meadows` × `bee_savanna` → `bee_common` (15%)
- `bee_meadows` × `bee_tropical` → `bee_common` (15%)
- `bee_meadows` × `bee_valiant` → `bee_common` (15%)
- `bee_meadows` × `bee_wintry` → `bee_common` (15%)
- `bee_meadows` × `bee_lush` → `bee_common` (15%)
- `bee_meadows` × `bee_aquatic` → `bee_common` (15%)
- `bee_modest` × `bee_savanna` → `bee_common` (15%)
- `bee_modest` × `bee_tropical` → `bee_common` (15%)
- `bee_modest` × `bee_valiant` → `bee_common` (15%)
- `bee_modest` × `bee_wintry` → `bee_common` (15%)
- `bee_modest` × `bee_lush` → `bee_common` (15%)
- `bee_modest` × `bee_aquatic` → `bee_common` (15%)
- `bee_savanna` × `bee_tropical` → `bee_common` (15%)
- `bee_savanna` × `bee_valiant` → `bee_common` (15%)
- `bee_savanna` × `bee_wintry` → `bee_common` (15%)
- `bee_savanna` × `bee_lush` → `bee_common` (15%)
- `bee_savanna` × `bee_aquatic` → `bee_common` (15%)
- `bee_tropical` × `bee_valiant` → `bee_common` (15%)
- `bee_tropical` × `bee_wintry` → `bee_common` (15%)
- `bee_tropical` × `bee_lush` → `bee_common` (15%)
- `bee_tropical` × `bee_aquatic` → `bee_common` (15%)
- `bee_valiant` × `bee_wintry` → `bee_common` (15%)
- `bee_valiant` × `bee_lush` → `bee_common` (15%)
- `bee_valiant` × `bee_aquatic` → `bee_common` (15%)
- `bee_wintry` × `bee_lush` → `bee_common` (15%)
- `bee_wintry` × `bee_aquatic` → `bee_common` (15%)
- `bee_lush` × `bee_aquatic` → `bee_common` (15%)

### Cultivated (`bee_cultivated`)

- `bee_common` × `bee_forest` → `bee_cultivated` (12%)
- `bee_common` × `bee_marshy` → `bee_cultivated` (12%)
- `bee_common` × `bee_meadows` → `bee_cultivated` (12%)
- `bee_common` × `bee_modest` → `bee_cultivated` (12%)
- `bee_common` × `bee_savanna` → `bee_cultivated` (12%)
- `bee_common` × `bee_tropical` → `bee_cultivated` (12%)
- `bee_common` × `bee_valiant` → `bee_cultivated` (12%)
- `bee_common` × `bee_wintry` → `bee_cultivated` (12%)
- `bee_common` × `bee_lush` → `bee_cultivated` (12%)
- `bee_common` × `bee_aquatic` → `bee_cultivated` (12%)

### Demonic (`bee_demonic`)

- `bee_sinister` × `bee_fiendish` → `bee_demonic` (25%) — _biome:BiomeTags.IS_NETHER_

### Diligent (`bee_diligent`)

- `bee_common` × `bee_cultivated` → `bee_diligent` (10%)

### Edenic (`bee_edenic`)

- `bee_exotic` × `bee_tropical` → `bee_edenic` (8%)

### Exotic (`bee_exotic`)

- `bee_austere` × `bee_tropical` → `bee_exotic` (12%)

### Farmerly (`bee_farmerly`)

- `bee_rural` × `bee_unweary` → `bee_farmerly` (10%) — _biome:ConventionalBiomeTags.IS_PLAINS_

### Fiendish (`bee_fiendish`)

- `bee_sinister` × `bee_cultivated` → `bee_fiendish` (40%) — _biome:BiomeTags.IS_NETHER_
- `bee_sinister` × `bee_modest` → `bee_fiendish` (40%) — _biome:BiomeTags.IS_NETHER_
- `bee_sinister` × `bee_tropical` → `bee_fiendish` (40%) — _biome:BiomeTags.IS_NETHER_

### Frugal (`bee_frugal`)

- `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
- `bee_modest` × `bee_fiendish` → `bee_frugal` (10%)

### Glacial (`bee_glacial`)

- `bee_icy` × `bee_wintry` → `bee_glacial` (8%)

### Hermitic (`bee_hermitic`)

- `bee_monastic` × `bee_secluded` → `bee_hermitic` (8%)

### Heroic (`bee_heroic`)

- `bee_steadfast` × `bee_valiant` → `bee_heroic` (6%) — _biome:BiomeTags.IS_FOREST_

### Icy (`bee_icy`)

- `bee_industrious` × `bee_wintry` → `bee_icy` (12%)

### Imperial (`bee_imperial`)

- `bee_noble` × `bee_majestic` → `bee_imperial` (8%)

### Industrious (`bee_industrious`)

- `bee_diligent` × `bee_unweary` → `bee_industrious` (8%)

### Kleptoplastic (`bee_kleptoplastic`)

- `bee_luxuriant` × `bee_monastic` → `bee_kleptoplastic` (12%)

### Leporine (`bee_leporine`)

- `bee_meadows` × `bee_forest` → `bee_leporine` (10%) — _date 3/29–4/15_

### Luxuriant (`bee_luxuriant`)

- `bee_lush` × `bee_verdant` → `bee_luxuriant` (8%) — _cave dwelling_

### Majestic (`bee_majestic`)

- `bee_noble` × `bee_cultivated` → `bee_majestic` (8%)

### Merry (`bee_merry`)

- `bee_wintry` × `bee_forest` → `bee_merry` (10%) — _date 12/21–12/27_

### Miry (`bee_miry`)

- `bee_marshy` × `bee_noble` → `bee_miry` (15%)

### Noble (`bee_noble`)

- `bee_common` × `bee_cultivated` → `bee_noble` (10%)

### Patriotic (`bee_patriotic`)

- `bee_rural` × `bee_noble` → `bee_patriotic` (15%) — _date 7/1–7/17_

### Phantasmal (`bee_phantasmal`)

- `bee_spectral` × `bee_ended` → `bee_phantasmal` (2%)

### Photosynthetic (`bee_photosynthetic`)

- `bee_kleptoplastic` × `bee_luxuriant` → `bee_photosynthetic` (8%)
- `bee_kleptoplastic` × `bee_monastic` → `bee_photosynthetic` (8%)

### Pride (`bee_pride`)

- `bee_savanna` × `bee_argil` → `bee_pride` (9%) — _biome:ReforestryBiomeTags.SHATTERED_SAVANNA_

### Primeval (`bee_primeval`)

- `bee_anachrone` × `bee_steadfast` → `bee_primeval` (15%)

### Prismatic (`bee_prismatic`)

- `bee_aquatic` × `bee_pirate` → `bee_prismatic` (8%)

### Rural (`bee_rural`)

- `bee_meadows` × `bee_diligent` → `bee_rural` (12%) — _biome:ConventionalBiomeTags.IS_PLAINS_

### Sculk (`bee_sculk`)

- `bee_abyssal` × `bee_hermitic` → `bee_sculk` (6%) — _biome:ReforestryBiomeTags.DEEP_DARK_

### Secluded (`bee_secluded`)

- `bee_monastic` × `bee_austere` → `bee_secluded` (12%)

### Seething (`bee_seething`)

- `bee_spiteful` × `bee_embittered` → `bee_seething` (8%)

### Sinister (`bee_sinister`)

- `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) — _biome:BiomeTags.IS_NETHER_
- `bee_cultivated` × `bee_tropical` → `bee_sinister` (60%) — _biome:BiomeTags.IS_NETHER_

### Spectral (`bee_spectral`)

- `bee_hermitic` × `bee_ended` → `bee_spectral` (4%)

### Spiteful (`bee_spiteful`)

- `bee_embittered` × `bee_fiendish` → `bee_spiteful` (12%)

### Tipsy (`bee_tipsy`)

- `bee_wintry` × `bee_meadows` → `bee_tipsy` (10%) — _date 12/27–1/2_

### Tricky (`bee_tricky`)

- `bee_sinister` × `bee_common` → `bee_tricky` (10%) — _date 10/15–11/3_

### Unweary (`bee_unweary`)

- `bee_diligent` × `bee_cultivated` → `bee_unweary` (8%)

### Vengeful (`bee_vengeful`)

- `bee_vindictive` × `bee_cultivated` → `bee_vengeful` (8%)

### Verdant (`bee_verdant`)

- `bee_lush` × `bee_valiant` → `bee_verdant` (10%) — _cave dwelling_

### Vindictive (`bee_vindictive`)

- `bee_savanna` × `bee_common` → `bee_vindictive` (12%)

### Warped (`bee_warped`)

- `bee_embittered` × `bee_ended` → `bee_warped` (40%) — _biome:ReforestryBiomeTags.WARPED_FOREST_
- `bee_spiteful` × `bee_ended` → `bee_warped` (40%) — _biome:ReforestryBiomeTags.WARPED_FOREST_
- `bee_embittered` × `bee_shulking` → `bee_warped` (40%) — _biome:ReforestryBiomeTags.WARPED_FOREST_
- `bee_spiteful` × `bee_shulking` → `bee_warped` (40%) — _biome:ReforestryBiomeTags.WARPED_FOREST_

## How to breed (shortest path from source bees)

Each path is one shortest sequence of mutations you must perform (shared ancestors counted once). Depth equals the number of steps in that path.

### Abyssal — depth 1

Direct recipes (any one works as the final step):

- `bee_pirate` × `bee_ended` (40%) — _cave dwelling_
- `bee_aquatic` × `bee_ended` (40%) — _cave dwelling_
- `bee_pirate` × `bee_shulking` (60%) — _cave dwelling_
- `bee_aquatic` × `bee_shulking` (60%) — _cave dwelling_

Example shortest path:

1. `bee_pirate` × `bee_shulking` → `bee_abyssal` (60%) (cave dwelling)

### Agrarian — depth 8

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_meadows` × `bee_diligent` → `bee_rural` (12%) (biome:ConventionalBiomeTags.IS_PLAINS)
5. `bee_diligent` × `bee_cultivated` → `bee_unweary` (8%)
6. `bee_rural` × `bee_unweary` → `bee_farmerly` (10%) (biome:ConventionalBiomeTags.IS_PLAINS)
7. `bee_diligent` × `bee_unweary` → `bee_industrious` (8%)
8. `bee_farmerly` × `bee_industrious` → `bee_agrarian` (6%) (biome:ConventionalBiomeTags.IS_PLAINS)

### Anachrone — depth 1

1. `bee_relic` × `bee_steadfast` → `bee_anachrone` (10%)

### Argil — depth 4

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_savanna` × `bee_diligent` → `bee_argil` (15%)

### Austere — depth 5

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
5. `bee_modest` × `bee_frugal` → `bee_austere` (8%)

### Autotrophic — depth 5

1. `bee_lush` × `bee_valiant` → `bee_verdant` (10%) (cave dwelling)
2. `bee_lush` × `bee_verdant` → `bee_luxuriant` (8%) (cave dwelling)
3. `bee_luxuriant` × `bee_monastic` → `bee_kleptoplastic` (12%)
4. `bee_kleptoplastic` × `bee_luxuriant` → `bee_photosynthetic` (8%)
5. `bee_kleptoplastic` × `bee_photosynthetic` → `bee_autotrophic` (4%)

### Avenging — depth 5

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_savanna` × `bee_common` → `bee_vindictive` (12%)
3. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
4. `bee_vindictive` × `bee_cultivated` → `bee_vengeful` (8%)
5. `bee_vindictive` × `bee_vengeful` → `bee_avenging` (4%)

### Boggy — depth 5

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_noble` (10%)
4. `bee_marshy` × `bee_noble` → `bee_miry` (15%)
5. `bee_marshy` × `bee_miry` → `bee_boggy` (9%)

### Common — depth 1

Direct recipes (any one works as the final step):

- `bee_forest` × `bee_marshy` (15%)
- `bee_forest` × `bee_meadows` (15%)
- `bee_forest` × `bee_modest` (15%)
- `bee_forest` × `bee_savanna` (15%)
- `bee_forest` × `bee_tropical` (15%)
- `bee_forest` × `bee_valiant` (15%)
- `bee_forest` × `bee_wintry` (15%)
- `bee_forest` × `bee_lush` (15%)
- `bee_forest` × `bee_aquatic` (15%)
- `bee_marshy` × `bee_meadows` (15%)
- `bee_marshy` × `bee_modest` (15%)
- `bee_marshy` × `bee_savanna` (15%)
- `bee_marshy` × `bee_tropical` (15%)
- `bee_marshy` × `bee_valiant` (15%)
- `bee_marshy` × `bee_wintry` (15%)
- `bee_marshy` × `bee_lush` (15%)
- `bee_marshy` × `bee_aquatic` (15%)
- `bee_meadows` × `bee_modest` (15%)
- `bee_meadows` × `bee_savanna` (15%)
- `bee_meadows` × `bee_tropical` (15%)
- `bee_meadows` × `bee_valiant` (15%)
- `bee_meadows` × `bee_wintry` (15%)
- `bee_meadows` × `bee_lush` (15%)
- `bee_meadows` × `bee_aquatic` (15%)
- `bee_modest` × `bee_savanna` (15%)
- `bee_modest` × `bee_tropical` (15%)
- `bee_modest` × `bee_valiant` (15%)
- `bee_modest` × `bee_wintry` (15%)
- `bee_modest` × `bee_lush` (15%)
- `bee_modest` × `bee_aquatic` (15%)
- `bee_savanna` × `bee_tropical` (15%)
- `bee_savanna` × `bee_valiant` (15%)
- `bee_savanna` × `bee_wintry` (15%)
- `bee_savanna` × `bee_lush` (15%)
- `bee_savanna` × `bee_aquatic` (15%)
- `bee_tropical` × `bee_valiant` (15%)
- `bee_tropical` × `bee_wintry` (15%)
- `bee_tropical` × `bee_lush` (15%)
- `bee_tropical` × `bee_aquatic` (15%)
- `bee_valiant` × `bee_wintry` (15%)
- `bee_valiant` × `bee_lush` (15%)
- `bee_valiant` × `bee_aquatic` (15%)
- `bee_wintry` × `bee_lush` (15%)
- `bee_wintry` × `bee_aquatic` (15%)
- `bee_lush` × `bee_aquatic` (15%)

Example shortest path:

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)

### Cultivated — depth 2

Direct recipes (any one works as the final step):

- `bee_common` × `bee_forest` (12%)
- `bee_common` × `bee_marshy` (12%)
- `bee_common` × `bee_meadows` (12%)
- `bee_common` × `bee_modest` (12%)
- `bee_common` × `bee_savanna` (12%)
- `bee_common` × `bee_tropical` (12%)
- `bee_common` × `bee_valiant` (12%)
- `bee_common` × `bee_wintry` (12%)
- `bee_common` × `bee_lush` (12%)
- `bee_common` × `bee_aquatic` (12%)

Example shortest path:

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)

### Demonic — depth 5

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_sinister` × `bee_cultivated` → `bee_fiendish` (40%) (biome:BiomeTags.IS_NETHER)
5. `bee_sinister` × `bee_fiendish` → `bee_demonic` (25%) (biome:BiomeTags.IS_NETHER)

### Diligent — depth 3

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)

### Edenic — depth 7

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
5. `bee_modest` × `bee_frugal` → `bee_austere` (8%)
6. `bee_austere` × `bee_tropical` → `bee_exotic` (12%)
7. `bee_exotic` × `bee_tropical` → `bee_edenic` (8%)

### Exotic — depth 6

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
5. `bee_modest` × `bee_frugal` → `bee_austere` (8%)
6. `bee_austere` × `bee_tropical` → `bee_exotic` (12%)

### Farmerly — depth 6

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_meadows` × `bee_diligent` → `bee_rural` (12%) (biome:ConventionalBiomeTags.IS_PLAINS)
5. `bee_diligent` × `bee_cultivated` → `bee_unweary` (8%)
6. `bee_rural` × `bee_unweary` → `bee_farmerly` (10%) (biome:ConventionalBiomeTags.IS_PLAINS)

### Fiendish — depth 4

Direct recipes (any one works as the final step):

- `bee_sinister` × `bee_cultivated` (40%) — _biome:BiomeTags.IS_NETHER_
- `bee_sinister` × `bee_modest` (40%) — _biome:BiomeTags.IS_NETHER_
- `bee_sinister` × `bee_tropical` (40%) — _biome:BiomeTags.IS_NETHER_

Example shortest path:

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_sinister` × `bee_cultivated` → `bee_fiendish` (40%) (biome:BiomeTags.IS_NETHER)

### Frugal — depth 4

Direct recipes (any one works as the final step):

- `bee_modest` × `bee_sinister` (16%)
- `bee_modest` × `bee_fiendish` (10%)

Example shortest path:

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)

### Glacial — depth 7

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_diligent` × `bee_cultivated` → `bee_unweary` (8%)
5. `bee_diligent` × `bee_unweary` → `bee_industrious` (8%)
6. `bee_industrious` × `bee_wintry` → `bee_icy` (12%)
7. `bee_icy` × `bee_wintry` → `bee_glacial` (8%)

### Hermitic — depth 7

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
5. `bee_modest` × `bee_frugal` → `bee_austere` (8%)
6. `bee_monastic` × `bee_austere` → `bee_secluded` (12%)
7. `bee_monastic` × `bee_secluded` → `bee_hermitic` (8%)

### Heroic — depth 1

1. `bee_steadfast` × `bee_valiant` → `bee_heroic` (6%) (biome:BiomeTags.IS_FOREST)

### Icy — depth 6

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_diligent` × `bee_cultivated` → `bee_unweary` (8%)
5. `bee_diligent` × `bee_unweary` → `bee_industrious` (8%)
6. `bee_industrious` × `bee_wintry` → `bee_icy` (12%)

### Imperial — depth 5

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_noble` (10%)
4. `bee_noble` × `bee_cultivated` → `bee_majestic` (8%)
5. `bee_noble` × `bee_majestic` → `bee_imperial` (8%)

### Industrious — depth 5

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_diligent` × `bee_cultivated` → `bee_unweary` (8%)
5. `bee_diligent` × `bee_unweary` → `bee_industrious` (8%)

### Kleptoplastic — depth 3

1. `bee_lush` × `bee_valiant` → `bee_verdant` (10%) (cave dwelling)
2. `bee_lush` × `bee_verdant` → `bee_luxuriant` (8%) (cave dwelling)
3. `bee_luxuriant` × `bee_monastic` → `bee_kleptoplastic` (12%)

### Leporine — depth 1

1. `bee_meadows` × `bee_forest` → `bee_leporine` (10%) (date 3/29–4/15)

### Luxuriant — depth 2

1. `bee_lush` × `bee_valiant` → `bee_verdant` (10%) (cave dwelling)
2. `bee_lush` × `bee_verdant` → `bee_luxuriant` (8%) (cave dwelling)

### Majestic — depth 4

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_noble` (10%)
4. `bee_noble` × `bee_cultivated` → `bee_majestic` (8%)

### Merry — depth 1

1. `bee_wintry` × `bee_forest` → `bee_merry` (10%) (date 12/21–12/27)

### Miry — depth 4

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_noble` (10%)
4. `bee_marshy` × `bee_noble` → `bee_miry` (15%)

### Noble — depth 3

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_noble` (10%)

### Patriotic — depth 6

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_meadows` × `bee_diligent` → `bee_rural` (12%) (biome:ConventionalBiomeTags.IS_PLAINS)
5. `bee_common` × `bee_cultivated` → `bee_noble` (10%)
6. `bee_rural` × `bee_noble` → `bee_patriotic` (15%) (date 7/1–7/17)

### Phantasmal — depth 9

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
5. `bee_modest` × `bee_frugal` → `bee_austere` (8%)
6. `bee_monastic` × `bee_austere` → `bee_secluded` (12%)
7. `bee_monastic` × `bee_secluded` → `bee_hermitic` (8%)
8. `bee_hermitic` × `bee_ended` → `bee_spectral` (4%)
9. `bee_spectral` × `bee_ended` → `bee_phantasmal` (2%)

### Photosynthetic — depth 4

Direct recipes (any one works as the final step):

- `bee_kleptoplastic` × `bee_luxuriant` (8%)
- `bee_kleptoplastic` × `bee_monastic` (8%)

Example shortest path:

1. `bee_lush` × `bee_valiant` → `bee_verdant` (10%) (cave dwelling)
2. `bee_lush` × `bee_verdant` → `bee_luxuriant` (8%) (cave dwelling)
3. `bee_luxuriant` × `bee_monastic` → `bee_kleptoplastic` (12%)
4. `bee_kleptoplastic` × `bee_luxuriant` → `bee_photosynthetic` (8%)

### Pride — depth 5

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_savanna` × `bee_diligent` → `bee_argil` (15%)
5. `bee_savanna` × `bee_argil` → `bee_pride` (9%) (biome:ReforestryBiomeTags.SHATTERED_SAVANNA)

### Primeval — depth 2

1. `bee_relic` × `bee_steadfast` → `bee_anachrone` (10%)
2. `bee_anachrone` × `bee_steadfast` → `bee_primeval` (15%)

### Prismatic — depth 1

1. `bee_aquatic` × `bee_pirate` → `bee_prismatic` (8%)

### Rural — depth 4

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_meadows` × `bee_diligent` → `bee_rural` (12%) (biome:ConventionalBiomeTags.IS_PLAINS)

### Sculk — depth 9

1. `bee_pirate` × `bee_shulking` → `bee_abyssal` (60%) (cave dwelling)
2. `bee_forest` × `bee_marshy` → `bee_common` (15%)
3. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
4. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
5. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
6. `bee_modest` × `bee_frugal` → `bee_austere` (8%)
7. `bee_monastic` × `bee_austere` → `bee_secluded` (12%)
8. `bee_monastic` × `bee_secluded` → `bee_hermitic` (8%)
9. `bee_abyssal` × `bee_hermitic` → `bee_sculk` (6%) (biome:ReforestryBiomeTags.DEEP_DARK)

### Secluded — depth 6

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
5. `bee_modest` × `bee_frugal` → `bee_austere` (8%)
6. `bee_monastic` × `bee_austere` → `bee_secluded` (12%)

### Seething — depth 6

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_sinister` × `bee_cultivated` → `bee_fiendish` (40%) (biome:BiomeTags.IS_NETHER)
5. `bee_embittered` × `bee_fiendish` → `bee_spiteful` (12%)
6. `bee_spiteful` × `bee_embittered` → `bee_seething` (8%)

### Sinister — depth 3

Direct recipes (any one works as the final step):

- `bee_cultivated` × `bee_modest` (60%) — _biome:BiomeTags.IS_NETHER_
- `bee_cultivated` × `bee_tropical` (60%) — _biome:BiomeTags.IS_NETHER_

Example shortest path:

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)

### Spectral — depth 8

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_modest` × `bee_sinister` → `bee_frugal` (16%)
5. `bee_modest` × `bee_frugal` → `bee_austere` (8%)
6. `bee_monastic` × `bee_austere` → `bee_secluded` (12%)
7. `bee_monastic` × `bee_secluded` → `bee_hermitic` (8%)
8. `bee_hermitic` × `bee_ended` → `bee_spectral` (4%)

### Spiteful — depth 5

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_sinister` × `bee_cultivated` → `bee_fiendish` (40%) (biome:BiomeTags.IS_NETHER)
5. `bee_embittered` × `bee_fiendish` → `bee_spiteful` (12%)

### Tipsy — depth 1

1. `bee_wintry` × `bee_meadows` → `bee_tipsy` (10%) (date 12/27–1/2)

### Tricky — depth 4

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_cultivated` × `bee_modest` → `bee_sinister` (60%) (biome:BiomeTags.IS_NETHER)
4. `bee_sinister` × `bee_common` → `bee_tricky` (10%) (date 10/15–11/3)

### Unweary — depth 4

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
3. `bee_common` × `bee_cultivated` → `bee_diligent` (10%)
4. `bee_diligent` × `bee_cultivated` → `bee_unweary` (8%)

### Vengeful — depth 4

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_savanna` × `bee_common` → `bee_vindictive` (12%)
3. `bee_common` × `bee_forest` → `bee_cultivated` (12%)
4. `bee_vindictive` × `bee_cultivated` → `bee_vengeful` (8%)

### Verdant — depth 1

1. `bee_lush` × `bee_valiant` → `bee_verdant` (10%) (cave dwelling)

### Vindictive — depth 2

1. `bee_forest` × `bee_marshy` → `bee_common` (15%)
2. `bee_savanna` × `bee_common` → `bee_vindictive` (12%)

### Warped — depth 1

Direct recipes (any one works as the final step):

- `bee_embittered` × `bee_ended` (40%) — _biome:ReforestryBiomeTags.WARPED_FOREST_
- `bee_spiteful` × `bee_ended` (40%) — _biome:ReforestryBiomeTags.WARPED_FOREST_
- `bee_embittered` × `bee_shulking` (40%) — _biome:ReforestryBiomeTags.WARPED_FOREST_
- `bee_spiteful` × `bee_shulking` (40%) — _biome:ReforestryBiomeTags.WARPED_FOREST_

Example shortest path:

1. `bee_embittered` × `bee_ended` → `bee_warped` (40%) (biome:ReforestryBiomeTags.WARPED_FOREST)

