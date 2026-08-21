package com.leon1236.reforestry.core.plugin;

import com.leon1236.reforestry.api.genetics.pollen.IPollenType;
import com.leon1236.reforestry.api.plugin.IPollenRegistration;
import com.leon1236.reforestry.core.genetics.pollen.PollenTypes;

public final class PollenRegistrationImpl implements IPollenRegistration {
	@Override
	public void registerPollenType(IPollenType pollenType) {
		PollenTypes.register(pollenType);
	}
}
