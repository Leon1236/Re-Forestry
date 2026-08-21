package com.leon1236.reforestry.core.genetics.pollen;

import java.util.ArrayList;
import java.util.List;

import com.leon1236.reforestry.api.genetics.pollen.IPollenType;

public final class PollenTypes {
    private static final List<IPollenType> TYPES = new ArrayList<>();

    private PollenTypes() {
    }

    public static void register(IPollenType type) {
        for (IPollenType existing : TYPES) {
            if (existing.id().equals(type.id())) {
                return;
            }
        }
        TYPES.add(type);
    }

    public static List<IPollenType> all() {
        return List.copyOf(TYPES);
    }
}
