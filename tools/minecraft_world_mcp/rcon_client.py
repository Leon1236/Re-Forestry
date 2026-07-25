#!/usr/bin/env python3
"""Minimal Minecraft RCON client (Source RCON protocol)."""

from __future__ import annotations

import socket
import struct
from dataclasses import dataclass


SERVERDATA_AUTH = 3
SERVERDATA_AUTH_RESPONSE = 2
SERVERDATA_EXECCOMMAND = 2
SERVERDATA_RESPONSE_VALUE = 0


class RconError(RuntimeError):
    pass


@dataclass
class RconConfig:
    host: str = "127.0.0.1"
    port: int = 25575
    password: str = ""
    timeout_seconds: float = 5.0


class RconClient:
    def __init__(self, config: RconConfig):
        self.config = config
        self._sock: socket.socket | None = None
        self._request_id = 0

    def __enter__(self) -> "RconClient":
        self.connect()
        return self

    def __exit__(self, exc_type, exc, tb) -> None:
        self.close()

    def connect(self) -> None:
        if self._sock is not None:
            return
        if not self.config.password:
            raise RconError(
                "RCON password is empty. Run: "
                "python tools/enable_dev_rcon.py"
            )
        sock = socket.create_connection(
            (self.config.host, self.config.port),
            timeout=self.config.timeout_seconds,
        )
        sock.settimeout(self.config.timeout_seconds)
        self._sock = sock
        try:
            response_id, _packet_type, _payload = self._transact(
                SERVERDATA_AUTH, self.config.password
            )
            if response_id == -1:
                raise RconError("RCON authentication failed (bad password)")
        except Exception:
            self.close()
            raise

    def close(self) -> None:
        if self._sock is not None:
            try:
                self._sock.close()
            finally:
                self._sock = None

    def command(self, command: str) -> str:
        if self._sock is None:
            self.connect()
        command = command.lstrip("/")
        _response_id, _packet_type, payload = self._transact(
            SERVERDATA_EXECCOMMAND, command
        )
        return payload

    def _next_id(self) -> int:
        self._request_id += 1
        if self._request_id > 2_000_000_000:
            self._request_id = 1
        return self._request_id

    def _transact(self, packet_type: int, payload: str) -> tuple[int, int, str]:
        assert self._sock is not None
        request_id = self._next_id()
        self._sock.sendall(self._encode(request_id, packet_type, payload))
        response_id, response_type, body = self._read_packet()

        if packet_type == SERVERDATA_AUTH:
            if response_type == SERVERDATA_RESPONSE_VALUE:
                response_id, response_type, body = self._read_packet()
            return response_id, response_type, body

        chunks = [body]
        original_timeout = self._sock.gettimeout()
        try:
            self._sock.settimeout(0.15)
            while True:
                try:
                    _follow_id, follow_type, follow_body = self._read_packet()
                except (TimeoutError, socket.timeout):
                    break
                if follow_type != SERVERDATA_RESPONSE_VALUE:
                    break
                if follow_body:
                    chunks.append(follow_body)
        finally:
            self._sock.settimeout(original_timeout)
        return response_id, response_type, "".join(chunks)

    @staticmethod
    def _encode(request_id: int, packet_type: int, payload: str) -> bytes:
        body = payload.encode("utf-8") + b"\x00\x00"
        size = 4 + 4 + len(body)
        return struct.pack("<iii", size, request_id, packet_type) + body

    def _read_packet(self) -> tuple[int, int, str]:
        assert self._sock is not None
        size_data = self._recv_exact(4)
        (size,) = struct.unpack("<i", size_data)
        if size < 10:
            raise RconError(f"Invalid RCON packet size: {size}")
        data = self._recv_exact(size)
        request_id, packet_type = struct.unpack("<ii", data[:8])
        payload = data[8:-2].decode("utf-8", errors="replace")
        return request_id, packet_type, payload

    def _recv_exact(self, count: int) -> bytes:
        assert self._sock is not None
        chunks: list[bytes] = []
        remaining = count
        while remaining > 0:
            chunk = self._sock.recv(remaining)
            if not chunk:
                raise RconError("RCON connection closed by server")
            chunks.append(chunk)
            remaining -= len(chunk)
        return b"".join(chunks)
