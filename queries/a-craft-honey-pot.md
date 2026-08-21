# A-CRAFT — honey pot recipe restore

CE 1.21.1 has **no** `honey_pot` crafting recipe (`src/generated/resources/data/forestry/recipe/` lists `ambrosia`, `apiary`, `bee_house`, `honeyed_slice` only).

Restored from Forestry 1.12 `ModuleApiculture` (FOOD module):

```
"# #", " X ", "# #"
# = honey_drop
X = empty wax capsule
result = honey_pot × 1
```

Local capsule id is `reforestry:capsule` (CE 1.21.1 `forestry:wax_capsule`). Do not rename this stage.

`honeyed_slice` uses CE 1.21.1 count **1**, not 1.12’s count 4.
