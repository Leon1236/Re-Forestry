#!/usr/bin/env python3
"""
ingest.py

Parses "repo-dump" style .md files (the format produced by tools like
gitingest/repomix: a directory tree followed by a `## File contents`
section with `### <path>` headers and fenced code blocks) into a
searchable SQLite database with an FTS5 full-text index.

USAGE
-----
Place this script in the same directory as your .md files, then run:

    python3 ingest.py

It will find every *.md file in its own directory, parse them, and
write/update a single database file: mods.db (also in the same directory).

Re-running is safe: each repo's rows are cleared and re-inserted before
re-adding, so you can re-run after adding/removing/updating .md files
without needing to delete mods.db first.

FORMAT ASSUMPTIONS
-------------------
Each .md file looks like:

    # <repo-name>
    ## Directory structure
    ```text
    ...
    ```
    ## File contents

    ### <relative/path/to/file1>

    ```<lang>
    <content>
    ```

    ### <relative/path/to/file2>

    ```<lang>
    <content>
    ```
    ...

The parser locates the FIRST "## File contents" marker, then finds every
"### <path>" header followed by a blank line and an opening fence. Content
for each file is everything between that header's opening fence and the
next "### " header (or end of document) -- NOT naive fence-matching, since
file content can itself contain triple-backtick fences (e.g. markdown
files, or code containing backtick strings) which would break a
"match the next ``` " approach.
"""

import re
import sqlite3
import sys
from pathlib import Path

DB_FILENAME = "mods.db"

# Matches "### <path>\n\n```<lang>\n" -- the header + opening fence of a file entry.
HEADER_RE = re.compile(r"^### (.+?)\n\n```(\w*)\n", re.MULTILINE)

SPLIT_MARKER = "## File contents"


def guess_loader_hint(repo_name: str, paths: list[str]) -> str:
    """Best-effort guess at Fabric vs Forge (old or new) vs unknown, for convenience metadata."""
    has_fabric = any(p.endswith("fabric.mod.json") for p in paths)
    has_forge_new = any(p.endswith("mods.toml") for p in paths)  # 1.13+ Forge
    has_forge_old = any(p.endswith("mcmod.info") for p in paths)  # pre-1.13 Forge
    has_forge = has_forge_new or has_forge_old

    if has_fabric and has_forge:
        return "fabric+forge"
    if has_fabric:
        return "fabric"
    if has_forge_old:
        return "forge (legacy)"
    if has_forge_new:
        return "forge"
    return "unknown"


def parse_md_file(path: Path) -> list[tuple[str, str, str]]:
    """
    Parse one repo-dump .md file.

    Returns a list of (relative_path, language, content) tuples.
    Returns an empty list (with a warning printed) if the expected
    structure isn't found, rather than raising -- so one malformed
    file doesn't abort the whole ingest run.
    """
    try:
        text = path.read_text(encoding="utf-8", errors="replace")
    except Exception as e:
        print(f"  ! Could not read {path.name}: {e}")
        return []

    if SPLIT_MARKER not in text:
        print(f"  ! Skipping {path.name}: no '{SPLIT_MARKER}' marker found")
        return []

    idx = text.index(SPLIT_MARKER)
    body = text[idx + len(SPLIT_MARKER):]

    matches = list(HEADER_RE.finditer(body))
    if not matches:
        print(f"  ! Skipping {path.name}: no file entries matched under '{SPLIT_MARKER}'")
        return []

    entries = []
    for i, m in enumerate(matches):
        file_path = m.group(1).strip()
        lang = m.group(2).strip()
        start = m.end()
        end = matches[i + 1].start() if i + 1 < len(matches) else len(body)
        chunk = body[start:end]

        # The chunk ends with the closing ``` fence for this file (plus
        # trailing blank line before the next header, or EOF). Strip that
        # closing fence off cleanly.
        stripped = chunk.rstrip()
        if stripped.endswith("```"):
            content = stripped[:-3].rstrip("\n")
        else:
            # Malformed / truncated entry (e.g. unbalanced fence). Keep
            # what we have rather than dropping the file entirely.
            content = chunk.rstrip("\n")

        entries.append((file_path, lang, content))

    return entries


def init_db(conn: sqlite3.Connection) -> None:
    conn.executescript(
        """
        CREATE TABLE IF NOT EXISTS repos (
            name        TEXT PRIMARY KEY,
            source_file TEXT NOT NULL,
            loader_hint TEXT,
            file_count  INTEGER,
            total_bytes INTEGER
        );

        CREATE TABLE IF NOT EXISTS files (
            id       INTEGER PRIMARY KEY AUTOINCREMENT,
            repo     TEXT NOT NULL,
            path     TEXT NOT NULL,
            language TEXT,
            content  TEXT NOT NULL,
            size     INTEGER NOT NULL,
            UNIQUE(repo, path)
        );

        CREATE INDEX IF NOT EXISTS idx_files_repo ON files(repo);
        CREATE INDEX IF NOT EXISTS idx_files_path ON files(path);

        CREATE VIRTUAL TABLE IF NOT EXISTS files_fts USING fts5(
            path,
            content,
            content='files',
            content_rowid='id',
            tokenize='unicode61 tokenchars ''_.$'''
        );

        -- Keep FTS index in sync with the files table.
        CREATE TRIGGER IF NOT EXISTS files_ai AFTER INSERT ON files BEGIN
            INSERT INTO files_fts(rowid, path, content)
            VALUES (new.id, new.path, new.content);
        END;

        CREATE TRIGGER IF NOT EXISTS files_ad AFTER DELETE ON files BEGIN
            INSERT INTO files_fts(files_fts, rowid, path, content)
            VALUES ('delete', old.id, old.path, old.content);
        END;

        CREATE TRIGGER IF NOT EXISTS files_au AFTER UPDATE ON files BEGIN
            INSERT INTO files_fts(files_fts, rowid, path, content)
            VALUES ('delete', old.id, old.path, old.content);
            INSERT INTO files_fts(rowid, path, content)
            VALUES (new.id, new.path, new.content);
        END;
        """
    )
    conn.commit()


def ingest_repo(conn: sqlite3.Connection, repo_name: str, source_file: str, entries: list[tuple[str, str, str]]) -> None:
    cur = conn.cursor()

    # Clear any previous rows for this repo so re-running is idempotent.
    cur.execute("DELETE FROM files WHERE repo = ?", (repo_name,))

    total_bytes = 0
    for file_path, lang, content in entries:
        size = len(content.encode("utf-8", errors="replace"))
        total_bytes += size
        cur.execute(
            "INSERT OR REPLACE INTO files (repo, path, language, content, size) VALUES (?, ?, ?, ?, ?)",
            (repo_name, file_path, lang, content, size),
        )

    loader_hint = guess_loader_hint(repo_name, [e[0] for e in entries])

    cur.execute(
        """
        INSERT INTO repos (name, source_file, loader_hint, file_count, total_bytes)
        VALUES (?, ?, ?, ?, ?)
        ON CONFLICT(name) DO UPDATE SET
            source_file = excluded.source_file,
            loader_hint = excluded.loader_hint,
            file_count  = excluded.file_count,
            total_bytes = excluded.total_bytes
        """,
        (repo_name, source_file, loader_hint, len(entries), total_bytes),
    )

    conn.commit()


def main() -> None:
    script_dir = Path(__file__).resolve().parent
    md_files = sorted(script_dir.glob("*.md"))

    if not md_files:
        print(f"No .md files found in {script_dir}. Place this script next to your .md dumps and re-run.")
        sys.exit(1)

    db_path = script_dir / DB_FILENAME
    print(f"Database: {db_path}")
    print(f"Found {len(md_files)} .md file(s) in {script_dir}\n")

    conn = sqlite3.connect(db_path)
    conn.execute("PRAGMA journal_mode=WAL")
    init_db(conn)

    grand_total_files = 0
    grand_total_bytes = 0

    for md_path in md_files:
        repo_name = md_path.stem  # e.g. "thedarkcolour-gendustry"
        print(f"Parsing {md_path.name} ...")

        entries = parse_md_file(md_path)
        if not entries:
            continue

        ingest_repo(conn, repo_name, md_path.name, entries)

        n_files = len(entries)
        n_bytes = sum(len(c.encode("utf-8", errors="replace")) for _, _, c in entries)
        grand_total_files += n_files
        grand_total_bytes += n_bytes

        print(f"  -> {repo_name}: {n_files} files, {n_bytes / 1024:.0f} KB content")

    # Rebuild FTS index from scratch to guarantee consistency (cheap at this scale).
    print("\nRebuilding full-text search index...")
    conn.execute("INSERT INTO files_fts(files_fts) VALUES ('rebuild')")
    conn.commit()

    print("\n--- Summary ---")
    for row in conn.execute("SELECT name, loader_hint, file_count, total_bytes FROM repos ORDER BY name"):
        name, loader_hint, file_count, total_bytes = row
        print(f"  {name:35s} [{loader_hint:12s}] {file_count:5d} files  {total_bytes/1024:8.0f} KB")

    print(f"\nTotal: {grand_total_files} files, {grand_total_bytes / 1024 / 1024:.1f} MB content")
    print(f"Database written to: {db_path}")

    conn.close()


if __name__ == "__main__":
    main()
