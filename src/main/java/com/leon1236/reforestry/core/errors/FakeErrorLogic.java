package com.leon1236.reforestry.core.errors;

import java.util.Set;

import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorLogic;

public enum FakeErrorLogic implements IErrorLogic {
	INSTANCE;

	@Override
	public boolean setCondition(boolean condition, IError error) {
		return false;
	}

	@Override
	public boolean contains(IError error) {
		return false;
	}

	@Override
	public boolean hasErrors() {
		return true;
	}

	@Override
	public void clearErrors() {
	}

	@Override
	public Set<IError> getErrors() {
		return Set.of();
	}
}
