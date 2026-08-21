#!/usr/bin/env python3
"""Generate missing arboriculture item models + MC 26.2 items/ defs (ARB-11.9f).

Usage:
  python3 tools/generate_arb_11_9f_assets.py
"""
from __future__ import annotations

import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/reforestry"
MODELS_ITEM = ASSETS / "models/item"
ITEMS_DEF = ASSETS / "items"

PODS = {
    "pods_cocoa": "reforestry:block/pods/cocoa_stage2",
    "pods_dates": "reforestry:block/pods/dates_2",
    "pods_papaya": "reforestry:block/pods/papaya_2",
    "pods_coconut": "reforestry:block/pods/coconut_2",
}

CHARCOAL_BLOCK_ITEMS = (
    "charcoal",
    "log_pile",
    "decorative_log_pile",
    "ash_block",
)


def write_json(path: Path, data: object) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")


def item_def(model_id: str) -> dict:
    return {"model": {"type": "minecraft:model", "model": model_id}}


def main() -> None:
    written = 0
    for item_id, parent in PODS.items():
        write_json(MODELS_ITEM / f"{item_id}.json", {"parent": parent})
        write_json(ITEMS_DEF / f"{item_id}.json", item_def(f"reforestry:item/{item_id}"))
        written += 2

    for item_id in CHARCOAL_BLOCK_ITEMS:
        model_path = MODELS_ITEM / f"{item_id}.json"
        if not model_path.exists():
            raise SystemExit(f"missing models/item for {item_id}")
        write_json(ITEMS_DEF / f"{item_id}.json", item_def(f"reforestry:item/{item_id}"))
        written += 1

    print(f"wrote {written} files under models/item + items/")


if __name__ == "__main__":
    main()
