package com.leon1236.reforestry.core.plugin;

import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.plugin.IErrorRegistration;
import com.leon1236.reforestry.core.errors.ErrorManager;

public final class ErrorRegistrationImpl implements IErrorRegistration {
	private final ErrorManager errorManager;

	public ErrorRegistrationImpl(ErrorManager errorManager) {
		this.errorManager = errorManager;
	}

	@Override
	public void registerError(IError error) {
		errorManager.register(error);
	}
}
