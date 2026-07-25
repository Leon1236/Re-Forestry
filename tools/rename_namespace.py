#!/usr/bin/env python3
"""Renames a Minecraft resource namespace across a mod's resources tree.

Usage:
  python3 tools/rename_namespace.py --old forestry --new reforestry [--apply]

Without --apply this only prints what would change (dry run). Pass --apply to
write the changes. Safe to run twice (idempotent) - already-renamed content is
left alone.

What it does, under src/main/resources/{assets,data,config}/:
  1. Renames any directory whose name is exactly `old` to `new` (namespace
     root folders, and any nested folder that happens to share the name,
     e.g. assets/<old>/textures/<old>/...).
  2. In .json/.mcmeta/.lang/.txt files, rewrites:
       - resource-location namespace prefixes: "old:" -> "new:"
       - translation-key segments: ".old." -> ".new."
  3. In .nbt structure files (gzip-compressed NBT), rewrites TAG_String
     values that start with "old:" to start with "new:", re-encoding the
     NBT length prefixes correctly (a plain text find/replace would corrupt
     these, since NBT strings are length-prefixed).

Does not touch .png or other binary/image files - textures are referenced by
path, not by namespace string, so only the folder they live in needs moving.
"""
import argparse
import gzip
import re
import struct
import sys
from pathlib import Path

TEXT_EXTENSIONS = {".json", ".mcmeta", ".lang", ".txt"}


# Only rename "old" when it's clearly being used as a resource-location or
# path token (touching one of these delimiters), never as a standalone word
# in prose - translated descriptions can legitimately contain plain English
# words that happen to match, so a bare word-boundary match isn't safe here.
_DELIMITERS = ':/."\''


def make_pattern(old: str):
    escaped = re.escape(old)
    before = r"(?<![A-Za-z0-9_-])"
    after = r"(?=[" + re.escape(_DELIMITERS) + r"]|$)"
    return re.compile(before + escaped + after)


def rewrite_text(content: str, old: str, new: str):
    pattern = make_pattern(old)
    changed_lines = []

    def sub_line(line):
        return pattern.subn(new, line)

    out_lines = []
    total = 0
    for line in content.splitlines(keepends=True):
        new_line, count = sub_line(line)
        if count:
            total += count
            changed_lines.append((line.rstrip("\n"), new_line.rstrip("\n")))
        out_lines.append(new_line)
    return "".join(out_lines), total, changed_lines


# --- minimal NBT reader/writer -----------------------------------------
# Only String tags are decoded into text; every other tag type is kept as
# opaque raw bytes and copied through unchanged. This avoids needing a full
# NBT value model while still letting us find/rewrite embedded strings.

def _read_payload(tag_id, buf, i):
    if tag_id in (1,):  # byte
        return ("raw", buf[i:i + 1]), i + 1
    if tag_id in (2,):  # short
        return ("raw", buf[i:i + 2]), i + 2
    if tag_id in (3, 5):  # int, float
        return ("raw", buf[i:i + 4]), i + 4
    if tag_id in (4, 6):  # long, double
        return ("raw", buf[i:i + 8]), i + 8
    if tag_id == 7:  # byte array
        (n,) = struct.unpack_from(">i", buf, i)
        total = 4 + n
        return ("raw", buf[i:i + total]), i + total
    if tag_id == 8:  # string
        (length,) = struct.unpack_from(">H", buf, i)
        s = buf[i + 2:i + 2 + length]
        return ("string", s), i + 2 + length
    if tag_id == 9:  # list
        elem_type = buf[i]
        i += 1
        (count,) = struct.unpack_from(">i", buf, i)
        i += 4
        items = []
        for _ in range(max(count, 0)):
            val, i = _read_payload(elem_type, buf, i)
            items.append(val)
        return ("list", elem_type, items), i
    if tag_id == 10:  # compound
        entries = []
        while True:
            t = buf[i]
            i += 1
            if t == 0:
                entries.append(("end",))
                break
            (nlen,) = struct.unpack_from(">H", buf, i)
            i += 2
            name = buf[i:i + nlen]
            i += nlen
            val, i = _read_payload(t, buf, i)
            entries.append((t, name, val))
        return ("compound", entries), i
    if tag_id == 11:  # int array
        (n,) = struct.unpack_from(">i", buf, i)
        total = 4 + n * 4
        return ("raw", buf[i:i + total]), i + total
    if tag_id == 12:  # long array
        (n,) = struct.unpack_from(">i", buf, i)
        total = 4 + n * 8
        return ("raw", buf[i:i + total]), i + total
    raise ValueError(f"unknown NBT tag id {tag_id}")


def _rewrite_payload(node, old_prefix, new_prefix, samples):
    kind = node[0]
    if kind == "raw":
        return node[1]
    if kind == "string":
        raw = node[1]
        try:
            text = raw.decode("utf-8")
        except UnicodeDecodeError:
            return struct.pack(">H", len(raw)) + raw
        if text.startswith(old_prefix):
            new_text = new_prefix + text[len(old_prefix):]
            samples.append((text, new_text))
            encoded = new_text.encode("utf-8")
        else:
            encoded = raw
        return struct.pack(">H", len(encoded)) + encoded
    if kind == "list":
        _, elem_type, items = node
        out = bytes([elem_type]) + struct.pack(">i", len(items))
        for item in items:
            out += _rewrite_payload(item, old_prefix, new_prefix, samples)
        return out
    if kind == "compound":
        out = b""
        for entry in node[1]:
            if entry[0] == "end":
                out += bytes([0])
                continue
            t, name, val = entry
            out += bytes([t]) + struct.pack(">H", len(name)) + name
            out += _rewrite_payload(val, old_prefix, new_prefix, samples)
        return out
    raise ValueError(kind)


def rewrite_nbt(raw_bytes: bytes, old: str, new: str):
    data = gzip.decompress(raw_bytes)
    root_type = data[0]
    (nlen,) = struct.unpack_from(">H", data, 1)
    root_name = data[3:3 + nlen]
    val, _ = _read_payload(root_type, data, 3 + nlen)

    samples = []
    payload_bytes = _rewrite_payload(val, old + ":", new + ":", samples)
    if not samples:
        return None, []

    out = bytes([root_type]) + struct.pack(">H", len(root_name)) + root_name + payload_bytes
    return gzip.compress(out, mtime=0), samples


# --- driver --------------------------------------------------------------

def rename_directories(resources_root: Path, old: str, new: str, apply: bool, log):
    renamed = []
    for base in ("assets", "data", "config"):
        base_dir = resources_root / base
        if not base_dir.is_dir():
            continue
        # Walk bottom-up so renaming a parent doesn't invalidate paths to children.
        candidates = [p for p in base_dir.rglob(old) if p.is_dir()]
        candidates.sort(key=lambda p: len(p.parts), reverse=True)
        for old_path in candidates:
            new_path = old_path.with_name(new)
            renamed.append((old_path, new_path))
            log(f"DIR  {old_path.relative_to(resources_root)} -> {new_path.relative_to(resources_root)}")
            if apply:
                old_path.rename(new_path)
    return renamed


def process_text_files(resources_root: Path, old: str, new: str, apply: bool, log):
    changed_files = 0
    total_replacements = 0
    for path in sorted(resources_root.rglob("*")):
        if not path.is_file() or path.suffix.lower() not in TEXT_EXTENSIONS:
            continue
        content = path.read_text(encoding="utf-8")
        new_content, count, changed_lines = rewrite_text(content, old, new)
        if count:
            changed_files += 1
            total_replacements += count
            log(f"TEXT {path.relative_to(resources_root)}: {count} change(s)")
            for before, after in changed_lines[:5]:
                log(f"       - {before.strip()}")
                log(f"       + {after.strip()}")
            if len(changed_lines) > 5:
                log(f"       ... and {len(changed_lines) - 5} more line(s)")
            if apply:
                path.write_text(new_content, encoding="utf-8")
    return changed_files, total_replacements


def process_nbt_files(resources_root: Path, old: str, new: str, apply: bool, log):
    changed_files = 0
    total_replacements = 0
    for path in sorted(resources_root.rglob("*.nbt")):
        raw = path.read_bytes()
        try:
            new_bytes, samples = rewrite_nbt(raw, old, new)
        except Exception as exc:  # noqa: BLE001 - report and continue
            log(f"NBT  {path.relative_to(resources_root)}: SKIPPED ({exc})")
            continue
        if not samples:
            continue
        changed_files += 1
        total_replacements += len(samples)
        log(f"NBT  {path.relative_to(resources_root)}: {len(samples)} string(s)")
        for before, after in samples[:5]:
            log(f"       - {before}")
            log(f"       + {after}")
        if len(samples) > 5:
            log(f"       ... and {len(samples) - 5} more")
        if apply:
            path.write_bytes(new_bytes)
    return changed_files, total_replacements


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--old", required=True, help="namespace to rename from, e.g. forestry")
    parser.add_argument("--new", required=True, help="namespace to rename to, e.g. reforestry")
    parser.add_argument("--root", default=".", help="project root (default: current directory)")
    parser.add_argument("--apply", action="store_true", help="write changes (default: dry run)")
    args = parser.parse_args()

    resources_root = Path(args.root) / "src" / "main" / "resources"
    if not resources_root.is_dir():
        print(f"error: {resources_root} not found", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] renaming namespace '{args.old}' -> '{args.new}' under {resources_root}\n")

    log = print

    rename_directories(resources_root, args.old, args.new, args.apply, log)
    text_files, text_changes = process_text_files(resources_root, args.old, args.new, args.apply, log)
    nbt_files, nbt_changes = process_nbt_files(resources_root, args.old, args.new, args.apply, log)

    print(f"\nsummary: {text_files} text file(s) / {text_changes} replacement(s), "
          f"{nbt_files} nbt file(s) / {nbt_changes} string(s)")
    if not args.apply:
        print("dry run only - rerun with --apply to write changes")


if __name__ == "__main__":
    main()
