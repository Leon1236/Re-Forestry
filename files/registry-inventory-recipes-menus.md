# Registry inventory — recipes, menu types (apiculture + arboriculture)

## Recipe types — apiculture has 1, arboriculture has 0
`HygroregulatorRecipe` (Alveary Hygroregulator's climate-control recipe) is the only custom recipe type in either module. JSON schema, confirmed from the serializer:
```json
{ "liquid": { /* fluid stack */ }, "time": 0, "humidity_steps": 0, "temperature_steps": 0 }
```
A fluid goes in, and it shifts humidity/temperature by N steps for a retain-time duration. Built on vanilla `RecipeSerializer`/`RecipeType` (JSON `fromJson` + network `fromNetwork`/`toNetwork`) — nothing Forge-specific in the recipe class itself except the fluid field, which is `net.minecraftforge.fluids.FluidStack` (ties back to the earlier Forge→Fabric mapping: this needs the Transfer API's fluid representation on the Fabric side, not a straight port).

**Scope note worth flagging now:** the recipe *type itself* is registered in `forestry.factory.features.FactoryRecipeTypes`, not in apiculture — factory acts as a shared recipe-type registry hub even for other modules' machine recipes. This is almost certainly the one cross-module reference from apiculture into factory found back in the dependency-graph pass. Since factory isn't in the current milestone scope, this is a real loose end: either stub a local recipe-type registration for the Hygroregulator specifically, carry over just factory's recipe-type-registry pattern, or defer the Hygroregulator machine until factory comes into scope. Worth a decision before implementation starts.

## Menu types (GUIs) — apiculture has 5, arboriculture has 0
`alveary`, `alveary_hygroregulator`, `alveary_sieve`, `alveary_swarmer`, `bee_housing` — the last one is shared by both the Apiary and Bee House (both are "housing" for a queen, same GUI shape). Arboriculture has no player-facing GUIs — saplings, leaves, and pods don't need one.

## Registry inventory — complete for this milestone's scope
Blocks (1,337), items (136), genetics data model (karyotypes + worked examples + effect system), tiles (14), entities (2), fluids (0), recipe types (1), menu types (5) — apiculture and arboriculture are now fully mapped from the ForestryCE source. Immersive-Forestry hasn't had the same exhaustive pass yet (only core/api were diffed so far, and found near-identical modulo the Forge→NeoForge rename) — worth a spot-check before treating either fork as final, per "compare per module, pick the better fit."
