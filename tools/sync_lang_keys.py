#!/usr/bin/env python3
# Usage: python3 tools/sync_lang_keys.py
"""Copy missing translation keys from en_us.json into other lang/*.json with English text."""

from __future__ import annotations

import json
from pathlib import Path

LANG_DIR = Path(__file__).resolve().parents[1] / "src/main/resources/assets/reforestry/lang"
SOURCE_NAME = "en_us.json"


def load_json(path: Path) -> dict[str, str]:
	with path.open(encoding="utf-8") as handle:
		data = json.load(handle)
	if not isinstance(data, dict):
		raise SystemExit(f"{path}: expected a JSON object")
	return {str(key): str(value) for key, value in data.items()}


def dump_json(path: Path, data: dict[str, str]) -> None:
	with path.open("w", encoding="utf-8", newline="\n") as handle:
		json.dump(data, handle, ensure_ascii=False, indent=2)
		handle.write("\n")


def main() -> None:
	source_path = LANG_DIR / SOURCE_NAME
	if not source_path.is_file():
		raise SystemExit(f"missing source lang file: {source_path}")

	source = load_json(source_path)
	total_added = 0

	for path in sorted(LANG_DIR.glob("*.json")):
		if path.name == SOURCE_NAME:
			continue
		target = load_json(path)
		missing = {key: value for key, value in source.items() if key not in target}
		if not missing:
			print(f"{path.name}: already complete ({len(target)} keys)")
			continue
		merged = dict(target)
		merged.update(missing)
		dump_json(path, merged)
		total_added += len(missing)
		print(f"{path.name}: added {len(missing)} keys ({len(target)} -> {len(merged)})")

	print(f"done: added {total_added} missing keys across lang files")


if __name__ == "__main__":
	main()
