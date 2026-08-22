#!/usr/bin/env python3
"""
server.py

MCP server that exposes the mods.db SQLite database (built by ingest.py)
to Claude Desktop over stdio.

Tools:
  - list_mods()                        list all indexed mod repos + metadata
  - search_code(query, repo?, ...)     full-text search over path + content
  - get_file(repo, path)               exact full content of one file
  - list_files(repo, path_prefix?)     browse files within a repo

SETUP
-----
1. Install the MCP SDK:
     pip install mcp

2. Make sure mods.db (built by ingest.py) is in the same directory as this
   script, OR set the MODS_DB_PATH environment variable to point at it.

3. Add this to your Claude Desktop config (claude_desktop_config.json):

     {
       "mcpServers": {
         "minecraft-mods": {
           "command": "python3",
           "args": ["/absolute/path/to/server.py"]
         }
       }
     }

   Config file locations:
     macOS:   ~/Library/Application Support/Claude/claude_desktop_config.json
     Windows: %APPDATA%\\Claude\\claude_desktop_config.json
     Linux:   ~/.config/Claude/claude_desktop_config.json

4. Restart Claude Desktop. The tools above should appear as available.

NOTE ON TESTING
----------------
The query logic in this file (everything in the "core query functions"
section) is plain Python that can be run and tested directly against
mods.db without any MCP machinery -- see the __main__ block at the
bottom, which runs a few sanity queries when you execute:

    python3 server.py --self-test

This is useful for confirming the database and queries work correctly
*before* wiring things into Claude Desktop, where failures are harder
to see directly.
"""

import os
import sqlite3
import sys
from pathlib import Path

# ---------------------------------------------------------------------------
# Database location
# ---------------------------------------------------------------------------

DEFAULT_DB_NAME = "mods.db"


def get_db_path() -> Path:
    env_path = os.environ.get("MODS_DB_PATH")
    if env_path:
        return Path(env_path).expanduser().resolve()
    return (Path(__file__).resolve().parent / DEFAULT_DB_NAME)


def get_connection() -> sqlite3.Connection:
    db_path = get_db_path()
    if not db_path.exists():
        raise FileNotFoundError(
            f"mods.db not found at {db_path}. Run ingest.py first, or set "
            f"the MODS_DB_PATH environment variable to point at your database."
        )
    conn = sqlite3.connect(str(db_path))
    conn.row_factory = sqlite3.Row
    return conn


# ---------------------------------------------------------------------------
# Core query functions (plain Python -- no MCP dependency, directly testable)
# ---------------------------------------------------------------------------

MAX_RESULTS_CAP = 50
MAX_CONTENT_CHARS = 20_000  # safety cap per file returned, to avoid flooding context
SNIPPET_CHARS = 300


def _sanitize_fts_query(query: str) -> str:
    """
    FTS5 MATCH has its own query syntax (AND/OR/NOT, quoting, prefix*, etc).
    To keep this tool predictable for arbitrary user/model input, we treat
    the query as a set of AND'd terms unless the caller already used FTS5
    operators, in which case we pass it through mostly as-is.
    """
    query = query.strip()
    if not query:
        return query

    fts_operators = {"AND", "OR", "NOT", "NEAR"}
    tokens = query.split()
    looks_advanced = (
        any(t.upper() in fts_operators for t in tokens)
        or '"' in query
        or "*" in query
        or ":" in query
    )
    if looks_advanced:
        return query

    # Plain phrase: quote each token and AND them together so e.g.
    # "onBlockPlaced ItemStack" requires both terms, and punctuation-heavy
    # identifiers like "net.minecraft.Foo" don't break FTS5's tokenizer/parser.
    quoted = [f'"{t}"' for t in tokens if t]
    return " AND ".join(quoted)


def list_mods() -> list[dict]:
    """Return metadata for every indexed mod repo."""
    conn = get_connection()
    try:
        rows = conn.execute(
            "SELECT name, loader_hint, file_count, total_bytes, source_file "
            "FROM repos ORDER BY name"
        ).fetchall()
        return [
            {
                "repo": r["name"],
                "loader": r["loader_hint"],
                "file_count": r["file_count"],
                "total_kb": round(r["total_bytes"] / 1024, 1),
                "source_file": r["source_file"],
            }
            for r in rows
        ]
    finally:
        conn.close()


def search_code(query: str, repo: str | None = None, language: str | None = None, limit: int = 15) -> dict:
    """
    Full-text search over file paths and contents.

    Returns ranked results with a short snippet showing the match in
    context, plus repo/path/language so a follow-up get_file() call can
    fetch the full content if needed.
    """
    if not query or not query.strip():
        return {"error": "query must not be empty"}

    limit = max(1, min(limit, MAX_RESULTS_CAP))
    fts_query = _sanitize_fts_query(query)

    conn = get_connection()
    try:
        sql = """
            SELECT f.repo, f.path, f.language, f.size,
                   snippet(files_fts, 1, '>>>', '<<<', ' ... ', 12) AS snippet,
                   rank
            FROM files_fts
            JOIN files f ON f.id = files_fts.rowid
            WHERE files_fts MATCH ?
        """
        params: list = [fts_query]

        if repo:
            sql += " AND f.repo = ?"
            params.append(repo)
        if language:
            sql += " AND f.language = ?"
            params.append(language)

        sql += " ORDER BY rank LIMIT ?"
        params.append(limit)

        try:
            rows = conn.execute(sql, params).fetchall()
        except sqlite3.OperationalError as e:
            return {"error": f"search query could not be parsed: {e}", "query_used": fts_query}

        results = [
            {
                "repo": r["repo"],
                "path": r["path"],
                "language": r["language"],
                "size_bytes": r["size"],
                "snippet": r["snippet"],
            }
            for r in rows
        ]
        return {"query": query, "result_count": len(results), "results": results}
    finally:
        conn.close()


def get_file(repo: str, path: str) -> dict:
    """Return the full content of one exact file, identified by repo + path."""
    conn = get_connection()
    try:
        row = conn.execute(
            "SELECT repo, path, language, content, size FROM files WHERE repo = ? AND path = ?",
            (repo, path),
        ).fetchone()

        if row is None:
            # Help the caller correct a near-miss path rather than just failing.
            similar = conn.execute(
                "SELECT path FROM files WHERE repo = ? AND path LIKE ? LIMIT 10",
                (repo, f"%{Path(path).name}%"),
            ).fetchall()
            return {
                "error": f"No file found at repo={repo!r} path={path!r}",
                "did_you_mean": [r["path"] for r in similar],
            }

        content = row["content"]
        truncated = False
        if len(content) > MAX_CONTENT_CHARS:
            content = content[:MAX_CONTENT_CHARS]
            truncated = True

        return {
            "repo": row["repo"],
            "path": row["path"],
            "language": row["language"],
            "size_bytes": row["size"],
            "content": content,
            "truncated": truncated,
        }
    finally:
        conn.close()


def list_files(repo: str, path_prefix: str | None = None, limit: int = 200) -> dict:
    """List file paths within a repo, optionally filtered by a path prefix (directory)."""
    limit = max(1, min(limit, 1000))
    conn = get_connection()
    try:
        repo_exists = conn.execute("SELECT 1 FROM repos WHERE name = ?", (repo,)).fetchone()
        if not repo_exists:
            all_repos = [r["name"] for r in conn.execute("SELECT name FROM repos ORDER BY name")]
            return {"error": f"No repo named {repo!r}", "available_repos": all_repos}

        if path_prefix:
            rows = conn.execute(
                "SELECT path, language, size FROM files WHERE repo = ? AND path LIKE ? "
                "ORDER BY path LIMIT ?",
                (repo, f"{path_prefix}%", limit),
            ).fetchall()
        else:
            rows = conn.execute(
                "SELECT path, language, size FROM files WHERE repo = ? ORDER BY path LIMIT ?",
                (repo, limit),
            ).fetchall()

        return {
            "repo": repo,
            "path_prefix": path_prefix,
            "file_count": len(rows),
            "files": [{"path": r["path"], "language": r["language"], "size_bytes": r["size"]} for r in rows],
        }
    finally:
        conn.close()


# ---------------------------------------------------------------------------
# MCP server wiring
# ---------------------------------------------------------------------------
#
# Everything below this point is thin glue code that registers the plain
# Python functions above as MCP tools. It requires the `mcp` package
# (pip install mcp) and a real MCP client (Claude Desktop) to actually run
# as a server -- it cannot be exercised in a sandbox without that SDK.

def _run_mcp_server() -> None:
    try:
        from mcp.server.fastmcp import FastMCP
    except ImportError:
        print(
            "The 'mcp' package is not installed. Run:\n\n"
            "    pip install mcp\n\n"
            "then re-run this script, or configure Claude Desktop to launch it "
            "(Claude Desktop will use its own Python environment where 'mcp' "
            "should be installed).",
            file=sys.stderr,
        )
        sys.exit(1)

    mcp = FastMCP("minecraft-mods")

    @mcp.tool()
    def list_mods_tool() -> list[dict]:
        """List all indexed Minecraft mod repositories with their loader type (Fabric/Forge), file count, and size."""
        return list_mods()

    @mcp.tool()
    def search_code_tool(query: str, repo: str | None = None, language: str | None = None, limit: int = 15) -> dict:
        """
        Full-text search across all indexed mod source code and resource files.

        Args:
            query: search terms (e.g. a class name, method name, or keyword).
                   Plain text is treated as AND'd terms; FTS5 syntax (AND/OR/NOT,
                   "quoted phrases", prefix*) is also supported.
            repo: optional exact repo name to restrict the search to (see list_mods).
            language: optional file extension to restrict to (e.g. "java", "json", "toml").
            limit: max number of results (default 15, max 50).
        """
        return search_code(query, repo=repo, language=language, limit=limit)

    @mcp.tool()
    def get_file_tool(repo: str, path: str) -> dict:
        """
        Fetch the full content of one exact file.

        Args:
            repo: exact repo name (see list_mods).
            path: exact relative file path within that repo (see search_code or list_files results).
        """
        return get_file(repo, path)

    @mcp.tool()
    def list_files_tool(repo: str, path_prefix: str | None = None, limit: int = 200) -> dict:
        """
        List file paths within a mod repo, optionally filtered to a subdirectory.

        Args:
            repo: exact repo name (see list_mods).
            path_prefix: optional path prefix to filter by, e.g. "src/main/java/".
            limit: max number of files to list (default 200, max 1000).
        """
        return list_files(repo, path_prefix=path_prefix, limit=limit)

    mcp.run(transport="stdio")


# ---------------------------------------------------------------------------
# Self-test entry point (no MCP dependency required)
# ---------------------------------------------------------------------------

def _self_test() -> None:
    print(f"Using database: {get_db_path()}\n")

    print("=== list_mods() ===")
    for m in list_mods():
        print(f"  {m['repo']:35s} [{m['loader']:15s}] {m['file_count']:5d} files  {m['total_kb']:8.1f} KB")

    mods = list_mods()
    if not mods:
        print("\nNo repos found in database -- nothing further to test.")
        return

    first_repo = mods[0]["repo"]

    print(f"\n=== list_files(repo={first_repo!r}) ===")
    lf = list_files(first_repo, limit=10)
    for f in lf["files"]:
        print(f"  {f['path']}  ({f['language']}, {f['size_bytes']} bytes)")

    print(f"\n=== search_code('onInitialize') ===")
    sc = search_code("onInitialize", limit=5)
    print(f"  {sc['result_count']} result(s)")
    for r in sc["results"]:
        print(f"  {r['repo']} | {r['path']}")
        print(f"    {r['snippet']}")

    if lf["files"]:
        target = lf["files"][0]["path"]
        print(f"\n=== get_file(repo={first_repo!r}, path={target!r}) ===")
        gf = get_file(first_repo, target)
        if "error" in gf:
            print("  ERROR:", gf["error"])
        else:
            preview = gf["content"][:150].replace("\n", "\\n")
            print(f"  {gf['size_bytes']} bytes, language={gf['language']}")
            print(f"  preview: {preview}...")

    print(f"\n=== get_file with a wrong path (should suggest alternatives) ===")
    gf_bad = get_file(first_repo, "this/path/does/not/exist.java")
    print(" ", gf_bad)

    print("\nSelf-test complete.")


if __name__ == "__main__":
    if "--self-test" in sys.argv:
        _self_test()
    else:
        _run_mcp_server()
