---
name: minecraft-world-test
description: Test Re-Forestry in a live Minecraft dedicated server via the minecraft-world MCP (RCON commands + latest.log). Use when verifying in-game behavior, giving items, placing blocks, checking crashes, or when the user asks to test the mod in the world.
---

# Minecraft world testing (Re-Forestry)

## Prerequisites

1. RCON enabled once: `python tools/enable_dev_rcon.py`
2. Dedicated server running: `./gradlew runServer` (not plain `runClient` — RCON needs the dedicated server)
3. Cursor MCP server `minecraft-world` enabled (`.cursor/mcp.json`)

Local RCON password default: `reforestry-dev` on port `25575`.

## MCP tools

Raw:

| Tool | Use for |
|------|---------|
| `world_status` | Is RCON up? Also returns online player list. |
| `run_command` | One raw MC command |
| `run_commands` | Batch of raw commands on one connection |
| `run_command_and_capture_log` | Run a command and capture the log lines it triggers in one round trip — best default for "did this work / crash" checks |

Log & crash inspection:

| Tool | Use for |
|------|---------|
| `read_latest_log` | Tail `run/logs/latest.log` |
| `grep_latest_log` | Regex search the current log |
| `wait_for_log` | Block until a pattern appears (or timeout) |
| `mark_log` / `read_new_log` | Checkpoint the log, then read only what a later step produced |
| `list_rotated_logs` / `read_rotated_log` | Browse archived `*.log.gz` from earlier runs |
| `list_crashes` / `read_crash` | List/read `run/crash-reports/*.txt` |

World/gameplay helpers (thin wrappers over commands, still return raw RCON response):

| Tool | Wraps |
|------|-------|
| `give` | `/give` |
| `place_block` / `read_block` / `fill_region` | `/setblock`, `/data get block`, `/fill` |
| `teleport_to` | `/tp` |
| `summon` | `/summon` |
| `get_player_nbt` | `/data get entity` |
| `list_players` | `/list` (parsed into a name array) |
| `set_world_time` / `set_world_weather` / `set_rule` | `/time set`, `/weather`, `/gamerule` |
| `save` | `/save-all flush` |

All accept an optional `dimension` where relevant (wraps the command in `execute in <dimension> run ...`).

## Workflow

1. Call `world_status`. If not reachable, tell the user to start `./gradlew runServer`.
2. Set up the scene (`set_world_time("day")`, `set_rule("doMobSpawning", "false")`, `give`, `place_block`, `teleport_to`).
3. For anything with side effects, prefer `run_command_and_capture_log` (or `mark_log` before + `read_new_log` after a batch) over a bare `run_command`, so you see errors immediately without a second round trip.
4. If the server dies, check `list_crashes` / `read_crash` — regular log tools won't show a crash that happened after the log rotated.
5. Prefer verifying via commands + logs over asking the user to click around.

## Re-Forestry examples

```
give("@p", "reforestry:bee_comb_honey", 16)
place_block("~", "~", "~", "reforestry:bee_house")
read_block("<x>", "<y>", "<z>")
run_command_and_capture_log("data get block <x> <y> <z>", pattern="Exception|ERROR")
mark_log()
run_commands(["time set day", "weather clear", "gamerule doMobSpawning false"])
read_new_log()
```

Use real registry ids from the codebase / `minecraft-mods` MCP — never invent `reforestry:` ids.

## Limits

- No screenshots / GUI clicks (RCON only).
- Needs a player online for `@p` / inventory checks; otherwise use absolute coords and `@a` / fake setup.
- Management Protocol (JSON-RPC) is admin-only — this tool uses RCON for real gameplay commands.
