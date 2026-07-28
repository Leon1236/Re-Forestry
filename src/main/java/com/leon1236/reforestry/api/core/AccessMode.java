package com.leon1236.reforestry.api.core;

public enum AccessMode {
	NONE,
	INPUT,
	OUTPUT,
	BOTH;

	public boolean allowsInput() {
		return this == INPUT || this == BOTH;
	}

	public boolean allowsOutput() {
		return this == OUTPUT || this == BOTH;
	}

	public AccessMode next() {
		return switch (this) {
			case NONE -> INPUT;
			case INPUT -> OUTPUT;
			case OUTPUT -> BOTH;
			case BOTH -> NONE;
		};
	}
}
