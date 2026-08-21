# AGENTS.md

## Cursor Cloud specific instructions

Re-Forestry is a **Fabric mod for Minecraft 26.2** (Java 25, Gradle 9.5.1 via the wrapper). There is a single Gradle project; there are no unit tests (`:test` is `NO-SOURCE`).

### Toolchain
- The build requires **JDK 25**. It is already provisioned in this environment and is the default `java`, so Gradle picks it up automatically (no `JAVA_HOME` needed). Do not downgrade to the system JDK 21.

### Build / lint / test
- Build (and dependency refresh): `./gradlew build` — compiles `src/main/java`, processes resources, assembles the jar. Fast and incremental after the first run.
- Lint/check: `./gradlew check`. There is **no separate linter**; `check` is effectively compile + `validateAccessWidener` (currently `NO-SOURCE`), and `test` is `NO-SOURCE`. Compile-only warnings (deprecation/unchecked) are expected and non-fatal.

### Running the mod
- **Dedicated server (headless, preferred for testing):** `./gradlew runServer`. The EULA is already accepted (`run/eula.txt`). Reaches `Done (...)! For help, type "help"` then auto-pauses after being empty for 60s — console commands still execute while paused. Drive the console by sending keys to the `runServer` process (e.g. via tmux `send-keys`) and read `run/logs/latest.log`.
  - Positions away from spawn may be unloaded; run `forceload add <x> <z>` before `setblock`/`data get block` at those coords.
- **Client (GUI):** `./gradlew runClient`. Works on the virtual X display (`DISPLAY=:1`) using **software OpenGL (llvmpipe/Mesa)**, so it is slow but functional. Audio (OpenAL/ALSA) is unavailable in the VM and logs harmless `ALSA`/`Failed to open OpenAL device` errors. Dev username defaults to `Dev`.

### Gotchas
- On server start, some **pre-existing data-pack ERRORs** appear (missing tag references such as `#c:sandstone_blocks`, `#c:slimeballs`). These are mod-content issues, not environment problems; the server still starts.
- `.cursor/mcp.json` declares `minecraft-world` and `minecraft-mods` MCP servers, but they point at files under `MarkDown_Maker/` and `tools/minecraft_world_mcp/` that are **not present in this repository**, so those MCP servers do not run here. Test in-world by driving the `runServer` console + logs directly instead.
