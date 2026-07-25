package com.leon1236.reforestry.api.core;

import java.util.Set;

public interface IErrorSource {
    IErrorSource EMPTY = Set::of;

    Set<IError> getErrors();
}
