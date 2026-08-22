# Mail module — out of scope

Re-Forestry **does not** port Forestry CE’s mail module.

Do not implement `reforestry:mail`, `api.mail`, stamps, letters, catalogue, mailbox, trade station, or stamp collector. Do not copy leftover mail assets back in.

CE still has that module; we skip it on purpose. Carpenter extract (`tools/extract_carpenter_recipes.py`) never writes mail recipes (`MAIL_NEVER`).

Next work: `files/implemented-features.md` **Next up** (worktable, bee-effect polish, naturalist chests / escritoire — not mail).
