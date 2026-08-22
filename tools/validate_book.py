#!/usr/bin/env python3
# Usage: python3 tools/validate_book.py
"""Validate Forester's Manual Patchouli JSON against lang keys, categories, and recipes."""

from __future__ import annotations

import json
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BOOK_ROOT = ROOT / "src/main/resources/assets/reforestry/patchouli_books/foresters_manual/en_us"
ENTRY_ROOT = BOOK_ROOT / "entries"
CATEGORY_ROOT = BOOK_ROOT / "categories"
LANG_PATH = ROOT / "src/main/resources/assets/reforestry/lang/en_us.json"
RECIPE_DIR = ROOT / "src/main/resources/data/reforestry/recipe"
ENTRY_PREFIX = "for.gui.book.patchouli.entry."
CATEGORY_PREFIX = "for.gui.book.patchouli.category."


def load_json(path: Path) -> dict:
	with path.open(encoding="utf-8") as handle:
		return json.load(handle)


def category_ids() -> set[str]:
	ids: set[str] = set()
	for path in sorted(CATEGORY_ROOT.glob("*.json")):
		ids.add(f"reforestry:{path.stem}")
	return ids


def recipe_ids() -> set[str]:
	ids: set[str] = set()
	for path in RECIPE_DIR.rglob("*.json"):
		ids.add(f"reforestry:{path.relative_to(RECIPE_DIR).with_suffix('').as_posix()}")
	return ids


def entry_id(path: Path) -> str:
	relative = path.relative_to(ENTRY_ROOT).with_suffix("")
	return relative.as_posix()


def collect_lang_keys(data: dict) -> list[str]:
	keys: list[str] = []
	if "name" in data:
		keys.append(data["name"])
	if "description" in data:
		keys.append(data["description"])
	for page in data.get("pages", []):
		for field in ("text", "title"):
			if field in page:
				keys.append(page[field])
	return keys


def main() -> int:
	lang = load_json(LANG_PATH)
	categories = category_ids()
	recipes = recipe_ids()
	errors: list[str] = []
	warnings: list[str] = []
	entry_count = 0
	category_count = 0

	for path in sorted(CATEGORY_ROOT.glob("*.json")):
		category_count += 1
		data = load_json(path)
		category_key = f"reforestry:{path.stem}"
		for key in collect_lang_keys(data):
			if key not in lang:
				errors.append(f"category {category_key}: missing lang key {key}")

	for path in sorted(ENTRY_ROOT.rglob("*.json")):
		entry_count += 1
		data = load_json(path)
		eid = entry_id(path)
		name_key = data.get("name", "")
		if not name_key.startswith(ENTRY_PREFIX):
			errors.append(f"entry {eid}: name key must start with {ENTRY_PREFIX}")
		for key in collect_lang_keys(data):
			if key not in lang:
				errors.append(f"entry {eid}: missing lang key {key}")
		category = data.get("category", "")
		if category not in categories:
			errors.append(f"entry {eid}: unknown category {category}")
		if "icon" in data and "{" in data["icon"]:
			warnings.append(f"entry {eid}: NBT icon may fail BookLoader parsing")
		if "__COMMENT__" in data:
			errors.append(f"entry {eid}: contains __COMMENT__ field")
		for index, page in enumerate(data.get("pages", [])):
			for field in ("recipe", "recipe2"):
				if field in page and page[field] not in recipes:
					errors.append(f"entry {eid} page {index}: missing recipe {page[field]}")

	print(f"Forester's Manual validation")
	print(f"  categories: {category_count}")
	print(f"  entries: {entry_count}")
	print(f"  lang keys checked: {len(lang)}")
	print(f"  recipes indexed: {len(recipes)}")
	if warnings:
		print(f"  warnings: {len(warnings)}")
		for warning in warnings:
			print(f"    WARN {warning}")
	if errors:
		print(f"  errors: {len(errors)}")
		for error in errors:
			print(f"    ERROR {error}")
		return 1
	print("  result: OK")
	return 0


if __name__ == "__main__":
	sys.exit(main())
