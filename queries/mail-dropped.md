# Mail module — out of scope

Re-Forestry **does not** port Forestry CE’s mail module.

Do not implement `reforestry:mail`, `api.mail`, stamps, letters, catalogue, mailbox, trade station, or stamp collector. Do not copy leftover mail assets back in.

CE still has that module; we skip it on purpose. Carpenter extract (`tools/extract_carpenter_recipes.py`) never writes mail recipes (`MAIL_NEVER`).

## Wave 9 Stage 2 (HYGIENE) — **done**

Removed dead mail surface area:

- `ForestryError` mail constants (`NO_STAMPS`, `NO_PAPER`, `NO_SUPPLIES`, `NO_TRADE`, `NOT_ALPHANUMERIC`, `NOT_UNIQUE`, `NOT_POST_PAID`, `NO_RECIPIENT`)
- Mail error + addressee lang keys from all locales
- Orphan `infuser` item/model/lang (never registered)
- Orphan `raintank` assets (blockstates, models, loot, hints, Patchouli entry, lang/JEI keys) — CE dropped; rainmaker page lands Stage 7
- `item.reforestry.foresters_manual.stub` lang (native almanac replaces stub)

Kept `habitat_locator` assets for optional Stage 12 OR3.
