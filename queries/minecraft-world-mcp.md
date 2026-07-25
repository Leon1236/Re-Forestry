# Minecraft world MCP (agent testing)

Created so Cursor can drive a live Re-Forestry dedicated server.

## Pieces

- `tools/minecraft_world_mcp/server.py` — FastMCP tools over RCON + `run/logs/latest.log`
- `tools/enable_dev_rcon.py` — writes local RCON settings into `run/server.properties`
- `.cursor/mcp.json` — registers MCP server `minecraft-world`
- `.cursor/skills/minecraft-world-test/SKILL.md` — when/how the agent should use it

## Why RCON (not Management Protocol)

MC 26.2 Management Protocol is admin-only (status, bans, gamerules, save/stop). It cannot `/give`, `/setblock`, or `/data get`. RCON can run arbitrary commands — required for mod testing.

## How to use

1. `python3 tools/enable_dev_rcon.py` (once)
2. `./gradlew runServer`
3. Reload Cursor MCP / enable `minecraft-world`
4. Ask the agent to test something in-world

Default password: `reforestry-dev`, port `25575`. Dev-only; bound to localhost.

## Tool inventory (see SKILL.md for full workflow guidance)

Raw RCON: `world_status`, `run_command`, `run_commands`, `run_command_and_capture_log`.

Log/crash: `read_latest_log`, `grep_latest_log`, `wait_for_log`, `mark_log`, `read_new_log`,
`list_rotated_logs`, `read_rotated_log`, `list_crashes`, `read_crash`.

Gameplay helpers (wrap commands, all support optional `dimension`): `give`, `place_block`,
`read_block`, `fill_region`, `teleport_to`, `summon`, `get_player_nbt`, `list_players`,
`set_world_time`, `set_world_weather`, `set_rule`, `save`.

`read_crash` defaults to the newest crash report if `name` is omitted — check it whenever the
server unexpectedly stops mid-test, since `latest.log` may not capture the fatal stack trace after
a rotation.
