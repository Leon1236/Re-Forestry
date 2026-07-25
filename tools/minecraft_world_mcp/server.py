#!/usr/bin/env python3
"""
MCP server: interact with a live Re-Forestry dedicated server via RCON + logs.

Usage (stdio MCP):
  python tools/minecraft_world_mcp/server.py

Self-test (no running game needed for log path checks):
  python tools/minecraft_world_mcp/server.py --self-test

Requires:
  1. python tools/enable_dev_rcon.py
  2. ./gradlew runServer   (RCON does not work on plain singleplayer)
  3. Cursor MCP server "minecraft-world" enabled (see .cursor/mcp.json)
"""

from __future__ import annotations

import gzip
import json
import os
import re
import socket
import sys
import time
from pathlib import Path

_SCRIPT_DIR = Path(__file__).resolve().parent
if str(_SCRIPT_DIR) not in sys.path:
    sys.path.insert(0, str(_SCRIPT_DIR))

from rcon_client import RconClient, RconConfig, RconError

REPO_ROOT = Path(__file__).resolve().parents[2]
DEFAULT_RUN_DIR = REPO_ROOT / "run"
DEFAULT_PASSWORD = "reforestry-dev"


def _env(name: str, default: str = "") -> str:
    value = os.environ.get(name)
    return default if value is None or value == "" else value


def get_run_dir() -> Path:
    raw = _env("REFORESTRY_RUN_DIR", str(DEFAULT_RUN_DIR))
    if "${" in raw:
        raw = str(DEFAULT_RUN_DIR)
    return Path(raw).expanduser().resolve()


def get_rcon_config() -> RconConfig:
    return RconConfig(
        host=_env("REFORESTRY_RCON_HOST", "127.0.0.1"),
        port=int(_env("REFORESTRY_RCON_PORT", "25575")),
        password=_env("REFORESTRY_RCON_PASSWORD", DEFAULT_PASSWORD),
        timeout_seconds=float(_env("REFORESTRY_RCON_TIMEOUT", "5")),
    )


def latest_log_path() -> Path:
    return get_run_dir() / "logs" / "latest.log"


def logs_dir() -> Path:
    return get_run_dir() / "logs"


def crash_reports_dir() -> Path:
    return get_run_dir() / "crash-reports"


_log_marker: dict[str, object] = {"path": None, "offset": 0}


def read_log_tail(lines: int = 80) -> dict:
    path = latest_log_path()
    if not path.exists():
        return {
            "ok": False,
            "error": f"Log not found: {path}",
            "hint": "Start the dedicated server with ./gradlew runServer",
        }
    text = path.read_text(encoding="utf-8", errors="replace")
    all_lines = text.splitlines()
    lines = max(1, min(lines, 2000))
    return {
        "ok": True,
        "path": str(path),
        "total_lines": len(all_lines),
        "lines": all_lines[-lines:],
    }


def grep_log(pattern: str, lines: int = 200, case_insensitive: bool = True) -> dict:
    path = latest_log_path()
    if not path.exists():
        return {"ok": False, "error": f"Log not found: {path}"}
    flags = re.IGNORECASE if case_insensitive else 0
    try:
        regex = re.compile(pattern, flags)
    except re.error as exc:
        return {"ok": False, "error": f"Invalid regex: {exc}"}
    matches: list[str] = []
    for line in path.read_text(encoding="utf-8", errors="replace").splitlines():
        if regex.search(line):
            matches.append(line)
    lines = max(1, min(lines, 2000))
    return {
        "ok": True,
        "path": str(path),
        "pattern": pattern,
        "match_count": len(matches),
        "matches": matches[-lines:],
    }


def rcon_probe() -> dict:
    config = get_rcon_config()
    try:
        with socket.create_connection((config.host, config.port), timeout=1.5):
            port_open = True
    except OSError as exc:
        return {
            "ok": False,
            "rcon_reachable": False,
            "host": config.host,
            "port": config.port,
            "error": str(exc),
            "hint": (
                "Dedicated server not listening on RCON. "
                "Run tools/enable_dev_rcon.py then ./gradlew runServer"
            ),
        }

    try:
        with RconClient(config) as client:
            listing = client.command("list")
        return {
            "ok": True,
            "rcon_reachable": True,
            "port_open": port_open,
            "host": config.host,
            "port": config.port,
            "list": listing,
            "run_dir": str(get_run_dir()),
            "log_exists": latest_log_path().exists(),
        }
    except RconError as exc:
        return {
            "ok": False,
            "rcon_reachable": False,
            "port_open": port_open,
            "host": config.host,
            "port": config.port,
            "error": str(exc),
        }


def run_rcon_command(command: str) -> dict:
    command = command.strip()
    if not command:
        return {"ok": False, "error": "command is empty"}
    config = get_rcon_config()
    try:
        with RconClient(config) as client:
            response = client.command(command)
        return {
            "ok": True,
            "command": command.lstrip("/"),
            "response": response,
        }
    except (RconError, OSError) as exc:
        return {
            "ok": False,
            "command": command.lstrip("/"),
            "error": str(exc),
            "hint": (
                "Is ./gradlew runServer up with RCON enabled? "
                "Try world_status first."
            ),
        }


def run_rcon_commands(commands: list[str], stop_on_error: bool = True) -> dict:
    results: list[dict] = []
    config = get_rcon_config()
    try:
        with RconClient(config) as client:
            for raw in commands:
                command = raw.strip()
                if not command:
                    results.append({"ok": False, "command": raw, "error": "empty command"})
                    if stop_on_error:
                        break
                    continue
                try:
                    response = client.command(command)
                    results.append(
                        {"ok": True, "command": command.lstrip("/"), "response": response}
                    )
                except RconError as exc:
                    results.append(
                        {"ok": False, "command": command.lstrip("/"), "error": str(exc)}
                    )
                    if stop_on_error:
                        break
    except (RconError, OSError) as exc:
        return {"ok": False, "error": str(exc), "results": results}
    ok = all(item.get("ok") for item in results) if results else False
    return {"ok": ok, "results": results}


def wait_for_log_pattern(
    pattern: str,
    timeout_seconds: float = 30.0,
    case_insensitive: bool = True,
) -> dict:
    path = latest_log_path()
    if not path.exists():
        return {"ok": False, "error": f"Log not found: {path}"}
    try:
        regex = re.compile(pattern, re.IGNORECASE if case_insensitive else 0)
    except re.error as exc:
        return {"ok": False, "error": f"Invalid regex: {exc}"}

    start = time.time()
    offset = path.stat().st_size
    while time.time() - start < timeout_seconds:
        size = path.stat().st_size
        if size < offset:
            offset = 0
        if size > offset:
            with path.open("r", encoding="utf-8", errors="replace") as handle:
                handle.seek(offset)
                chunk = handle.read()
                offset = handle.tell()
            for line in chunk.splitlines():
                if regex.search(line):
                    return {
                        "ok": True,
                        "matched": True,
                        "line": line,
                        "waited_seconds": round(time.time() - start, 2),
                    }
        time.sleep(0.25)
    return {
        "ok": False,
        "matched": False,
        "pattern": pattern,
        "timeout_seconds": timeout_seconds,
        "error": "Timed out waiting for log pattern",
    }


def mark_log_position() -> dict:
    path = latest_log_path()
    if not path.exists():
        return {"ok": False, "error": f"Log not found: {path}"}
    offset = path.stat().st_size
    _log_marker["path"] = str(path)
    _log_marker["offset"] = offset
    return {"ok": True, "path": str(path), "marked_at_offset": offset}


def read_new_log_lines(max_lines: int = 500) -> dict:
    path = latest_log_path()
    if not path.exists():
        return {"ok": False, "error": f"Log not found: {path}"}
    offset = _log_marker.get("offset", 0) if _log_marker.get("path") == str(path) else 0
    size = path.stat().st_size
    if size < offset:
        offset = 0
    with path.open("r", encoding="utf-8", errors="replace") as handle:
        handle.seek(offset)
        chunk = handle.read()
        new_offset = handle.tell()
    _log_marker["path"] = str(path)
    _log_marker["offset"] = new_offset
    lines = chunk.splitlines()
    max_lines = max(1, min(max_lines, 5000))
    return {
        "ok": True,
        "path": str(path),
        "new_line_count": len(lines),
        "lines": lines[-max_lines:],
    }


def list_log_files(limit: int = 30) -> dict:
    directory = logs_dir()
    if not directory.exists():
        return {"ok": False, "error": f"Log directory not found: {directory}"}
    files = sorted(directory.glob("*.log*"), key=lambda p: p.stat().st_mtime, reverse=True)
    limit = max(1, min(limit, 200))
    return {
        "ok": True,
        "directory": str(directory),
        "files": [
            {"name": p.name, "size_bytes": p.stat().st_size, "modified": p.stat().st_mtime}
            for p in files[:limit]
        ],
    }


def read_log_file(name: str, lines: int = 200) -> dict:
    path = logs_dir() / name
    if not path.exists():
        return {"ok": False, "error": f"Log file not found: {path}"}
    opener = gzip.open if path.suffix == ".gz" else open
    try:
        with opener(path, "rt", encoding="utf-8", errors="replace") as handle:
            text = handle.read()
    except OSError as exc:
        return {"ok": False, "error": str(exc)}
    all_lines = text.splitlines()
    lines = max(1, min(lines, 5000))
    return {
        "ok": True,
        "path": str(path),
        "total_lines": len(all_lines),
        "lines": all_lines[-lines:],
    }


def list_crash_reports(limit: int = 20) -> dict:
    directory = crash_reports_dir()
    if not directory.exists():
        return {"ok": True, "directory": str(directory), "count": 0, "reports": []}
    reports = sorted(directory.glob("*.txt"), key=lambda p: p.stat().st_mtime, reverse=True)
    limit = max(1, min(limit, 200))
    return {
        "ok": True,
        "directory": str(directory),
        "count": len(reports),
        "reports": [
            {"name": p.name, "size_bytes": p.stat().st_size, "modified": p.stat().st_mtime}
            for p in reports[:limit]
        ],
    }


def read_crash_report(name: str | None = None, max_chars: int = 8000) -> dict:
    directory = crash_reports_dir()
    if not directory.exists():
        return {"ok": False, "error": f"No crash-reports directory: {directory}"}
    if name:
        path = directory / name
        if not path.exists():
            return {"ok": False, "error": f"Crash report not found: {path}"}
    else:
        reports = sorted(directory.glob("*.txt"), key=lambda p: p.stat().st_mtime, reverse=True)
        if not reports:
            return {"ok": False, "error": "No crash reports found"}
        path = reports[0]
    text = path.read_text(encoding="utf-8", errors="replace")
    truncated = False
    max_chars = max(500, min(max_chars, 50_000))
    if len(text) > max_chars:
        text = text[:max_chars]
        truncated = True
    return {"ok": True, "path": str(path), "truncated": truncated, "content": text}


def run_rcon_command_with_log(
    command: str,
    capture_seconds: float = 2.0,
    pattern: str | None = None,
) -> dict:
    path = latest_log_path()
    offset = path.stat().st_size if path.exists() else 0

    regex = None
    if pattern:
        try:
            regex = re.compile(pattern, re.IGNORECASE)
        except re.error as exc:
            return {"ok": False, "command": command.lstrip("/"), "error": f"Invalid regex: {exc}"}

    command_result = run_rcon_command(command)
    if not command_result.get("ok"):
        return {**command_result, "captured_lines": [], "matched_line": None}

    start = time.time()
    captured: list[str] = []
    matched_line: str | None = None
    while time.time() - start < capture_seconds:
        if path.exists():
            size = path.stat().st_size
            if size > offset:
                with path.open("r", encoding="utf-8", errors="replace") as handle:
                    handle.seek(offset)
                    chunk = handle.read()
                    offset = handle.tell()
                for line in chunk.splitlines():
                    captured.append(line)
                    if regex is not None and matched_line is None and regex.search(line):
                        matched_line = line
            if regex is not None and matched_line is not None:
                break
        time.sleep(0.1)

    _log_marker["path"] = str(path)
    _log_marker["offset"] = offset
    return {**command_result, "captured_lines": captured, "matched_line": matched_line}


def give_item(player: str, item: str, count: int = 1, suffix: str = "") -> dict:
    item_arg = f"{item}{suffix}" if suffix else item
    return run_rcon_command(f"give {player} {item_arg} {count}")


def set_block(x: str, y: str, z: str, block: str, mode: str = "replace", dimension: str | None = None) -> dict:
    command = f"setblock {x} {y} {z} {block} {mode}"
    if dimension:
        command = f"execute in {dimension} run {command}"
    return run_rcon_command(command)


def get_block(x: str, y: str, z: str, dimension: str | None = None) -> dict:
    command = f"data get block {x} {y} {z}"
    if dimension:
        command = f"execute in {dimension} run {command}"
    return run_rcon_command(command)


def fill_blocks(
    x1: str, y1: str, z1: str, x2: str, y2: str, z2: str,
    block: str, mode: str = "replace", dimension: str | None = None,
) -> dict:
    command = f"fill {x1} {y1} {z1} {x2} {y2} {z2} {block} {mode}"
    if dimension:
        command = f"execute in {dimension} run {command}"
    return run_rcon_command(command)


def teleport(selector: str, x: str, y: str, z: str, dimension: str | None = None) -> dict:
    command = f"tp {selector} {x} {y} {z}"
    if dimension:
        command = f"execute in {dimension} run {command}"
    return run_rcon_command(command)


def summon_entity(entity: str, x: str, y: str, z: str, nbt: str = "", dimension: str | None = None) -> dict:
    command = f"summon {entity} {x} {y} {z}"
    if nbt:
        command += f" {nbt}"
    if dimension:
        command = f"execute in {dimension} run {command}"
    return run_rcon_command(command)


def get_player_data(player: str, path: str | None = None) -> dict:
    command = f"data get entity {player}"
    if path:
        command += f" {path}"
    return run_rcon_command(command)


def list_online_players() -> dict:
    result = run_rcon_command("list")
    if not result.get("ok"):
        return result
    response = result.get("response", "")
    names: list[str] = []
    match = re.search(r":\s*(.*)$", response)
    if match:
        tail = match.group(1).strip()
        if tail:
            names = [name.strip() for name in tail.split(",") if name.strip()]
    return {"ok": True, "raw": response, "players": names}


def set_time(value: str) -> dict:
    return run_rcon_command(f"time set {value}")


def set_weather(kind: str, duration_seconds: int | None = None) -> dict:
    command = f"weather {kind}"
    if duration_seconds is not None:
        command += f" {duration_seconds}"
    return run_rcon_command(command)


def set_gamerule(rule: str, value: str) -> dict:
    return run_rcon_command(f"gamerule {rule} {value}")


def save_world(flush: bool = True) -> dict:
    return run_rcon_command("save-all flush" if flush else "save-all")


def _run_mcp_server() -> None:
    try:
        from mcp.server.fastmcp import FastMCP
    except ImportError:
        print(
            "Missing mcp package. Use the project venv:\n"
            "  MarkDown_Maker/Markdown_files/.venv/bin/python "
            "tools/minecraft_world_mcp/server.py",
            file=sys.stderr,
        )
        sys.exit(1)

    mcp = FastMCP("minecraft-world")

    @mcp.tool()
    def world_status() -> dict:
        """Check whether the Re-Forestry dedicated server RCON port is up and usable."""
        return rcon_probe()

    @mcp.tool()
    def run_command(command: str) -> dict:
        """
        Run one Minecraft command on the dedicated server via RCON.

        Args:
            command: command without needing a leading slash, e.g.
                     "give @p reforestry:honey_drop 16" or "time set day".
        """
        return run_rcon_command(command)

    @mcp.tool()
    def run_commands(commands: list[str], stop_on_error: bool = True) -> dict:
        """
        Run several Minecraft commands in order on one RCON connection.

        Args:
            commands: list of commands (slash optional).
            stop_on_error: stop the batch when one command fails.
        """
        return run_rcon_commands(commands, stop_on_error=stop_on_error)

    @mcp.tool()
    def read_latest_log(lines: int = 80) -> dict:
        """
        Read the last N lines from run/logs/latest.log.

        Args:
            lines: how many trailing lines to return (default 80, max 2000).
        """
        return read_log_tail(lines)

    @mcp.tool()
    def grep_latest_log(
        pattern: str, lines: int = 200, case_insensitive: bool = True
    ) -> dict:
        """
        Search run/logs/latest.log with a regex and return matching lines.

        Args:
            pattern: Python regex, e.g. "reforestry|Exception|ERROR".
            lines: max matching lines to return from the end.
            case_insensitive: default True.
        """
        return grep_log(pattern, lines=lines, case_insensitive=case_insensitive)

    @mcp.tool()
    def wait_for_log(
        pattern: str, timeout_seconds: float = 30.0, case_insensitive: bool = True
    ) -> dict:
        """
        Wait until a new log line matching pattern appears (or timeout).

        Useful after run_command when checking for errors or custom debug output.
        """
        return wait_for_log_pattern(
            pattern, timeout_seconds=timeout_seconds, case_insensitive=case_insensitive
        )

    @mcp.tool()
    def run_command_and_capture_log(
        command: str, capture_seconds: float = 2.0, pattern: str | None = None
    ) -> dict:
        """
        Run one command via RCON and also capture the log lines it produces.

        Runs the command, then watches run/logs/latest.log for capture_seconds
        (or until pattern matches, whichever is first). Best tool for "did this
        command work / crash / print an error" checks in one round trip.

        Args:
            command: command without leading slash.
            capture_seconds: how long to watch the log afterward (default 2s).
            pattern: optional regex; stop capturing early once a line matches.
        """
        return run_rcon_command_with_log(command, capture_seconds=capture_seconds, pattern=pattern)

    @mcp.tool()
    def mark_log() -> dict:
        """Record the current end of run/logs/latest.log as a checkpoint for read_new_log()."""
        return mark_log_position()

    @mcp.tool()
    def read_new_log(max_lines: int = 500) -> dict:
        """
        Read log lines written since the last mark_log() call (or since server start
        if never marked). Advances the checkpoint. Use with mark_log() to isolate
        exactly what a test step produced.
        """
        return read_new_log_lines(max_lines)

    @mcp.tool()
    def list_rotated_logs(limit: int = 30) -> dict:
        """List archived log files in run/logs/ (e.g. yesterday's *.log.gz), newest first."""
        return list_log_files(limit)

    @mcp.tool()
    def read_rotated_log(name: str, lines: int = 200) -> dict:
        """
        Read the tail of a specific archived log file from run/logs/ (supports .gz).

        Args:
            name: exact file name from list_rotated_logs, e.g. "2026-07-24-1.log.gz".
            lines: trailing line count to return.
        """
        return read_log_file(name, lines)

    @mcp.tool()
    def list_crashes(limit: int = 20) -> dict:
        """List crash reports in run/crash-reports/, newest first."""
        return list_crash_reports(limit)

    @mcp.tool()
    def read_crash(name: str | None = None, max_chars: int = 8000) -> dict:
        """
        Read a crash report (defaults to the most recent one if name is omitted).

        Args:
            name: exact file name from list_crashes, or omit for the latest.
            max_chars: truncate content to this many characters (default 8000, max 50000).
        """
        return read_crash_report(name, max_chars)

    @mcp.tool()
    def give(player: str, item: str, count: int = 1, suffix: str = "") -> dict:
        """
        Give an item to a player.

        Args:
            player: player name or selector, e.g. "@p" or "@a".
            item: item id, e.g. "reforestry:honey_drop".
            count: stack size.
            suffix: optional raw text appended right after the item id, e.g.
                    component/NBT data such as '[custom_name=\\"Test\\"]'.
        """
        return give_item(player, item, count, suffix)

    @mcp.tool()
    def place_block(
        x: str, y: str, z: str, block: str, mode: str = "replace", dimension: str | None = None
    ) -> dict:
        """
        Place a block at absolute or relative coordinates (setblock).

        Args:
            x, y, z: coordinates, e.g. "100", "~1", "~-2".
            block: block id with optional state, e.g. "reforestry:bee_house[facing=north]".
            mode: "replace", "keep", or "destroy".
            dimension: optional, e.g. "minecraft:the_nether".
        """
        return set_block(x, y, z, block, mode, dimension)

    @mcp.tool()
    def read_block(x: str, y: str, z: str, dimension: str | None = None) -> dict:
        """Read a block's state and NBT/data at the given coordinates (data get block)."""
        return get_block(x, y, z, dimension)

    @mcp.tool()
    def fill_region(
        x1: str, y1: str, z1: str, x2: str, y2: str, z2: str,
        block: str, mode: str = "replace", dimension: str | None = None,
    ) -> dict:
        """Fill a cuboid region between two corners with a block (fill)."""
        return fill_blocks(x1, y1, z1, x2, y2, z2, block, mode, dimension)

    @mcp.tool()
    def teleport_to(selector: str, x: str, y: str, z: str, dimension: str | None = None) -> dict:
        """Teleport an entity/player selector to coordinates (tp), optionally in another dimension."""
        return teleport(selector, x, y, z, dimension)

    @mcp.tool()
    def summon(entity: str, x: str, y: str, z: str, nbt: str = "", dimension: str | None = None) -> dict:
        """
        Summon an entity.

        Args:
            entity: entity id, e.g. "reforestry:some_entity" or "minecraft:villager".
            nbt: optional raw NBT compound, e.g. '{CustomName:\\"test\\"}'.
        """
        return summon_entity(entity, x, y, z, nbt, dimension)

    @mcp.tool()
    def get_player_nbt(player: str, path: str | None = None) -> dict:
        """
        Read a player's entity data (data get entity), optionally a sub-path
        like "Inventory" or "SelectedItem".
        """
        return get_player_data(player, path)

    @mcp.tool()
    def list_players() -> dict:
        """List currently connected player names."""
        return list_online_players()

    @mcp.tool()
    def set_world_time(value: str) -> dict:
        """Set world time, e.g. "day", "night", "noon", or a tick count."""
        return set_time(value)

    @mcp.tool()
    def set_world_weather(kind: str, duration_seconds: int | None = None) -> dict:
        """Set weather: "clear", "rain", or "thunder", with optional duration in seconds."""
        return set_weather(kind, duration_seconds)

    @mcp.tool()
    def set_rule(rule: str, value: str) -> dict:
        """Set a gamerule, e.g. rule="doMobSpawning", value="false"."""
        return set_gamerule(rule, value)

    @mcp.tool()
    def save(flush: bool = True) -> dict:
        """Force-save the world (save-all), useful before inspecting region files directly."""
        return save_world(flush)

    mcp.run(transport="stdio")


def _self_test() -> None:
    print("run_dir:", get_run_dir())
    print("log:", latest_log_path(), "exists=", latest_log_path().exists())
    print("rcon config:", json.dumps(get_rcon_config().__dict__, indent=2))
    print("\n=== world_status() ===")
    print(json.dumps(rcon_probe(), indent=2))
    print("\n=== read_latest_log(20) ===")
    print(json.dumps(read_log_tail(20), indent=2)[:2000])
    print("\n=== list_rotated_logs() ===")
    print(json.dumps(list_log_files(5), indent=2)[:1500])
    print("\n=== list_crashes() ===")
    print(json.dumps(list_crash_reports(5), indent=2))
    print("\n=== mark_log() + read_new_log() (should be empty right after marking) ===")
    print(json.dumps(mark_log_position(), indent=2))
    print(json.dumps(read_new_log_lines(20), indent=2))
    print("\nSelf-test done. If rcon_reachable is false, start ./gradlew runServer.")


if __name__ == "__main__":
    if "--self-test" in sys.argv:
        _self_test()
    else:
        _run_mcp_server()
