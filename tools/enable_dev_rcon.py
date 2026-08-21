#!/usr/bin/env python3
"""Enable local-dev RCON on run/server.properties. Usage: python tools/enable_dev_rcon.py"""

from __future__ import annotations

import argparse
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parents[1]
DEFAULT_PROPERTIES = REPO_ROOT / "run" / "server.properties"
DEFAULT_PASSWORD = "reforestry-dev"
DEFAULT_PORT = 25575


def set_property(lines: list[str], key: str, value: str) -> list[str]:
    prefix = f"{key}="
    updated = False
    result: list[str] = []
    for line in lines:
        if line.startswith(prefix):
            result.append(f"{key}={value}")
            updated = True
        else:
            result.append(line)
    if not updated:
        result.append(f"{key}={value}")
    return result


def enable_rcon(
    properties_path: Path,
    password: str,
    port: int,
    never_pause_when_empty: bool,
) -> None:
    properties_path.parent.mkdir(parents=True, exist_ok=True)
    if properties_path.exists():
        lines = properties_path.read_text(encoding="utf-8").splitlines()
    else:
        lines = []

    lines = set_property(lines, "enable-rcon", "true")
    lines = set_property(lines, "rcon.password", password)
    lines = set_property(lines, "rcon.port", str(port))
    lines = set_property(lines, "broadcast-rcon-to-ops", "true")
    lines = set_property(lines, "online-mode", "false")
    lines = set_property(lines, "enforce-secure-profile", "false")
    lines = set_property(lines, "spawn-protection", "0")
    if never_pause_when_empty:
        lines = set_property(lines, "pause-when-empty-seconds", "-1")

    properties_path.write_text("\n".join(lines) + "\n", encoding="utf-8")

    # Stable offline op for loom client --username Dev (see build.gradle)
    ops_path = properties_path.parent / "ops.json"
    import hashlib
    import json
    import uuid

    def offline_uuid(name: str) -> str:
        md5 = hashlib.md5(f"OfflinePlayer:{name}".encode("utf-8")).digest()
        b = bytearray(md5)
        b[6] = (b[6] & 0x0F) | 0x30
        b[8] = (b[8] & 0x3F) | 0x80
        return str(uuid.UUID(bytes=bytes(b)))

    ops = [
        {
            "uuid": offline_uuid("Dev"),
            "name": "Dev",
            "level": 4,
            "bypassesPlayerLimit": True,
        }
    ]
    if ops_path.exists():
        try:
            existing = json.loads(ops_path.read_text(encoding="utf-8") or "[]")
        except json.JSONDecodeError:
            existing = []
        by_name = {entry.get("name"): entry for entry in existing if isinstance(entry, dict)}
        by_name["Dev"] = ops[0]
        ops = list(by_name.values())
    ops_path.write_text(json.dumps(ops, indent=2) + "\n", encoding="utf-8")


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--properties",
        type=Path,
        default=DEFAULT_PROPERTIES,
        help="Path to server.properties (default: run/server.properties)",
    )
    parser.add_argument("--password", default=DEFAULT_PASSWORD)
    parser.add_argument("--port", type=int, default=DEFAULT_PORT)
    parser.add_argument(
        "--allow-pause-when-empty",
        action="store_true",
        help="Do not force pause-when-empty-seconds=-1",
    )
    args = parser.parse_args()

    enable_rcon(
        properties_path=args.properties.resolve(),
        password=args.password,
        port=args.port,
        never_pause_when_empty=not args.allow_pause_when_empty,
    )
    print(f"Updated {args.properties.resolve()}")
    print(f"  enable-rcon=true")
    print(f"  rcon.port={args.port}")
    print(f"  rcon.password={args.password}")
    print("  online-mode=false")
    print("  enforce-secure-profile=false")
    print("  spawn-protection=0")
    print("  ops.json includes offline op 'Dev'")
    if not args.allow_pause_when_empty:
        print("  pause-when-empty-seconds=-1")
    print("Restart the dedicated server for changes to apply: ./gradlew runServer")


if __name__ == "__main__":
    main()
