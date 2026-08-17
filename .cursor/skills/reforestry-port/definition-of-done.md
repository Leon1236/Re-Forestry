# Definition of done

A stage is not done until every applicable row is true. Mark N/A only when CE has no such piece (plain item, no GUI, no API).

| # | Deliverable |
|---|---|
| D1 | CE `api/` types exist under `com.leon1236.reforestry.api.*` and impl uses them |
| D2 | Registered via `FeatureItem` / `FeatureBlock` / groups / BE / menu as needed; module-gated if non-core |
| D3 | Behavior matches CE for the scoped stage (use, GUI, filters, fluids, genetics) |
| D4 | `models/item/{id}.json` **and** `items/{id}.json` for every new item id |
| D5 | Textures copied from reference, not invented |
| D6 | `en_us` names + CE tooltips/GUI strings |
| D7 | Correct creative tab, CE-like order |
| D8 | Recipes that produce or consume these ids (extract; document skips) |
| D9 | Item/block tags + `c:` where CE used `forge:` |
| D10 | Client: screens, BER, tint, renderer as CE requires |
| D11 | Data components / inventories survive save/reload |
| D12 | Smoke: `/give` + in-world action, or listed manual checks |
| D13 | `files/implemented-features.md` updated |

Full per-stage checklists: `queries/item-gap-implementation-plan.md`.

## Common skips to document in `queries/`

- Recipe outputs that are not registered yet
- JEI recipe-transfer packets
- Animated BER / fill-level blockstates
- Optional Trinkets slots
